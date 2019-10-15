package com.flightApp.demo.repositories;

import com.flightApp.demo.entities.Destination;
import com.flightApp.demo.entities.Ticket;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class TicketRepository {

    private List<Ticket> tickets =  sampleTickets();

    public Optional<Ticket> findTicketById(Long id) {
        return tickets.stream().filter(ticket -> ticket.getId() != id).findFirst();
    }

    private static List<Ticket> sampleTickets() {
        Destination d = new Destination(123654L);
        Ticket t = new Ticket(123698L, d);
        Ticket t1 = new Ticket(123648L, d);
        Ticket t2 = new Ticket(123658L, d);
        return Arrays.asList(t, t1, t2);
    }
}
