package com.example.demo.sagas;


import com.example.demo.commands.CalculateClaimPayoutCommand;
import com.example.demo.commands.DenyClaimCommand;
import com.example.demo.commands.ResolveClaimCommand;
import com.example.demo.commands.StartProcessingClaimCommand;
import com.example.demo.entities.ClaimStatus;
import com.example.demo.entities.Policy;
import com.example.demo.events.FailedProcessingResultsEvent;
import com.example.demo.events.FileClaimEvent;

import com.example.demo.events.PayOutCalculatedEvent;
import com.example.demo.events.SuccessfulProcessingResultsEvent;
import com.example.demo.services.PolicyService;
import com.example.demo.web.StartBankTransferWO;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static org.axonframework.modelling.saga.SagaLifecycle.associateWith;

@Saga
@Slf4j
public class ClaimSaga {


    public String INSURANCE_COMPANY_BANK_ACCOUNT_ID = "aa83cab5-55bc-42d1-b075-6196271cdeb2";

    @Autowired
    private transient CommandGateway gateway;

    @Autowired
    private PolicyService policyService;

    @Autowired
    private RestTemplate restTemplate;



    private String claimId;
    private String firstName;
    private String lastName;
    private double damages;



    @StartSaga
    @SagaEventHandler(associationProperty = "claimId")
    public void on (FileClaimEvent event){

        log.info("ClaimSaga reached through FileClaimEvent, will start request to process claim ");

        String processingId = UUID.randomUUID().toString();

        associateWith("processingId", processingId  );

        StartProcessingClaimCommand command = new StartProcessingClaimCommand();
        BeanUtils.copyProperties(event, command);

        command.setProcessingId(processingId);





        this.claimId = event.getClaimId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.damages = event.getDamages();

        gateway.sendAndWait(command);



    }



    @SagaEventHandler(associationProperty = "claimId")
    public void on (SuccessfulProcessingResultsEvent event){

        log.info("ClaimSaga reached through SuccessfulProcessingResultsEvent, will forward request to calculate payment ");

        String payoutCalculatorId = UUID.randomUUID().toString();

        associateWith("payoutCalculatorId", payoutCalculatorId  );

        CalculateClaimPayoutCommand command = new CalculateClaimPayoutCommand();
        command.setPayoutCalculatorId(payoutCalculatorId);
        command.setClaimId(event.getClaimId());
        command.setPolicyId(event.getPolicyId());


        command.setDamages(8);
        command.setProcessingId(event.getProcessingId());
        command.setRating(event.getRating());


        gateway.sendAndWait(command);
    }


    @SagaEventHandler(associationProperty = "claimId")
    @EndSaga
    public void on (PayOutCalculatedEvent event){

        log.info("ClaimSaga reached through PayOutCalculatedEvent, will forward request to resolve claim and send payment ");

        ResolveClaimCommand command = new ResolveClaimCommand();
        command.setClaimId(event.getClaimId());
        command.setComments("Claim has been resolved and wire transfer is being sent out");
        command.setStatus(ClaimStatus.ACCEPTED);

        associateWith("claimId", event.getClaimId()  );

        gateway.send(command);

       Policy policy =  policyService.getById(event.getPolicyId());
       String customerBankAccountId = policy.getBankAccountId();
       double payout = event.getPayout();
       String insurerBankAcct = INSURANCE_COMPANY_BANK_ACCOUNT_ID;

        StartBankTransferWO transfer = new StartBankTransferWO(insurerBankAcct,customerBankAccountId, payout );

        restTemplate.postForObject("http://localhost:8085/bank-transfers/create/" , transfer , StartBankTransferWO.class);

    }

    @SagaEventHandler(associationProperty = "claimId")
    @EndSaga
    public void on (FailedProcessingResultsEvent event){

        log.info("ClaimSaga reached through FailedProcessingResultsEvent, will perform compensating transaction on claim to state it as denied ");

        DenyClaimCommand command = new DenyClaimCommand();
        command.setClaimId(event.getClaimId());
        command.setDate(event.getDate());
        command.setPolicyId(event.getPolicyId());
        command.setProcessingId(event.getProcessingId());
        command.setComments(event.getComments());

        gateway.send(command);
    }




}
