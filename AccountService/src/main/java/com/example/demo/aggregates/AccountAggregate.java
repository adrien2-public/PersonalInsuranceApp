package com.example.demo.aggregates;


import com.example.demo.commands.*;
import com.example.demo.entities.AccountStatus;

import com.example.demo.events.AccountFlaggedEvent;
import com.example.demo.events.BadDebit.UndoBadDebitEvent;
import com.example.demo.events.BadDeposit.BadDepositEvent;
import com.example.demo.events.BankAccountCreated.BankAccountCreatedEvent;
import com.example.demo.events.CreditedAccount.CreditedAccountEvent;
import com.example.demo.events.DebitedAccount.DebitedAccountEvent;
import com.example.demo.events.DebitedAccountFailed.DebitedAccountFailedEvent;
import com.example.demo.events.DepositFunds.DepositFundsEvent;
import com.example.demo.events.InsufficientFunds.InsufficientFundsEvent;
import com.example.demo.events.InsuranceBusinessAccountCreatedEvent;
import com.example.demo.events.WithdrawFundsEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Slf4j
@Aggregate
public class AccountAggregate {

    @AggregateIdentifier
    private String accountNumber;
    private double overdraftLimit;
    private  double balance;
    private AccountStatus accountState;

    public AccountAggregate( ) {}

    @ExceptionHandler
    public void on (Exception exception){
        System.out.println("General Exception caught");
    }

      @CommandHandler
    public AccountAggregate(CreateBankAccountCommand command){
        BankAccountCreatedEvent event = new BankAccountCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
        log.info("Aggregate for CreateBankAccountCommand reached , will create regular Bank account");

    }

    @CommandHandler
    public AccountAggregate(CreateInsuranceBankAccountCommand command){

        InsuranceBusinessAccountCreatedEvent event = new InsuranceBusinessAccountCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
        log.info("Aggregate for CreateInsuranceBankAccountCommand reached , will create Insurance Corp Bank account");
    }

    @CommandHandler
    public void depositFunds(FlagAccountCommand command){
        AccountFlaggedEvent event = new AccountFlaggedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);

        log.info("Aggregate for FlagAccountCommand reached , will flag Account # " + event.getAccountNumber() );
    }

    @CommandHandler
    public void depositFunds(DepositFundsCommand command){
        DepositFundsEvent event = new DepositFundsEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);

        log.info("Aggregate for DepositFundsCommand reached , will attempt to debit $" + command.getQuantity() + " from Account " + event.getAccountNumber() );
    }

    @CommandHandler
    public void undoDepositFunds(UndoDepositCommand command){
        BadDepositEvent event = new BadDepositEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);

        log.info("Aggregate for UndoDepositCommand reached , will attempt to undo deposit of " + command.getQuantity() + " from Account " + event.getAccountNumber() );
    }

    @CommandHandler
    public void withdrawFunds(WithdrawFundsCommand command){

        log.info("Aggregate for WithdrawFundsCommand reached , will attempt to withdraw " + command.getQuantity() + " from Account " + command.getAccountNumber() );


        if(command.getQuantity() <= this.balance + this.overdraftLimit){
            WithdrawFundsEvent event = new WithdrawFundsEvent();
            BeanUtils.copyProperties(command, event);
            AggregateLifecycle.apply(event);

        }else{
            InsufficientFundsEvent event = new InsufficientFundsEvent();
            BeanUtils.copyProperties(command, event);
            AggregateLifecycle.apply(event);

        }
    }


    @CommandHandler
    public void handle(DebitAccountCommand command){

        log.info("Aggregate for DebitAccountCommand reached ");

        if(command.getAmount()  <= this.balance + this.overdraftLimit){

            DebitedAccountEvent event = new DebitedAccountEvent();
            BeanUtils.copyProperties(command, event);
            AggregateLifecycle.apply(event );

        }else{
            DebitedAccountFailedEvent event = new DebitedAccountFailedEvent();
            BeanUtils.copyProperties(command, event);
            AggregateLifecycle.apply(event);
        }

    }

    @CommandHandler
    public void credit(CreditAccountCommand command){

        CreditedAccountEvent event = new CreditedAccountEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event );

        log.info("Aggregate for CreditAccountCommand reached , will attempt to credit $" + command.getAmount() + " from Account " + event.getAccountNumber() );

    }


    @CommandHandler
    public void refund(UndoBadDebitCommand command){

        UndoBadDebitEvent event = new UndoBadDebitEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event );

        log.info("Aggregate for UndoBadDebitCommand reached , will attempt to undo debit of $" + command.getQuantity() + " from Account " + event.getAccountNumber() );

    }





    @EventSourcingHandler
    public void on(BankAccountCreatedEvent event){
        this.accountNumber = event.getAccountNumber();
        this.accountState = AccountStatus.ACTIVE;
        this.overdraftLimit = event.getOverdraftLimit();
        this.balance = 1000.0;

        log.info(" BankAccountCreatedEvent event reached, added to events for Account # " + event.getAccountNumber());
     //   System.out.println( this.accountNumber);
    }

    @EventSourcingHandler
    public void on(WithdrawFundsEvent event){
        log.info(" WithdrawFundsEvent event reached, added to events for Account # " + event.getAccountNumber());
        this.balance = this.balance - event.getQuantity();
    }

    // AccountFlaggedEvent
    @EventSourcingHandler
    public void on(AccountFlaggedEvent event){
        log.info(" AccountFlaggedEvent event reached, added to events for Account # " + event.getAccountNumber());
        this.accountState = AccountStatus.FLAGGED;
    }

    @EventSourcingHandler
    public void on(InsufficientFundsEvent event){
        log.info(" InsufficientFundsEvent event reached, added to events for Account # " + event.getAccountNumber());
        this.balance = this.balance + 0;
    }

    @EventSourcingHandler
    public void on(DepositFundsEvent event){
        log.info(" DepositFundsEvent event reached, added to events for Account # " + event.getAccountNumber());
        this.balance = this.balance + event.getQuantity();
    }

    @EventSourcingHandler
    public void on(BadDepositEvent event){
        log.info(" BadDepositEvent event reached, added to events for Account # " + event.getAccountNumber());
        this.balance = this.balance - event.getQuantity();}

    @EventSourcingHandler
    public void on(UndoBadDebitEvent event){
        log.info(" UndoBadDebitEvent event reached, added to events for Account # " + event.getAccountNumber());
        this.balance = this.balance + event.getAmount();
    }

    @EventSourcingHandler
    public void on(InsuranceBusinessAccountCreatedEvent event){
        log.info(" InsuranceBusinessAccountCreatedEvent event reached, added to events for Account # " + event.getAccountNumber());
        this.accountNumber = event.getAccountNumber();
        this.accountState = event.getAccountState();
        this.overdraftLimit = event.getOverdraftLimit();
        this.balance = event.getBalance();


    }

    @EventSourcingHandler
    public void on(DebitedAccountEvent event){
        log.info("DebitedAccountEvent event reached, added to events for Account # " + event.getAccountNumber());

        this.balance =  this.balance - event.getAmount();
    }

    @EventSourcingHandler
    public void on(CreditedAccountEvent event){
        log.info("CreditedAccountEvent source event reached, added to events for Account # " + event.getAccountNumber());

        this.balance =  this.balance + event.getAmount();
    }











}
