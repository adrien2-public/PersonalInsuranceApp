package com.example.demo.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class UndoBadCreditCommand {


    @TargetAggregateIdentifier
    private String accountNumber;
    private double quantity;

    public UndoBadCreditCommand(   ) {}

    public UndoBadCreditCommand(String accountNumber, double quantity) {
        this.accountNumber = accountNumber;
        this.quantity = quantity;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }


}
