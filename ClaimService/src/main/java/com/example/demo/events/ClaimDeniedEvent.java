package com.example.demo.events;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClaimDeniedEvent {



    private String claimId;
    private String processingId;
    private String policyId;
    private LocalDateTime date;
    private String comments;

}
