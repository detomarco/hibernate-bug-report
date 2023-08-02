package com.bugreport.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BUS")
public class BusJava extends VehicleJava {

    @Column(name = "is_bus")
    private Boolean isBus;

    public Boolean getBus() {
        return isBus;
    }

    public void setBus(Boolean bus) {
        isBus = bus;
    }
}
