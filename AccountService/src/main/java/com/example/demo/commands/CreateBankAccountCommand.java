package com.example.demo.commands;


import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateBankAccountCommand {

    @TargetAggregateIdentifier
    private String accountNumber;
    private double overdraftLimit;


    public CreateBankAccountCommand( ) {}

    public CreateBankAccountCommand(String accountNumber, double overdraftLimit) {
        this.accountNumber = accountNumber;
        this.overdraftLimit = overdraftLimit;

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

}
