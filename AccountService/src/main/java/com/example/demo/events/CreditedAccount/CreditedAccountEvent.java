package com.example.demo.events.CreditedAccount;

public class CreditedAccountEvent {

    private String accountNumber;
    private String bankTransferId;
    private double amount;

    public CreditedAccountEvent( ) {}

    public CreditedAccountEvent(String accountNumber, double amount, String transferNumber) {
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankTransferId() {
        return bankTransferId;
    }

    public void setBankTransferId(String bankTransferId) {
        this.bankTransferId = bankTransferId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


}
