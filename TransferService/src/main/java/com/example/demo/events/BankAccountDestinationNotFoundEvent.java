package com.example.demo.events;

public class BankAccountDestinationNotFoundEvent {

    private String bankTransferId;

    public BankAccountDestinationNotFoundEvent( ) {

    }

    public BankAccountDestinationNotFoundEvent(String bankTransferId) {
        this.bankTransferId = bankTransferId;
    }

    public String getBankTransferId() {
        return bankTransferId;
    }

    public void setBankTransferId(String bankTransferId) {
        this.bankTransferId = bankTransferId;
    }

}
