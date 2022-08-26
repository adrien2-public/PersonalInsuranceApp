package com.example.demo.events;

public class BankAccountDebitRejectedFromSourceEvent {



    private String bankTransferId;

    public BankAccountDebitRejectedFromSourceEvent( ) {}

    public BankAccountDebitRejectedFromSourceEvent(String bankTransferId) {
        this.bankTransferId = bankTransferId;
    }

    public String getBankTransferId() {
        return bankTransferId;
    }

    public void setBankTransferId(String bankTransferId) {
        this.bankTransferId = bankTransferId;
    }

}
