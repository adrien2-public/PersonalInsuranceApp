package com.example.demo.entities;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transfer {

    @Id
    private String bankTransferId;
    private String sendingAccountNumber;
    private String receivingAccountNumber;
    private double quantity;
    private TransferStatus status;


    public Transfer(   ) {

    }

    public Transfer(String bankTransferId, String sendingAccountNumber, String receivingAccountNumber, double quantity, TransferStatus status) {
        this.bankTransferId = bankTransferId;
        this.sendingAccountNumber = sendingAccountNumber;
        this.receivingAccountNumber = receivingAccountNumber;
        this.quantity = quantity;
        this.status = status;
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

    public String getReceivingAccountNumber() {
        return receivingAccountNumber;
    }

    public void setReceivingAccountNumber(String receivingAccountNumber) {
        this.receivingAccountNumber = receivingAccountNumber;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public TransferStatus getStatus() {
        return status;
    }

    public void setStatus(TransferStatus status) {
        this.status = status;
    }


}
