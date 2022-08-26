package com.example.demo.handlers;


import com.example.demo.entities.Transfer;
import com.example.demo.entities.TransferStatus;

import com.example.demo.events.FailedTransferEvent;
import com.example.demo.events.TransferCompletedEvent;
import com.example.demo.events.TransferCreatedEvent;
import com.example.demo.repositories.TransactionRepo;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@ProcessingGroup("amqpEvents")
@Component
 class TransferHandler {

   @Autowired
    private TransactionRepo repository;

    public TransferHandler( ) {}


    @EventHandler
    public void on(TransferCreatedEvent event){
        Transfer transfer = new Transfer();

        transfer.setQuantity(event.getQuantity());
        transfer.setStatus(TransferStatus.IN_PROGRESS);
        transfer.setBankTransferId(event.getBankTransferId());
        transfer.setSendingAccountNumber(event.getSendingAccountNumber());
        transfer.setReceivingAccountNumber(event.getReceivingAccountNumber());


        log.info(" Transaction id " + event.getBankTransferId() + " is now in progress");

        repository.save(transfer);

    }


    @EventHandler
    public void on(FailedTransferEvent event){

        Transfer transfer = repository.getById(event.getBankTransferId());
        transfer.setStatus(TransferStatus.FAILED);

        repository.save(transfer);

        log.info(" Transaction id " + event.getBankTransferId() + " is now marked as failed ");

    }

    @EventHandler
    public void on(TransferCompletedEvent event){
        Transfer transfer = repository.getById(event.getAccountNumber());
        transfer.setStatus(TransferStatus.COMPLETED);

        repository.save(transfer);

        log.info(" Transaction id " + event.getBankTransferId() + " is now marked as completed ");

    }


















}
