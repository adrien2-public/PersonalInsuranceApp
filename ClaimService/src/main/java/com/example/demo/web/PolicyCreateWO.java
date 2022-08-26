package com.example.demo.web;



public class PolicyCreateWO {



    private String firstName;
    private String lastName;
    private String bankAccountId;
/*
    @Enumerated(EnumType.STRING)
    private CoverageType coverageType;
*/
    private String coverageType;

    private double coverageAmount;
    private double creditScore;


    public PolicyCreateWO() {
    }

    public PolicyCreateWO( String firstName,
                          String lastName, String bankAccountId,
                          String coverageType, double coverageAmount,
                          double creditScore ) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.bankAccountId = bankAccountId;
        this.coverageType = coverageType;
        this.coverageAmount = coverageAmount;
        this.creditScore = creditScore;

    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(String bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public String getCoverageType() {
        return coverageType;
    }

    public void setCoverageType(String coverageType) {
        this.coverageType = coverageType;
    }

    public double getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(double coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public double getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(double creditScore) {
        this.creditScore = creditScore;
    }


}
