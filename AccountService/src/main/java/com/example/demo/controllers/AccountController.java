package com.example.demo.controllers;


import com.example.demo.commands.*;
import com.example.demo.entities.AccountStatus;
import com.example.demo.entities.BankAccount;
import com.example.demo.handlers.AccountQueryHandler;

import com.example.demo.handlers.TransactionSecurityHandler;
import com.example.demo.web.BankAccountWO;
import com.example.demo.web.CreateInsuranceCompanyBankAccountWO;
import com.example.demo.web.DepositWO;
import com.example.demo.web.WithdrawalWO;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/bank-account")
public class AccountController {


    private final CommandGateway gateway;

    @Autowired
    private AccountQueryHandler queryHandler;

    @Autowired
    private TransactionSecurityHandler securityHandler;

    public AccountController(CommandGateway gateway) {
        this.gateway = gateway;

    }


    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> getBankAccountById(@PathVariable(value="id") String id){

      BankAccount account =   queryHandler.getBankAccountById(id);

      return new ResponseEntity(account, HttpStatus.OK);

    }


    @PostMapping("/create")
    public ResponseEntity<String> createBankAccount(@RequestBody BankAccountWO account){

        if( account.getOverdraftLimit() <= 0.0)  return new ResponseEntity("Bad Request", HttpStatus.BAD_REQUEST);

        String id = UUID.randomUUID().toString();
        CreateBankAccountCommand command = new CreateBankAccountCommand();
        command.setAccountNumber(id);
        command.setOverdraftLimit(account.getOverdraftLimit());

        String response = gateway.sendAndWait(command);

        return new ResponseEntity("Account number: " + response, HttpStatus.OK);

    }


    @PostMapping("/withdraw")
    public ResponseEntity<String> withdrawFunds(@RequestBody WithdrawalWO withdrawal){

        if( withdrawal.getAmount() <= 0.0)  return new ResponseEntity("Bad Request", HttpStatus.BAD_REQUEST);

        boolean result = securityHandler.performAntiFraudProcessingDummy(withdrawal.getAccountId());

        if(result == true){
            FlagAccountCommand command = new FlagAccountCommand();
            command.setAccountNumber(withdrawal.getAccountId());
            gateway.send(command);

            return new ResponseEntity("Account was flagged for suspicious activity buddy", HttpStatus.BAD_REQUEST);
        }

        WithdrawFundsCommand command = new WithdrawFundsCommand();
        command.setAccountNumber(withdrawal.getAccountId());
        command.setQuantity(withdrawal.getAmount());

         gateway.sendAndWait(command);


        return new ResponseEntity("Withdrawal Request Sent", HttpStatus.OK);
    }

    @PostMapping("/deposit")
    public ResponseEntity<String>  depositFunds( @RequestBody DepositWO deposit){

        if( deposit.getAmount() <= 0.0)  return new ResponseEntity("Bad Request", HttpStatus.BAD_REQUEST);

        boolean result = securityHandler.performAntiFraudProcessingDummy(deposit.getAccountId());

        if(result == true){
            FlagAccountCommand command = new FlagAccountCommand();
            command.setAccountNumber(deposit.getAccountId());
            gateway.send(command);

            return new ResponseEntity("Account was flagged for suspicious activity buddy", HttpStatus.BAD_REQUEST);
        }

        DepositFundsCommand command = new DepositFundsCommand();
        command.setAccountNumber(deposit.getAccountId());
        command.setQuantity(deposit.getAmount());

        gateway.sendAndWait(command);

        return new ResponseEntity("Deposit Request Sent", HttpStatus.OK);
    }
    


    @PostMapping("/create-business")
    public ResponseEntity<String> createInsuranceCompany(@RequestBody CreateInsuranceCompanyBankAccountWO account){

        CreateInsuranceBankAccountCommand command = new CreateInsuranceBankAccountCommand();
        command.setAccountNumber(account.getAccountNumber());
        command.setOverdraftLimit(100000000);
        command.setBalance(1000000000);
        command.setAccountState(AccountStatus.ACTIVE);

        gateway.send(command);

        return new ResponseEntity("Insurance Business Created", HttpStatus.OK);
    }

























}
