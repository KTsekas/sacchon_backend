package gr.codehub.sacchon.services;

import gr.codehub.sacchon.util.JpaUtil;

import javax.persistence.EntityManager;

public class BaseService {
    protected EntityManager em;

    public BaseService(){
        this.em = JpaUtil.getEntityManager();
    }

    public void close(){
        this.em.close();
    }
}
