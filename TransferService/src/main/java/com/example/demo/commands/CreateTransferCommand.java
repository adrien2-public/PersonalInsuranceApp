package com.example.demo.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateTransferCommand {

    @TargetAggregateIdentifier
    private String bankTransferId;
    private String sendingAccountNumber;
    private String receivingAccountNumber;
    private double quantity;

    public CreateTransferCommand() {}

    public CreateTransferCommand(String bankTransferId, String sendingAccountNumber, String receivingAccountNumber, double quantity) {
        this.bankTransferId = bankTransferId;
        this.sendingAccountNumber = sendingAccountNumber;
        this.receivingAccountNumber = receivingAccountNumber;
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

}
