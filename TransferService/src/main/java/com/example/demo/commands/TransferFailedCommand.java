package com.example.demo.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class TransferFailedCommand {

    @TargetAggregateIdentifier
    private String bankTransferId;
    private String accountNumber;
    private double amount;

    public TransferFailedCommand( ) {}

    public TransferFailedCommand(String bankTransferId, String accountNumber, double amount) {
        this.bankTransferId = bankTransferId;
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBankTransferId() {
        return bankTransferId;
    }

    public void setBankTransferId(String bankTransferId) {
        this.bankTransferId = bankTransferId;
    }

}
