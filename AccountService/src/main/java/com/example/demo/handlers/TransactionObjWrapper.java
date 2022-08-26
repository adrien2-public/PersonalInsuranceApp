package com.example.demo.handlers;

import com.example.demo.entities.TransactionType;

public class TransactionObjWrapper {


    private TransactionType transactionType;
    private Double amount;

    public TransactionObjWrapper( ) {}


    public TransactionObjWrapper(TransactionType transactionType, Double amount) {
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        return "TransactionObject{" +
                "transactionType=" + transactionType +
                ", amount=" + amount +
                '}';
    }
}

