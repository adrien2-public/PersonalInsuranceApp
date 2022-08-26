package com.example.demo.events;


import com.example.demo.entities.ClaimStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClaimResolvedEvent {

    private String claimId;
    private ClaimStatus status;
    private String comments;


}
