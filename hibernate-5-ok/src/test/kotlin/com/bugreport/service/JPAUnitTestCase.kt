package com.bugreport.service

import javax.persistence.EntityManager
import javax.persistence.Persistence
import com.bugreport.service.entity.CarJava
import com.bugreport.service.entity.CarKotlin
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class JPAUnitTestCase {

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

        val entityJava: CarJava =
            entityManager.createQuery("FROM CarJava WHERE brand = 'Audi'", CarJava::class.java)
                .resultList.first()

        assertNotNull(entityJava)
        assertNotNull(entityJava.id)
    }

    @Test
    fun kotlinKO() {

        val entityKotlin: CarKotlin =
            entityManager.createQuery("FROM CarKotlin WHERE brand = 'Audi'", CarKotlin::class.java)
                .resultList.first()

        assertNotNull(entityKotlin)
        assertNotNull(entityKotlin.id)
    }
}
