package com.example.demo.utility;


import com.example.demo.commands.StartProcessingClaimCommand;
import com.example.demo.entities.LiabilityRating;

public class ConcreteProcessorTruckCrash  implements ClaimsProcessor{


    public ConcreteProcessorTruckCrash() {
    }

    @Override
    public LiabilityRating doProcessingWork(StartProcessingClaimCommand command) {

        // DO SOME ACTUARIAL WORK HERE , MAYBE REFER TO TABLE THAT USES DAMAGE CLAIM AND POLICY TERMS TO ASSESS LIABILITY LEVEL
        double rating =   calculateLiabilityFactor(command.getDamages());

        LiabilityRating result = getLiabilityRating(rating);

        return  result;
    }


    public double calculateLiabilityFactor(double damagesClaimed){
        double rating = damagesClaimed / 10;
        return rating ;
    }

    public LiabilityRating getLiabilityRating(double rating){

        if(rating < 100) return LiabilityRating.LOW;
        if(rating  > 100  && rating < 200) return LiabilityRating.LOW;
        if(rating  > 200  && rating < 1000) return LiabilityRating.MEDIUM;
        if(rating  > 1000  && rating < 10000) return LiabilityRating.HIGH;
        if(rating  > 10000  ) return LiabilityRating.EXTREME;

        return  LiabilityRating.LOW;
    }

}
