package com.bugreport.service

import com.bugreport.service.entity.CarJava
import com.bugreport.service.entity.CarKotlin
import jakarta.persistence.EntityManager
import jakarta.persistence.Persistence
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class JPAUnitCaseTest {

    companion object {
        private lateinit var entityManager: EntityManager
        private var initdb = false
    }

    @BeforeEach
    fun beforeAll() {

        if (!initdb) {
            initdb = true

            val entityManagerFactory = Persistence.createEntityManagerFactory("templatePU")
            entityManager = entityManagerFactory!!.createEntityManager()
            entityManager.transaction.begin()

            entityManager.createNativeQuery(
                """
                CREATE TABLE vehicles (
                    id uuid DEFAULT random_uuid(),
                    vehicle_type varchar(255) not null,
                    brand varchar(255) not null
                );
            """.trimIndent()
            ).executeUpdate()

            entityManager.createNativeQuery(
                """
               insert into vehicles (vehicle_type, brand) values ('CAR', 'Audi');
            """.trimIndent()
            ).executeUpdate()

            entityManager.transaction.commit()

        }

    }

    @Test
    fun javaOK() {

        val entityJava =
            entityManager.createQuery("FROM CarJava WHERE brand = 'Audi'", CarJava::class.java)
                .resultList.first()

        assertTrue(entityJava is CarJava)
        assertNotNull(entityJava)
        assertNotNull(entityJava.id)
    }

    @Test
    fun kotlinOK() {

        val entityKotlin = entityManager
            .createQuery("FROM CarKotlin WHERE brand = 'Audi'", CarKotlin::class.java)
            .resultList.first()

        assertTrue(entityKotlin is CarKotlin)
        assertNotNull(entityKotlin)
        assertNotNull(entityKotlin.id)
    }
}
