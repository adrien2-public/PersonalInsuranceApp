package com.example.demo.events;





public class FailedProcessingResultsEvent {

    private String processingId;
    private String claimId;
    private String policyId;

    public FailedProcessingResultsEvent( ) {}

    public FailedProcessingResultsEvent(String processingId, String claimId, String policyId) {
        this.processingId = processingId;
        this.claimId = claimId;
        this.policyId = policyId;
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
