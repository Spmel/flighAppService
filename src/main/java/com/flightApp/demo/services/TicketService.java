package com.flightApp.demo.services;

import com.flightApp.demo.entities.Ticket;
import org.springframework.stereotype.Repository;


@Repository
public interface TicketService {
    boolean exists(Long ticketId);
}
