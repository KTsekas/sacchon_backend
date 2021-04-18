package gr.codehub.sacchon.services;

import gr.codehub.sacchon.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class BaseService {
    protected EntityManager em;

    public BaseService() {
        this(true);
    }

    public BaseService(boolean alloc) {
        if (alloc)
            this.em = JpaUtil.getEntityManager();
    }

    public void close() {
        this.em.close();
    }


    public <T> List<T> getSubList(TypedQuery<T> q, int offset, int limit) {
        return q
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
