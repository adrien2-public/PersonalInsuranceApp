package com.example.demo.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class FlagAccountCommand {

    @TargetAggregateIdentifier
    private String accountNumber;


    public FlagAccountCommand( ) {}

    public FlagAccountCommand(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

}
