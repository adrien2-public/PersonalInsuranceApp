package com.example.demo.entities;


import javax.persistence.*;

@Entity
@Table(name = "Policy")
public class Policy {

    @Id
    private String policyId;

    @Column(length = 100)
    private String firstName;

    @Column(length = 100)
    private String lastName;

    @Column(length = 100)
    private String bankAccountId;

    @Enumerated(EnumType.STRING)
    private CoverageType coverageType;

    @Column(length = 100)
    private double coverageAmount;

    @Column(length = 100)
    private double creditScore;

    @Column(length = 5)
    private boolean isBehindOnPayments;


    public Policy() {

    }

    public Policy(String policyId, String firstName, String lastName, String bankAccountId,
                  CoverageType coverageType, double coverageAmount, boolean isBehindOnPayments,  double creditScore ) {
        this.policyId = policyId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bankAccountId = bankAccountId;
        this.coverageType = coverageType;
        this.coverageAmount = coverageAmount;
        this.isBehindOnPayments = isBehindOnPayments;
        this.creditScore = creditScore;

    }

    public boolean isBehindOnPayments() {
        return isBehindOnPayments;
    }

    public void setBehindOnPayments(boolean behindOnPayments) {
        isBehindOnPayments = behindOnPayments;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
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

    public CoverageType getCoverageType() {
        return coverageType;
    }

    public void setCoverageType(CoverageType coverageType) {
        this.coverageType = coverageType;
    }

    public double getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(double coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public double getCreditScore() {return creditScore;}

    public void setCreditScore(double creditScore) {this.creditScore = creditScore;}
}
