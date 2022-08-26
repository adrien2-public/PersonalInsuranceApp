package com.example.demo.services;


import com.example.demo.entities.Claim;
import com.example.demo.repositories.ClaimsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClaimService {

     @Autowired
    private ClaimsRepository repository;


     public Claim getById(String claimId){
       return  repository.getById(claimId);
     }

}
