package com.flightApp.demo.services;

import com.flightApp.demo.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultTicketServiceImplt implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public boolean exists(Long ticketId) {
        return ticketRepository.findTicketById(ticketId).isPresent();
    }
}
