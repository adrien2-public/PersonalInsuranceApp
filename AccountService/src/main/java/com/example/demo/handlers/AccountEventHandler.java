package com.example.demo.handlers;


import com.example.demo.entities.AccountStatus;
import com.example.demo.entities.BankAccount;
import com.example.demo.events.AccountFlaggedEvent;
import com.example.demo.events.BadDebit.UndoBadDebitEvent;
import com.example.demo.events.BankAccountCreated.BankAccountCreatedEvent;
import com.example.demo.events.CreditedAccount.CreditedAccountEvent;
import com.example.demo.events.DebitedAccount.DebitedAccountEvent;
import com.example.demo.events.DepositFunds.DepositFundsEvent;
import com.example.demo.events.InsuranceBusinessAccountCreatedEvent;
import com.example.demo.events.WithdrawFundsEvent;
import com.example.demo.repositories.BankAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AccountEventHandler {

    @Autowired
    private BankAccountRepository repository;


    public AccountEventHandler( ) {}

    @EventHandler
    public void on(BankAccountCreatedEvent event){
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber(event.getAccountNumber());
        bankAccount.setBalance(1000);
        bankAccount.setOverdraftLimit(event.getOverdraftLimit());

        repository.save(bankAccount);
        log.info("EventHandler for BankAccountCreatedEvent is reached in AccountEventHandler, will save Bank account # " + event.getAccountNumber());

    }

    @EventHandler
    public void on(InsuranceBusinessAccountCreatedEvent event){
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber(event.getAccountNumber());
        bankAccount.setBalance(event.getBalance());
        bankAccount.setOverdraftLimit(event.getOverdraftLimit());

        repository.save(bankAccount);
        log.info("EventHandler for InsuranceBusinessAccountCreatedEvent is reached in AccountEventHandler, will save Bank account # " + event.getAccountNumber());

    }

    @EventHandler
    public void on(DepositFundsEvent event){

        log.info("EventHandler for DepositFundsEvent is reached in AccountEventHandler, will deposit $" + event.getQuantity() + " to account # " + event.getAccountNumber());

        BankAccount bankAccount = repository.getById(event.getAccountNumber());
        bankAccount.setBalance( bankAccount.getBalance() + event.getQuantity() );
        repository.save(bankAccount);

    }

    @EventHandler
    public void on(AccountFlaggedEvent event){

        log.info("EventHandler for AccountFlaggedEvent is reached in AccountEventHandler, will flag account # " + event.getAccountNumber());

        BankAccount bankAccount = repository.getById(event.getAccountNumber());
        bankAccount.setAccountState( AccountStatus.FLAGGED );
        repository.save(bankAccount);

    }

    @EventHandler
    public void on(WithdrawFundsEvent event){

        log.info("EventHandler for WithdrawFundsEvent is reached in AccountEventHandler, will withdraw $" + event.getQuantity() + " to account # " + event.getAccountNumber());

        BankAccount bankAccount = repository.getById(event.getAccountNumber());
        bankAccount.setBalance( bankAccount.getBalance() - event.getQuantity() );
        repository.save(bankAccount);
    }


    @EventHandler
    public void on(DebitedAccountEvent event){

        log.info("EventHandler for DebitedAccountEvent is reached in AccountEventHandler, will debit $" + event.getAmount() + " to account # " + event.getAccountNumber());

        BankAccount bankAccount = repository.getById(event.getAccountNumber());

        bankAccount.setBalance( bankAccount.getBalance() - event.getAmount() );
        repository.save(bankAccount);

    }

    @EventHandler
    public void on(CreditedAccountEvent event){

        log.info("EventHandler for CreditedAccountEvent is reached in AccountEventHandler, will credit $" + event.getAmount() + " to account # " + event.getAccountNumber());

        BankAccount bankAccount = repository.getById(event.getAccountNumber());
        bankAccount.setBalance( bankAccount.getBalance() + event.getAmount() );
        repository.save(bankAccount);

    }


    @EventHandler
    public void on(UndoBadDebitEvent event){

        log.info("EventHandler for UndoBadDebitEvent is reached in AccountEventHandler, will undo debit of $" + event.getAmount() + " from account # " + event.getAccountNumber());

        BankAccount bankAccount = repository.getById(event.getAccountNumber());
        bankAccount.setBalance( bankAccount.getBalance() + event.getAmount() );
        repository.save(bankAccount);

    }







}
