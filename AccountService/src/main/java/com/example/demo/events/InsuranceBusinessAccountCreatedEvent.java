package com.example.demo.events;


import com.example.demo.entities.AccountStatus;

public class InsuranceBusinessAccountCreatedEvent {


    private String accountNumber;
    private double overdraftLimit;
    private AccountStatus accountState;
    private  double balance;

    public InsuranceBusinessAccountCreatedEvent( ) {
    }


    public InsuranceBusinessAccountCreatedEvent(String accountNumber, double overdraftLimit, AccountStatus accountState, double balance) {
        this.accountNumber = accountNumber;
        this.overdraftLimit = overdraftLimit;
        this.accountState = accountState;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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
        return accountState;
    }

    public void setAccountState(AccountStatus accountState) {
        this.accountState = accountState;
    }
}
