package com.bugreport.service.entity

import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
@DiscriminatorValue("BUS")
data class BusKotlin(
    @Id override var id: UUID,
    override var brand: String,
    @Column(name = "is_bus")
    val isBus: Boolean
): VehicleKotlin(
    id = id,
    brand = brand
) {
    companion object {
        const val TYPE = "BUS"
    }
}
