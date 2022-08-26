package com.example.demo.events;

public class BankAccountSourceNotFoundEvent {

    private String bankTransferId;

    public BankAccountSourceNotFoundEvent( ) {}

    public BankAccountSourceNotFoundEvent(String bankTransferId) {
        this.bankTransferId = bankTransferId;
    }

    public String getBankTransferId() {
        return bankTransferId;
    }

    public void setBankTransferId(String bankTransferId) {
        this.bankTransferId = bankTransferId;
    }

}
