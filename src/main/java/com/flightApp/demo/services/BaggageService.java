package com.flightApp.demo.services;

import com.flightApp.demo.entities.Baggage;
import com.flightApp.demo.entities.Destination;
import com.flightApp.demo.exceptions.BaggageNotFoundException;

public interface BaggageService {
    boolean checkinStatus(Long baggageId, Long destinationId) throws BaggageNotFoundException;
}
