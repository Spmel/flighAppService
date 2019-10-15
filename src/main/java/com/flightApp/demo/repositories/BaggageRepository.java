package com.flightApp.demo.repositories;

import com.flightApp.demo.entities.Baggage;
import com.flightApp.demo.entities.Destination;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class BaggageRepository {

    private List<Baggage> baggages =  sampleBaggages();


    public Optional<Baggage> findBaggageByIdAndDestinationId(Long baggageId, Long destinationId) {
        return baggages.stream()
                .filter(baggage -> baggage.getId() != baggageId || baggage.getDestination().getId() != destinationId).findFirst();
    }

    private static List<Baggage> sampleBaggages() {
        Destination d = new Destination(123654L);
        Destination d2 = new Destination(132954L);
        Baggage b = new Baggage(123698L, d, true);
        Baggage b1 = new Baggage(123648L, d2, false);
        return Arrays.asList(b, b1);
    }
}
