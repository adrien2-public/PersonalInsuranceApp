package com.example.demo.web;



import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InsuranceClaimWO {


    private String firstName;
    private String lastName;
    private double damagesClaimed;
    private String incidentType;
    private String policyId;

    public InsuranceClaimWO( ) {}



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

    public double getDamagesClaimed() {
        return damagesClaimed;
    }

    public void setDamagesClaimed(double damagesClaimed) {
        this.damagesClaimed = damagesClaimed;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

}
