package com.bugreport.service;

import com.bugreport.service.entity.CarJava;
import com.bugreport.service.entity.CarKotlin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JPAUnitTestCase {

    private static EntityManager entityManager;
    private static Boolean initdb = false;

    @BeforeEach
    public void setup() {
        if (!initdb) {
            initdb = true;

            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager
                    .createNativeQuery("CREATE TABLE vehicles ( id uuid DEFAULT random_uuid(), vehicle_type varchar(255) not null, brand varchar(255) not null, is_car boolean null, is_bus boolean null );")
                    .executeUpdate();

            entityManager
                    .createNativeQuery("INSERT INTO vehicles (vehicle_type, brand, is_car) VALUES ('CAR', 'Audi', true);")
                    .executeUpdate();

            entityManager.getTransaction().commit();

        }
    }

    @Test
    public void javaOk() {

        CarJava entityJava = entityManager
                .createQuery("FROM CarJava WHERE brand = 'Audi'", CarJava.class).getResultList().get(0);

        assertNotNull(entityJava);
        assertNotNull(entityJava.getId());
    }

    @Test
    public void kotlinKO() {

        CarKotlin entityKotlin = entityManager
                .createQuery("FROM CarKotlin WHERE brand = 'Audi'", CarKotlin.class).getResultList().get(0);

        assertNotNull(entityKotlin);
        assertNotNull(entityKotlin.getId());
    }
}
