package com.flightApp.demo.entities;

public class Ticket {

    private Long id;

    private Destination destination;

    public Ticket(Long id, Destination destination) {
        this.id = id;
        this.destination = destination;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}
