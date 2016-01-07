package com.model;

import javax.persistence.Entity;

@Entity
public class Event extends AbstractDocument {
    private String type;

    public Event() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
