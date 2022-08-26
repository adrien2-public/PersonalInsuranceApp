package com.example.demo.events;


import com.example.demo.entities.Claim;
import com.example.demo.entities.ClaimStatus;
import com.example.demo.repositories.ClaimsRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClaimsEventHandler {


    @Autowired
    private  ClaimsRepository claimsRepo;

    public ClaimsEventHandler( ) {}



    @EventHandler
    public void on (FileClaimEvent event){

        Claim claim = new Claim();
        claim.setClaimId(event.getClaimId());
        claim.setDamages(event.getDamages());
        claim.setDate(event.getDate());
        claim.setFirstName(event.getFirstName());
        claim.setLastName(event.getLastName());
        claim.setIncidentType(event.getIncidentType());
        claim.setStatus(ClaimStatus.IN_PROGRESS);

        claimsRepo.save(claim);

    }

    @EventHandler
    public void on (ClaimDeniedEvent event){

        Claim claim = claimsRepo.getById(event.getClaimId());

        claim.setStatus(ClaimStatus.REJECTED);

        claimsRepo.save(claim);

    }


    @EventHandler
    public void on (ClaimResolvedEvent event){

        Claim claim = claimsRepo.getById(event.getClaimId());

        claim.setStatus(ClaimStatus.ACCEPTED);

        claimsRepo.save(claim);

    }



}
