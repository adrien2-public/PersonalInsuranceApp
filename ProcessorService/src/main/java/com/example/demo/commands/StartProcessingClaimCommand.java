package com.example.demo.commands;


import com.example.demo.entities.IncidentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
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


}
