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
public class PayOutCalculatedEvent {

    private String payoutCalculatorId;
    private String claimId;
    private String policyId;
    private double payout;
    private LocalDateTime date;


}
