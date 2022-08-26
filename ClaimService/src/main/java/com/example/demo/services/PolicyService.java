package com.example.demo.services;


import com.example.demo.entities.Policy;
import com.example.demo.repositories.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolicyService {



    @Autowired
    private PolicyRepository policyRepo;



    public Policy getById(String policyId){

        Policy policy =   policyRepo.getById(policyId);

      return policy;
    }

    public void save(Policy policy){
       policyRepo.save(policy);

    }





}
