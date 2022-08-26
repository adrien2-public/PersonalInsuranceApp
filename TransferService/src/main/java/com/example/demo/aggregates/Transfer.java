package com.example.demo.aggregates;

import com.example.demo.commands.CompleteTransferCommand;
import com.example.demo.commands.CreateTransferCommand;
import com.example.demo.commands.RejectTransferCommand;
import com.example.demo.commands.TransferFailedCommand;
import com.example.demo.entities.TransferStatus;

import com.example.demo.events.FailedTransferEvent;
import com.example.demo.events.TransferCompletedEvent;
import com.example.demo.events.TransferCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;


@Slf4j
@Aggregate
public class Transfer {



    @AggregateIdentifier
    private String bankTransferId;
    private String sendingAccountNumber;
    private String receivingAccountNumber;
    private double quantity;
    private TransferStatus status;

    public Transfer() {
    }



    @CommandHandler
    public Transfer(CreateTransferCommand command) {
        TransferCreatedEvent event = new TransferCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);

        log.info("Transfer Aggregate is reached through CreateTransferCommand , Transfer of funds is beginning with transfer id # " + event.getBankTransferId());

    }

    @CommandHandler
    public void completeTransfer(CompleteTransferCommand command){

        TransferCompletedEvent event = new TransferCompletedEvent();
        BeanUtils.copyProperties(command, event);
        event.setStatus(TransferStatus.COMPLETED);
        AggregateLifecycle.apply(event);

        log.info("Transfer Aggregate is reached through CompleteTransferCommand , Transfer of funds is ending for transfer id # " + event.getBankTransferId());

    }

    @CommandHandler
    public void rejectTransfer(RejectTransferCommand command){
        FailedTransferEvent event = new FailedTransferEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);

        log.info("Transfer Aggregate is reached through RejectTransferCommand , Transfer denial is for transfer id # " + event.getBankTransferId());
    }

    @CommandHandler
    public void rejectTransfer(TransferFailedCommand command){
        FailedTransferEvent event = new FailedTransferEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);

        log.info("Transfer Aggregate is reached through TransferFailedCommand ,  Transfer denial is for transfer id # " + event.getBankTransferId());
    }


    @EventSourcingHandler
    public void on(TransferCompletedEvent event){
        this.status = event.getStatus();
    }

    @EventSourcingHandler
    public void on(FailedTransferEvent event){this.status = TransferStatus.FAILED; }

    @EventSourcingHandler
    public void on(TransferCreatedEvent event){

        this.status = TransferStatus.IN_PROGRESS;
        this.bankTransferId = event.getBankTransferId();
        this.receivingAccountNumber = event.getReceivingAccountNumber();
        this.sendingAccountNumber = event.getSendingAccountNumber();
        this.quantity = event.getQuantity();

        log.info("Transfer EventSourcingHandler is reached , Transfer # " + event.getBankTransferId());
    }





}
