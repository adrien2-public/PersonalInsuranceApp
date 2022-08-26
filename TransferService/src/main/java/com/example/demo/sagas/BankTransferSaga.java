package com.example.demo.sagas;


import com.example.demo.commands.*;
import com.example.demo.events.BankAccountDestinationNotFoundEvent;
import com.example.demo.events.BankAccountSourceNotFoundEvent;
import com.example.demo.events.CreditedAccount.CreditedAccountEvent;
import com.example.demo.events.DebitedAccount.DebitedAccountEvent;
import com.example.demo.events.DebitedAccountFailed.DebitedAccountFailedEvent;
import com.example.demo.events.TransferCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Saga
public class BankTransferSaga {


    @Autowired
    private transient CommandGateway gateway;

    private String sendingAccountNumber;
    private String receivingAccountNumber;
    private double quantity;



    @StartSaga
    @SagaEventHandler(associationProperty = "bankTransferId")
    public void on (TransferCreatedEvent event){

        log.info("SAGA reached through TransferCreatedEvent, Transfer of funds is beginning, Id :" + event.getBankTransferId() );

        this.sendingAccountNumber = event.getSendingAccountNumber();
        this.receivingAccountNumber = event.getReceivingAccountNumber();
        this.quantity = event.getQuantity();

        DebitAccountCommand command = new DebitAccountCommand();
        command.setAccountNumber(event.getSendingAccountNumber());
        command.setBankTransferId(event.getBankTransferId());
        command.setAmount(event.getQuantity());

        gateway.sendAndWait(command);

    }





    @SagaEventHandler(associationProperty = "bankTransferId")
    public void on (DebitedAccountEvent event){

        log.info("SAGA reached through DebitedAccountEvent, Credit of funds to Acct number "  + receivingAccountNumber + " is beginning, Id :" + event.getBankTransferId() );

        CreditAccountCommand command = new CreditAccountCommand();
        command.setAccountNumber(receivingAccountNumber);
        command.setBankTransferId(event.getBankTransferId());
        command.setAmount(event.getAmount());

        gateway.sendAndWait(command);
    }


    @SagaEventHandler(associationProperty = "bankTransferId")
    @EndSaga
    public void on (BankAccountSourceNotFoundEvent event){

        log.info("SAGA reached through BankAccountSourceNotFoundEvent, Will undo previous transaction for Id :" + event.getBankTransferId() );

        TransferFailedCommand command = new TransferFailedCommand();
        command.setBankTransferId(event.getBankTransferId());

        gateway.sendAndWait(command);
    }


    @SagaEventHandler(associationProperty = "bankTransferId")
    @EndSaga
    public void on (DebitedAccountFailedEvent event){

        log.info("SAGA reached through DebitedAccountFailedEvent, Will cancel  transaction for Id :" + event.getBankTransferId() );

        TransferFailedCommand command = new TransferFailedCommand();
        command.setBankTransferId(event.getBankTransferId());

        gateway.send(command);
    }

    @SagaEventHandler(associationProperty = "bankTransferId")
    @EndSaga
    public void on (BankAccountDestinationNotFoundEvent event){

        log.info("SAGA reached through BankAccountDestinationNotFoundEvent, Will undo previous transaction for Id :" + event.getBankTransferId() );

        UndoBadDebitTransactionCommand refundMoney = new UndoBadDebitTransactionCommand( );
        refundMoney.setTransferNumber(event.getBankTransferId());
        refundMoney.setAmount(quantity);
        refundMoney.setAccountNumber(sendingAccountNumber);

        gateway.send(refundMoney);

        TransferFailedCommand command = new TransferFailedCommand();
        command.setBankTransferId(event.getBankTransferId());

        gateway.send(command);
    }


    @EndSaga
    @SagaEventHandler(associationProperty = "bankTransferId")
    public void on (CreditedAccountEvent event){

        log.info("SAGA reached through CreditedAccountEvent, Will finalize transaction for Id :" + event.getBankTransferId() );

        CompleteTransferCommand command = new CompleteTransferCommand();
        BeanUtils.copyProperties(event, command);

        gateway.send(command);
    }







}
