package com.example.demo.entities;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class BankAccount {

    @Id
    private String accountNumber;
    private double balance;
    private double overdraftLimit;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountState;


    public BankAccount( ) {}


    public BankAccount(String accountNumber, double balance, double overdraftLimit, AccountStatus accountState) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.overdraftLimit = overdraftLimit;
        this.accountState = accountState;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }


    public AccountStatus getAccountState() {return accountState;}

    public void setAccountState(AccountStatus accountState) {this.accountState = accountState;}
}
