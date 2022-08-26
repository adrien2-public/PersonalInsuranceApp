package com.example.demo.commands;

import com.example.demo.entities.AccountStatus;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateInsuranceBankAccountCommand {

    @TargetAggregateIdentifier
    private String accountNumber;
    private double overdraftLimit;

    private AccountStatus accountState;
    private  double balance;


    public CreateInsuranceBankAccountCommand( ) {

    }

    public CreateInsuranceBankAccountCommand(String accountNumber, double overdraftLimit, AccountStatus accountState, double balance) {
        this.accountNumber = accountNumber;
        this.overdraftLimit = overdraftLimit;
        this.accountState = accountState;
        this.balance = balance;
    }

    public AccountStatus getAccountState() {
        return accountState;
    }

    public void setAccountState(AccountStatus accountState) {
        this.accountState = accountState;
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

}
