package com.example.demo.events;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@Builder
public class PayOutCalculatedEvent {

    private String payoutCalculatorId;
    private String claimId;
    private String policyId;
    private double payout;
    private LocalDateTime date;

    public PayOutCalculatedEvent( ) {

    }

    public String getPayoutCalculatorId() {
        return payoutCalculatorId;
    }

    public void setPayoutCalculatorId(String payoutCalculatorId) {
        this.payoutCalculatorId = payoutCalculatorId;
    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public double getPayout() {
        return payout;
    }

    public void setPayout(double payout) {
        this.payout = payout;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
