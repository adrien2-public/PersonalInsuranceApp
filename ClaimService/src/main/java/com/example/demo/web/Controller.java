package com.example.demo.web;


import com.example.demo.commands.FileClaimCommand;
import com.example.demo.entities.*;
import com.example.demo.services.ClaimService;
import com.example.demo.services.PolicyService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/claims")
public class Controller {

    @Autowired
    private PolicyService policyService;

    @Autowired
     private ClaimService claimService;

    private final CommandGateway gateway;

    public Controller(CommandGateway gateway) {
        this.gateway = gateway;
    }

    @PostMapping("/file")
    public ResponseEntity<InsuranceClaimResponse> fileClaim(@RequestBody InsuranceClaimWO claim){

        Policy policy = policyService.getById(claim.getPolicyId());

        if(policy == null) return  new ResponseEntity("Invalid Id, Claim Not Accepted", HttpStatus.NOT_FOUND);

        if(policy.isBehindOnPayments()) return  new ResponseEntity("Pay outstanding bills before filing claim", HttpStatus.FORBIDDEN);

        IncidentType incidentType = getIncidentType(claim.getIncidentType());

        if(incidentType == null ) return  new ResponseEntity("Invalid Incident Type, Claim Not Accepted", HttpStatus.NOT_FOUND);

        String uuid = UUID.randomUUID().toString();

        FileClaimCommand command = FileClaimCommand.builder()
                .claimId(uuid)
                .damages(claim.getDamagesClaimed())
                .date( LocalDateTime.now())
                .firstName(claim.getFirstName())
                .lastName(claim.getLastName())
                .status(ClaimStatus.IN_PROGRESS)
                .policyId(claim.getPolicyId())
                .incidentType(incidentType)
                .comments("Claim filed")
                .build();



        gateway.send(command);

        InsuranceClaimResponse response = new InsuranceClaimResponse(uuid, "Claim has been filed");

        return new ResponseEntity<InsuranceClaimResponse>(response, HttpStatus.CREATED);
    }




    @GetMapping("/claim/{id}")
    public ResponseEntity<Claim> getClaim(@PathVariable(value="id") String claimId){

        Claim claim = claimService.getById(claimId);

        if(claim == null) return  new ResponseEntity("Invalid Id", HttpStatus.NOT_FOUND);

        return new ResponseEntity<Claim>(claim, HttpStatus.FOUND);
    }

    @PostMapping("/policy")
    public ResponseEntity<String> createPolicy(@RequestBody PolicyCreateWO policy){

        CoverageType coverageType =  getCoverageType(policy.getCoverageType());
        if(coverageType == null ){
            return  new ResponseEntity("Invalid Coverage", HttpStatus.BAD_REQUEST);
        }

        String uuid = UUID.randomUUID().toString();

        Policy newPolicy = new Policy();
        newPolicy.setPolicyId(uuid);
        newPolicy.setBankAccountId(newPolicy.getBankAccountId());
        newPolicy.setCoverageAmount(policy.getCoverageAmount());
        newPolicy.setCreditScore(policy.getCreditScore());
        newPolicy.setFirstName(policy.getFirstName());
        newPolicy.setLastName(policy.getLastName());
        newPolicy.setBehindOnPayments(false);
        newPolicy.setCoverageType(coverageType);

        policyService.save(newPolicy);


        return new ResponseEntity("Policy id: " + uuid, HttpStatus.CREATED);
    }


    public IncidentType getIncidentType(String incident){

        if(incident.toLowerCase().contains("weather")) return IncidentType.BAD_WEATHER_COLLISION;
        if(incident.toLowerCase().contains("car")) return IncidentType.CAR_CRASH;
        if(incident.toLowerCase().contains("truck")) return IncidentType.TRUCK_TRASH;
        if(incident.toLowerCase().contains("natural disaster")) return IncidentType.NATURAL_DISASTER;
        if(incident.toLowerCase().contains("vandalism")) return IncidentType.VANDALISM;

        return null;
    }

    public CoverageType getCoverageType(String coverage){

        if(coverage.toLowerCase().equals("1")) return CoverageType.LEVEL_1;
        if(coverage.toLowerCase().equals("2")) return CoverageType.LEVEL_2;
        if(coverage.toLowerCase().equals("3")) return CoverageType.LEVEL_3;
        if(coverage.toLowerCase().equals("4")) return CoverageType.LEVEL_4;

        return null;
    }
}
