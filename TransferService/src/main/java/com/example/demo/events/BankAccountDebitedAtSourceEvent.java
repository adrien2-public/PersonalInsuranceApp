package com.example.demo.events;

public class BankAccountDebitedAtSourceEvent {


    private String bankTransferId;
    private String sendingAccountNumber;
    private double quantity;

    public BankAccountDebitedAtSourceEvent(   ) {

    }

    public BankAccountDebitedAtSourceEvent(String bankTransferId, String sendingAccountNumber, double quantity) {
        this.bankTransferId = bankTransferId;
        this.sendingAccountNumber = sendingAccountNumber;
        this.quantity = quantity;
    }

    public String getBankTransferId() {
        return bankTransferId;
    }

    public void setBankTransferId(String bankTransferId) {
        this.bankTransferId = bankTransferId;
    }

    public String getSendingAccountNumber() {
        return sendingAccountNumber;
    }

    public void setSendingAccountNumber(String sendingAccountNumber) {
        this.sendingAccountNumber = sendingAccountNumber;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
