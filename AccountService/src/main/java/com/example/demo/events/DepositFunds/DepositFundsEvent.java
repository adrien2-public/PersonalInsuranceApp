package com.example.demo.events.DepositFunds;



public class DepositFundsEvent {



    private String accountNumber;
    private double quantity;

    public DepositFundsEvent(   ) {}

    public DepositFundsEvent(String accountNumber, double quantity) {
        this.accountNumber = accountNumber;
        this.quantity = quantity;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }






}
