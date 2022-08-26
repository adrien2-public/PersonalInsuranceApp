package com.example.demo.commands;


import com.example.demo.entities.LiabilityRating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


@AllArgsConstructor
@Builder
public class CalculateClaimPayoutCommand {


    @TargetAggregateIdentifier
    private String payoutCalculatorId;
    public LiabilityRating rating;
    private String processingId;
    private String claimId;
    private String policyId;
    private double damages;

    public CalculateClaimPayoutCommand( ) {

    }

    public String getPayoutCalculatorId() {
        return payoutCalculatorId;
    }

    public void setPayoutCalculatorId(String payoutCalculatorId) {
        this.payoutCalculatorId = payoutCalculatorId;
    }

    public LiabilityRating getRating() {
        return rating;
    }

    public void setRating(LiabilityRating rating) {
        this.rating = rating;
    }

    public String getProcessingId() {
        return processingId;
    }

    public void setProcessingId(String processingId) {
        this.processingId = processingId;
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

    public double getDamages() {
        return damages;
    }

    public void setDamages(double damages) {
        this.damages = damages;
    }
}
