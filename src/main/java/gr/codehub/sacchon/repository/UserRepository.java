package gr.codehub.sacchon.repository;

import gr.codehub.sacchon.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class UserRepository {
    private EntityManager em;

    public UserRepository(EntityManager em) { this.em = em;}


    // searches for a single user based on username(email), returns null if none exist
    public User getUser(String email){
        try {
            return (User) em.createQuery("from User where email=?1").setParameter(1, email).getSingleResult();
        }catch(NoResultException ex){
            return null;
        }
    }
}
