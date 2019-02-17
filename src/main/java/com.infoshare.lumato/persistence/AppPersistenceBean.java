package com.infoshare.lumato.persistence;

import com.infoshare.lumato.entities.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppPersistenceBean {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("AppPersistence");

        UserEntity user = new UserEntity("kondor", "theGlorious", "padlina", "lubie@placki.xd");

        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();

        entityManagerFactory.close();
    }
}
