package com.example.demo.commands;


import com.example.demo.entities.IncidentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;


@AllArgsConstructor
@Builder
public class StartProcessingClaimCommand {

    @TargetAggregateIdentifier
    private String processingId;
    private String claimId;
    private String policyId;
    private LocalDateTime date;
    private double damages;
    private IncidentType incidentType;
    private String comments;

    public StartProcessingClaimCommand(   ) {

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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
