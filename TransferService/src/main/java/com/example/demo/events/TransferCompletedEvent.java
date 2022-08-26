package com.example.demo.events;

import com.example.demo.entities.TransferStatus;

public class TransferCompletedEvent {

    private TransferStatus status;
    private String accountNumber;
    private String bankTransferId;

    public TransferCompletedEvent( ) {}

    public TransferCompletedEvent(TransferStatus status, String accountNumber, String bankTransferId) {
        this.status = status;
        this.accountNumber = accountNumber;
        this.bankTransferId = bankTransferId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public TransferStatus getStatus() {
        return status;
    }

    public void setStatus(TransferStatus status) {
        this.status = status;
    }

    public String getBankTransferId() {
        return bankTransferId;
    }

    public void setBankTransferId(String bankTransferId) {
        this.bankTransferId = bankTransferId;
    }

}
