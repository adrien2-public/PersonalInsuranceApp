package com.example.demo.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class RejectTransferCommand {


    @TargetAggregateIdentifier
    private String bankTransferId;

    public RejectTransferCommand() {}

    public RejectTransferCommand(String bankTransferId) {
        this.bankTransferId = bankTransferId;
    }

    public String getBankTransferId() {
        return bankTransferId;
    }

    public void setBankTransferId(String bankTransferId) {
        this.bankTransferId = bankTransferId;
    }

}
