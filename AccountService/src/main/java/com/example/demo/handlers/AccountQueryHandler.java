package com.example.demo.handlers;


import com.example.demo.entities.BankAccount;
import com.example.demo.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountQueryHandler {


    @Autowired
    private BankAccountRepository repository;


    public BankAccount getBankAccountById(String id){

       BankAccount bankAccount =  repository.getById(id);

       return bankAccount;
    }


}

