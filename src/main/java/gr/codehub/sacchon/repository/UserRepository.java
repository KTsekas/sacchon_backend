package gr.codehub.sacchon.repository;

import gr.codehub.sacchon.model.User;

import javax.persistence.EntityManager;

public class UserRepository {
    private EntityManager em;

    public UserRepository(EntityManager em) { this.em = em;}


    public User getUser(String email){
        return (User) em.createQuery("from User where email=?1").setParameter(1,email).getSingleResult();
    }
}
