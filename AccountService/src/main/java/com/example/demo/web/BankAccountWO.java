package com.example.demo.web;

public class BankAccountWO {

    private double overdraftLimit;

    public BankAccountWO( ) {}

    public BankAccountWO(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }


    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }


}
