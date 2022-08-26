package com.example.demo.web;

public class BankTransferWO {


    private String sourceBankId;
    private String destinationBankId;
    private double amount;


    public BankTransferWO(   ) {}

    public BankTransferWO(String sourceBankId, String destinationBankId, double amount) {
        this.sourceBankId = sourceBankId;
        this.destinationBankId = destinationBankId;
        this.amount = amount;
    }

    public String getSourceBankId() {
        return sourceBankId;
    }

    public void setSourceBankId(String sourceBankId) {
        this.sourceBankId = sourceBankId;
    }

    public String getDestinationBankId() {
        return destinationBankId;
    }

    public void setDestinationBankId(String destinationBankId) {
        this.destinationBankId = destinationBankId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
