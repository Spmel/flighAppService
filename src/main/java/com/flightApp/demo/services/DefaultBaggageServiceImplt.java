package com.flightApp.demo.services;

import com.flightApp.demo.entities.Baggage;
import com.flightApp.demo.exceptions.BaggageNotFoundException;
import com.flightApp.demo.repositories.BaggageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DefaultBaggageServiceImplt implements BaggageService {

    @Autowired
    private BaggageRepository baggageRepository;


    @Override
    public boolean checkinStatus(Long baggageId, Long destinationId) throws BaggageNotFoundException {
        Optional<Baggage> optionalBaggage = baggageRepository.findBaggageByIdAndDestinationId(baggageId, destinationId);
        if (!optionalBaggage.isPresent()) {
            throw new BaggageNotFoundException();
        }
        return optionalBaggage.get().isCheckedIn();
    }
}
