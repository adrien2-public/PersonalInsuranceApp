package com.example.demo.web;

public class WithdrawalWO {


    private String accountId;
    private double amount;

    public WithdrawalWO(   ) {}

    public WithdrawalWO(String accountId, double amount) {
        this.accountId = accountId;
        this.amount = amount;
    }


    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


}
