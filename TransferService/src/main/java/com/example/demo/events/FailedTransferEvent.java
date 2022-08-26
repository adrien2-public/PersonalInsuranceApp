package com.example.demo.events;

public class FailedTransferEvent {

    private String bankTransferId;
    private String accountNumber;
    private double amount;

    public FailedTransferEvent( ) {
    }



    public FailedTransferEvent(String bankTransferId, String accountNumber, double amount) {
        this.bankTransferId = bankTransferId;
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public String getBankTransferId() {
        return bankTransferId;
    }

    public void setBankTransferId(String bankTransferId) {
        this.bankTransferId = bankTransferId;
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
