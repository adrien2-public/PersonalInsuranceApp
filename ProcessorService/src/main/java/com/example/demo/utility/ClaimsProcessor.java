package com.example.demo.utility;


import com.example.demo.commands.StartProcessingClaimCommand;
import com.example.demo.entities.LiabilityRating;

public interface ClaimsProcessor {

    public LiabilityRating doProcessingWork(StartProcessingClaimCommand command);

}


