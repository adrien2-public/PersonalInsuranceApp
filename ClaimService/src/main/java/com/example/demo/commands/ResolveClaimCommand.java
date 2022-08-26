package com.example.demo.commands;

import com.example.demo.entities.ClaimStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


@Builder
public class ResolveClaimCommand {

    @TargetAggregateIdentifier
    private String claimId;
    private ClaimStatus status;
    private String comments;


    public ResolveClaimCommand(   ) {

    }

    public ResolveClaimCommand(String claimId, ClaimStatus status, String comments) {
        this.claimId = claimId;
        this.status = status;
        this.comments = comments;
    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    public ClaimStatus getStatus() {
        return status;
    }

    public void setStatus(ClaimStatus status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
