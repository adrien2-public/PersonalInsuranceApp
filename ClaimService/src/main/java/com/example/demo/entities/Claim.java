package com.example.demo.entities;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Claim")
public class Claim {

    @Id
    private String claimId;

    @Column(length = 100)
    private String firstName;

    @Column(length = 100)
    private String lastName;

    private LocalDateTime date;

    @Column(length = 100)
    private double damages;

    @Enumerated(EnumType.STRING)
    private IncidentType incidentType;

    @Enumerated(EnumType.STRING)
    private ClaimStatus status;


    public Claim( ) {}


    public Claim(String claimId, String firstName, String lastName,
                 LocalDateTime date, double damages, IncidentType incidentType, ClaimStatus status) {
        this.claimId = claimId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
        this.damages = damages;
        this.incidentType = incidentType;
        this.status = status;
    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getDamages() {
        return damages;
    }

    public void setDamages(double damages) {
        this.damages = damages;
    }

    public IncidentType getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(IncidentType incidentType) {
        this.incidentType = incidentType;
    }

    public ClaimStatus getStatus() {
        return status;
    }

    public void setStatus(ClaimStatus status) {
        this.status = status;
    }

}
