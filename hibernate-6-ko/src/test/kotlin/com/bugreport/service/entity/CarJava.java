package com.bugreport.service.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;

@Entity
@DiscriminatorValue("CAR")
public class CarJava extends VehicleJava {

    @Column(name = "is_car")
    private Boolean isCar;

    public Boolean getCar() {
        return isCar;
    }

    public void setCar(Boolean car) {
        isCar = car;
    }
}
