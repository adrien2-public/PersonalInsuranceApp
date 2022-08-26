package com.example.demo.events.BankAccountCreated;

import com.example.demo.entities.AccountStatus;

public class BankAccountCreatedEvent {



    private String accountNumber;
    private double overdraftLimit;
    private AccountStatus accountState;

    public BankAccountCreatedEvent( ) {}

    public BankAccountCreatedEvent(String accountNumber, double overdraftLimit) {
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



    public AccountStatus getAccountState() {
        return  this.accountState;
    }

    public void setAccountState(AccountStatus accountState) {
        this.accountState = accountState;
    }

}
