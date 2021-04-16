package gr.codehub.sacchon.repository;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class Repository<T,K> {

    private EntityManager entityManager;

    public Repository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    //Create , insert
    public T save(T t){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(t);
            entityManager.getTransaction().commit();
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract Class<T> getEntityClass();
    public abstract String getClassName();
    // Read select
    public T read(K id){
        return entityManager.find(getEntityClass(), id);
    }

    public List<T> findAll(){
        return entityManager.createQuery("from "+getClassName()).getResultList();
    }


    // Update
    public T update( T t)
    {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(t);
            entityManager.getTransaction().commit();
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    // Delete
    public boolean delete(K id) {
        T t = read(id);
        if (t == null) {
            return false;
        }
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(t);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
