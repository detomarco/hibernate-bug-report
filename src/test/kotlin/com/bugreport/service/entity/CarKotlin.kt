package com.bugreport.service.entity

import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
@DiscriminatorValue("CAR")
data class CarKotlin(
    @Id override var id: UUID,
    override var brand: String,
    @Column(name = "is_car")
    val isCar: Boolean
) : VehicleKotlin(
    id = id,
    brand = brand,
)
