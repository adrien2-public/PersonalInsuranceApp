package com.example.demo.events;


import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class AccountFlaggedEvent {

    @TargetAggregateIdentifier
    private String accountNumber;


    public AccountFlaggedEvent( ) {}

    public AccountFlaggedEvent(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

}
