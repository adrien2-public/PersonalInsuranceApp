package com.example.demo.events;


import com.example.demo.entities.LiabilityRating;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuccessfulProcessingResultsEvent {


    public LiabilityRating rating;
    private String processingId;
    private String claimId;
    private String policyId;


}
