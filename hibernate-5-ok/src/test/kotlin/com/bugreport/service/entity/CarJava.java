package com.bugreport.service.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CAR")
public class CarJava extends VehicleJava {

}
