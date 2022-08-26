package com.example.demo.events;

 import com.example.demo.entities.LiabilityRating;


public class SuccessfulProcessingResultsEvent {


    public LiabilityRating rating;
    private String processingId;
    private String claimId;
    private String policyId;


    public SuccessfulProcessingResultsEvent(   ) {
    }

    public SuccessfulProcessingResultsEvent(LiabilityRating rating, String processingId, String claimId, String policyId) {
        this.rating = rating;
        this.processingId = processingId;
        this.claimId = claimId;
        this.policyId = policyId;
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
}
