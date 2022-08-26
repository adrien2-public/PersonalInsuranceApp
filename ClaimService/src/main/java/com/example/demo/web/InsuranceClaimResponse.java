package com.example.demo.web;




public class InsuranceClaimResponse {

    private String claimId;
    private String comment;

    public InsuranceClaimResponse(   ) {

    }

    public InsuranceClaimResponse(String claimId, String comment) {
        this.claimId = claimId;
        this.comment = comment;
    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
