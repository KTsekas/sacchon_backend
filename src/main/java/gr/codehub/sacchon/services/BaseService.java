package gr.codehub.sacchon.services;

import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.util.JpaUtil;
import gr.codehub.sacchon.util.PaginationTuple;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class BaseService {
    protected EntityManager em;

    public BaseService() {
        this(true);
    }
    public BaseService(boolean alloc){
        if ( alloc )
            this.em = JpaUtil.getEntityManager();
    }

    public void close(){
        this.em.close();
    }


    public <T> PaginationTuple<T> getPagination(TypedQuery<T> q,int offset,int limit){
        int maxItems = q.getMaxResults();
        return new PaginationTuple<>(q
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList(),
                offset, maxItems);
    }
}
