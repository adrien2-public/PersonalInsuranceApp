package com.example.demo.aggregates;


import com.example.demo.commands.FileClaimCommand;
import com.example.demo.commands.ResolveClaimCommand;
import com.example.demo.commands.DenyClaimCommand;
import com.example.demo.entities.ClaimStatus;
import com.example.demo.entities.IncidentType;
import com.example.demo.events.ClaimResolvedEvent;
import com.example.demo.events.FileClaimEvent;
import com.example.demo.events.ClaimDeniedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Slf4j
public class InsuranceClaimAggregate {

    @AggregateIdentifier
    private String claimId;

    private String policyId;

    private String firstName;
    private String lastName;
    private LocalDateTime date;
    private double damages;
    private IncidentType incidentType;
    private ClaimStatus status;
    private String comments;


    public InsuranceClaimAggregate() {}

    @CommandHandler
    public InsuranceClaimAggregate(FileClaimCommand command) {

        if(claimIsHighValue(command) == true){

            command.setComments(" High ticket item, proceed with caution ");

            FileClaimEvent event = new FileClaimEvent();
            BeanUtils.copyProperties(command, event);

            System.out.println( event.toString());


            apply(event);

        }else{

            command.setComments(" low priority  item, proceed as wished ");

            FileClaimEvent event = new FileClaimEvent();
            BeanUtils.copyProperties(command, event);
            System.out.println( event.toString());
            apply(event);
        }

        log.info("Aggregate reached through FileClaimCommand , Insurance claim " + command.getClaimId() +   " is being filed");

    }


    @CommandHandler
    public void handle(DenyClaimCommand command) {

        log.info("Insurance claim " + command.getClaimId() +   " is being denied");

        ClaimDeniedEvent event = new ClaimDeniedEvent();
        BeanUtils.copyProperties(command,  event);
        apply(event);

    }

    @CommandHandler
    public void handle(ResolveClaimCommand command) {

        log.info("Insurance claim " + command.getClaimId() +   " is being finalized and resolved");

        ClaimResolvedEvent event = new ClaimResolvedEvent();
        BeanUtils.copyProperties(command,  event);
        apply(event);

    }

    @EventSourcingHandler
    public void handle(FileClaimEvent event){

        this.claimId =  event.getClaimId()  ;
        this.policyId =   event.getPolicyId() ;
        this.firstName =  event.getFirstName()  ;
        this.lastName =  event.getLastName()  ;
        this.date =   event.getDate() ;
        this.damages =   event.getDamages() ;
        this.incidentType =   event.getIncidentType() ;
        this.status =   event.getStatus() ;
        this.comments =   event.getComments() ;

        log.info(" FileClaimEvent reached by EventSourcingHandler ");

    }

    @EventSourcingHandler
    public void handle(ClaimDeniedEvent event){

        this.date = event.getDate();
        this.status =  ClaimStatus.REJECTED ;
        this.comments =  "Claim denied because :" +  event.getComments() ;
        this.claimId = event.getClaimId();

    }

    @EventSourcingHandler
    public void handle(ClaimResolvedEvent event){

        this.status =  event.getStatus() ;
        this.comments = event.getComments(); ;
        this.claimId = event.getClaimId();

    }


    public boolean claimIsHighValue(FileClaimCommand command){
        if(command.getDamages() > 100000) return  true;
        return false;
    }


}
