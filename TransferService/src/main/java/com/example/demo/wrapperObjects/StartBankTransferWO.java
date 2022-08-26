package com.example.demo.wrapperObjects;


public class StartBankTransferWO {


    private String sourceBankId;
    private String destinationBankId;
    private double amount;

    public StartBankTransferWO( ) {}


    public StartBankTransferWO(String sourceBankId, String destinationBankId, double amount) {
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


    @Override
    public String toString() {
        return "BankTransferWrapperObject{" +
                "sourceBankId='" + sourceBankId + '\'' +
                ", destinationBankId='" + destinationBankId + '\'' +
                ", amount=" + amount +
                '}';
    }

}
