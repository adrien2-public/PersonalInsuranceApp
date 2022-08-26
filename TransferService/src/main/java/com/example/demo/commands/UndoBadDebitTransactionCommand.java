package com.example.demo.commands;


import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class UndoBadDebitTransactionCommand {

    @TargetAggregateIdentifier
    private String accountNumber;
    private String transferNumber;
    private double amount;

    public UndoBadDebitTransactionCommand(   ) {}

    public UndoBadDebitTransactionCommand(String accountNumber, String transferNumber, double amount) {
        this.accountNumber = accountNumber;
        this.transferNumber = transferNumber;
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTransferNumber() {
        return transferNumber;
    }

    public void setTransferNumber(String transferNumber) {
        this.transferNumber = transferNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
