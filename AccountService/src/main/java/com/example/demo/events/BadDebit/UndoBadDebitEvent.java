package com.example.demo.events.BadDebit;


public class UndoBadDebitEvent {



    private String accountNumber;
    private double amount;

    public UndoBadDebitEvent( ) {
    }

    public UndoBadDebitEvent(String accountNumber, double amount) {
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


}
