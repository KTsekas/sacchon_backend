package gr.codehub.sacchon.services;

import gr.codehub.sacchon.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class UserRepository extends Repository<User,Integer> {

    public UserRepository(EntityManager em) {
        super(em);
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public String getClassName() {
        return User.class.getName();
    }


    // searches for a single user based on username(email), returns null if none exist
    public User getUser(String email){
        try {
            return (User) getEntityManager().createQuery("from User where email=?1").setParameter(1, email).getSingleResult();
        }catch(NoResultException ex){
            return null;
        }
    }
}
