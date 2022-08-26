package com.example.demo.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class DebitAccountCommand {

    @TargetAggregateIdentifier
    private String accountNumber;
    private String bankTransferId;
    private double amount;

    public DebitAccountCommand() {}

    public DebitAccountCommand(String accountNumber, String bankTransferId, double amount) {
        this.accountNumber = accountNumber;
        this.bankTransferId = bankTransferId;
        this.amount = amount;
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankTransferId() {
        return bankTransferId;
    }

    public void setBankTransferId(String bankTransferId) {
        this.bankTransferId = bankTransferId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
