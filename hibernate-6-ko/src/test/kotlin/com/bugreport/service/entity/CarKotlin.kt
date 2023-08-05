package com.bugreport.service.entity

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.UUID

@Entity
@DiscriminatorValue("CAR")
data class CarKotlin(
    @Id override var id: UUID,
    override var brand: String
) : VehicleKotlin(
    id = id,
    brand = brand,
)
