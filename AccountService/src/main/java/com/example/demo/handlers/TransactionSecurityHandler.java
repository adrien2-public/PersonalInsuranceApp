package com.example.demo.handlers;


import com.example.demo.entities.TransactionType;
import com.example.demo.events.BadDebit.UndoBadDebitEvent;
import com.example.demo.events.BankAccountCreated.BankAccountCreatedEvent;
import com.example.demo.events.CreditedAccount.CreditedAccountEvent;
import com.example.demo.events.DebitedAccount.DebitedAccountEvent;
import com.example.demo.events.DepositFunds.DepositFundsEvent;
import com.example.demo.events.InsuranceBusinessAccountCreatedEvent;
import com.example.demo.events.WithdrawFundsEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Component
@ProcessingGroup("antiFraudReplay")
public class TransactionSecurityHandler {



    Map<String, ArrayList<TransactionObjWrapper>> map = new HashMap<>();


    @EventSourcingHandler
    public void on(BankAccountCreatedEvent event){

        ArrayList<TransactionObjWrapper> list =  map.get(event.getAccountNumber());

        TransactionObjWrapper newTransaction = new TransactionObjWrapper();
        newTransaction.setAmount(0.0);
        newTransaction.setTransactionType(TransactionType.OPEN);
        list.add(newTransaction);

    }


    @EventSourcingHandler
    public void on(DepositFundsEvent event){

        ArrayList<TransactionObjWrapper> list =  map.get(event.getAccountNumber());

        TransactionObjWrapper newTransaction = new TransactionObjWrapper();
        newTransaction.setAmount(newTransaction.getAmount());
        newTransaction.setTransactionType(TransactionType.CREDIT);
        list.add(newTransaction);


    }



    @EventSourcingHandler
    public void on(WithdrawFundsEvent event){


        ArrayList<TransactionObjWrapper> list =  map.get(event.getAccountNumber());

        TransactionObjWrapper newTransaction = new TransactionObjWrapper();
        newTransaction.setAmount(newTransaction.getAmount());
        newTransaction.setTransactionType(TransactionType.DEBIT);
        list.add(newTransaction);

    }


    @EventSourcingHandler
    public void on(DebitedAccountEvent event){

        ArrayList<TransactionObjWrapper> list =  map.get(event.getAccountNumber());

        TransactionObjWrapper newTransaction = new TransactionObjWrapper();
        newTransaction.setAmount(newTransaction.getAmount());
        newTransaction.setTransactionType(TransactionType.DEBIT);
        list.add(newTransaction);

    }


    @EventSourcingHandler
    public void on(CreditedAccountEvent event){

        ArrayList<TransactionObjWrapper> list =  map.get(event.getAccountNumber());

        TransactionObjWrapper newTransaction = new TransactionObjWrapper();
        newTransaction.setAmount(newTransaction.getAmount());
        newTransaction.setTransactionType(TransactionType.CREDIT);
        list.add(newTransaction);


    }


    @EventSourcingHandler
    public void on(UndoBadDebitEvent event){

        ArrayList<TransactionObjWrapper> list =  map.get(event.getAccountNumber());

        TransactionObjWrapper newTransaction = new TransactionObjWrapper();
        newTransaction.setAmount(newTransaction.getAmount());
        newTransaction.setTransactionType(TransactionType.CORRECTION);
        list.add(newTransaction);

    }

    public ArrayList<TransactionObjWrapper> getByAggregateId(String key){

        ArrayList<TransactionObjWrapper> x = map.get(key);

        return x;
    }

    public void performAntiFraudProcessing(String key){
/*
        ArrayList<TransactionObjWrapper> eventList = getByAggregateId(key);

        List<TransactionObjWrapper> inputData = new ArrayList<>();

        for(int x = 0; x < eventList.size(); x++){
            inputData.add(eventList.get(x));
        }

        Logger.getLogger("org.apache").setLevel(Level.WARN);

        SparkConf conf = new SparkConf().setAppName("mySpark").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<Object> myRdd = sc.parallelize(inputData);
*/
        /**
         (1-A) Windows users need to install winutils.exe to use Apache Spark, as it uses POSIX like file access operations in windows using windows API.

         (1-B) do some processing with the list of objects, ideally in the real world we would pass the data into a pipeline that performs some
         machine learning work that will categorize the ensemble of events to determine if fraudulent activity is present.

         (1-C) the above work is omitted as it is beyond scope of project, rather a dummy implementation is used in method performAntiFraudProcessingDummy() .

         */

        //  sc.close();

    }

    public boolean performAntiFraudProcessingDummy(String key){

        ArrayList<TransactionObjWrapper> eventList = getByAggregateId(key);

        List<TransactionObjWrapper> dodgyTransactions = new ArrayList<>();

        for(int x = 0; x < eventList.size(); x++){

            if(eventList.get(x).getAmount() > 1000){
                dodgyTransactions.add(eventList.get(x));
            }
        }

        if(dodgyTransactions.size() > 2 ) return true;

        return false;
    }






}