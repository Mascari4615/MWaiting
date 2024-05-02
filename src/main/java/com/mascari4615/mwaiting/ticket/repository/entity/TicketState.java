package com.mascari4615.mwaiting.ticket.repository.entity;

import lombok.Getter;

@Getter
public enum TicketState {
    CANCELED("CANCELED"),
    WAITING("WAITING"),
    PROCESSING("PROCESSING"),
    COMPLETED("COMPLETED");

    final private String name;

    TicketState(String name) {
        this.name = name;
    }
}
