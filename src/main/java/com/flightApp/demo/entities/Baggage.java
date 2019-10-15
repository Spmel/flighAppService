package com.flightApp.demo.entities;

public class Baggage {

    private Long id;
    private  Destination destination;
    private boolean checkedIn;

    public Baggage(Long id, Destination destination, boolean checkedIn) {
        this.id = id;
        this.destination = destination;
        this.checkedIn = checkedIn;
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

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }
}
