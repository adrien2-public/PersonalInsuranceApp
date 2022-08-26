package com.example.demo.aggregates;




import com.example.demo.commands.StartProcessingClaimCommand;
import com.example.demo.entities.IncidentType;
import com.example.demo.entities.LiabilityRating;
import com.example.demo.events.FailedProcessingResultsEvent;
import com.example.demo.events.FinishedProcessingClaimEvent;
import com.example.demo.events.SuccessfulProcessingResultsEvent;

import com.example.demo.utility.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Slf4j
public class ClaimsProcessorAggregate {


    @AggregateIdentifier
    private String processingId;
    private String claimId;
    private String policyId;
    private LocalDateTime date;
    private double damages;
    private IncidentType incidentType;
    private String comments;

    public ClaimsProcessorAggregate() {
    }

    @CommandHandler
    public ClaimsProcessorAggregate(StartProcessingClaimCommand command) {

        log.info("Aggregate reached");

        ClaimsProcessor processor = getProcessor(command.getIncidentType());

        if(processor == null) {

            handleFaultyProcessingInputs(command);

            return;

        }else{

            FinishedProcessingClaimEvent finishProcessingEvent = new FinishedProcessingClaimEvent();
            BeanUtils.copyProperties(command, finishProcessingEvent);
            apply(finishProcessingEvent);


            SuccessfulProcessingResultsEvent event = new SuccessfulProcessingResultsEvent();

            LiabilityRating rating = processor.doProcessingWork(command);

            event.setClaimId(command.getClaimId());
            event.setProcessingId(command.getProcessingId());
            event.setPolicyId(command.getPolicyId());
            event.setRating(rating);

            apply(event);

        }

    }



    @EventSourcingHandler
    public void handle(FinishedProcessingClaimEvent event){

        log.info("FinishedProcessingClaimEvent reached");

        this.processingId = event.getProcessingId();
        this.claimId =  event.getClaimId()  ;
        this.policyId =   event.getPolicyId() ;
        this.date =   event.getDate() ;
        this.damages =   event.getDamages() ;
        this.incidentType =   event.getIncidentType() ;
        this.comments =   event.getComments() ;

    }

    @EventHandler
    public void handle(SuccessfulProcessingResultsEvent event){
        log.info("Processing of claim # : " + event.getClaimId() + " was completed and was successful ");
        this.comments =  "Successfully completed processing" ;

    }

    @EventHandler
    public void handle(FailedProcessingResultsEvent event){
        log.info("Processing of claim # : " + event.getClaimId() + " was completed but was not successful");
        this.comments =  "Failure failure to is the result of processing" ;
    }

    public ClaimsProcessor getProcessor(IncidentType incidentType){

        if(incidentType == IncidentType.BAD_WEATHER_COLLISION) return new ConcreteProcessorBadWeather();
        if(incidentType == IncidentType.CAR_CRASH) return new ConcreteProcessorCarCrash();
        if(incidentType == IncidentType.NATURAL_DISASTER) return new ConcreteProcessorNaturalDisaster();
        if(incidentType == IncidentType.TRUCK_CRASH) return new ConcreteProcessorTruckCrash();
        if(incidentType == IncidentType.VANDALISM) return new ConcreteProcessorVandalism();

        return null;
    }


    private void handleFaultyProcessingInputs(StartProcessingClaimCommand command) {
        FinishedProcessingClaimEvent finishProcessingEvent = new FinishedProcessingClaimEvent();
        BeanUtils.copyProperties(command, finishProcessingEvent);
        apply(finishProcessingEvent);

        FailedProcessingResultsEvent event = new FailedProcessingResultsEvent();
        event.setClaimId(command.getClaimId());
        event.setProcessingId(command.getProcessingId());
        event.setPolicyId(command.getPolicyId());

        apply(event);
    }





}
