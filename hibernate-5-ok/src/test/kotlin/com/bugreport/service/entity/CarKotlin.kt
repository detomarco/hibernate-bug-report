package com.bugreport.service.entity

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.Id
import java.util.*

@Entity
@DiscriminatorValue("CAR")
data class CarKotlin(
    @Id override var id: UUID,
    override var brand: String
) : VehicleKotlin(
    id = id,
    brand = brand,
)
