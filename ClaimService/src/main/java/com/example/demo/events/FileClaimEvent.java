package com.example.demo.events;


import com.example.demo.entities.ClaimStatus;
import com.example.demo.entities.IncidentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileClaimEvent {


    private String claimId;
    private String policyId;
    private String firstName;
    private String lastName;
    private LocalDateTime date;
    private double damages;
    private IncidentType incidentType;
    private ClaimStatus status;
    private String comments;


}
