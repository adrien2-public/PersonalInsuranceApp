package com.example.demo.aggregates;


import com.example.demo.commands.CalculateClaimPayoutCommand;
import com.example.demo.events.PayOutCalculatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDateTime;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class PayoutAggregate {

    @AggregateIdentifier
    private String payoutCalculatorId;
    private String claimId;
    private String policyId;
    private double payout;
    private LocalDateTime date;

    public PayoutAggregate() {}

    @CommandHandler
    public PayoutAggregate(CalculateClaimPayoutCommand command) {

        double payout =  calculatePayout(command);

        PayOutCalculatedEvent event = new PayOutCalculatedEvent();
        event.setPolicyId(command.getPolicyId());
        event.setDate(LocalDateTime.now());
        event.setPayoutCalculatorId(command.getPayoutCalculatorId());
        event.setClaimId(command.getClaimId());
        event.setPayout(payout);

        apply(event);

    }

    @EventSourcingHandler
    private void handle(PayOutCalculatedEvent event){

        this.claimId = event.getClaimId();
        this.date = event.getDate();
        this.payout = event.getPayout();
        this.policyId = event.getPolicyId();
        this.payoutCalculatorId = event.getPayoutCalculatorId();
    }


    public double calculatePayout(CalculateClaimPayoutCommand command){

        double payout = command.getDamages() * 3;

        return payout;
    }
}
