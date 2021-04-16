package gr.codehub.sacchon.services;

import gr.codehub.sacchon.model.CarbRecord;
import gr.codehub.sacchon.model.GlucoseRecord;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.util.JpaUtil;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class CarbService {

    private Patient patient;
    private EntityManager em;

    public CarbService(Patient patient) {
        this.patient = patient;
        this.em = JpaUtil.getEntityManager();
    }

    public void close() {
        this.em.close();
    }

    public CarbRecord get(int id) {
        return em.find(CarbRecord.class, id);
    }

    public boolean del(int id) {
        CarbRecord rec = get(id);
        if (rec == null)
            return false;
        try {
            em.getTransaction().begin();
            em.remove(rec);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public CarbRecord post(CarbRecord rec) {
        rec.setPatient(patient);
        try {
            em.getTransaction().begin();
            em.persist(rec);
            em.getTransaction().commit();
            return rec;
        } catch (Exception ex) {
            return null;
        }
    }

    public CarbRecord put(CarbRecord rec) {
        try {
            em.getTransaction().begin();
            em.createQuery("from CarbRecord where patient=?1").setParameter(1, patient);
            em.getTransaction().commit();
            return rec;
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("all")
    public List<GlucoseRecord> getList(int offset, int limit) {
        return em.createQuery("from GlucoseRecord where patient=?1")
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }


    public double getAverage(LocalDate start, LocalDate end) {
        try {
            return (Double) em.createQuery("select avg(g.glucoseLevel) from GlucoseRecord g where c.patient is ?3 and c.dateCreated between ?1 and ?2")
                    .setParameter(1, start)
                    .setParameter(2, end)
                    .setParameter(3, patient).getSingleResult();
        } catch (NullPointerException ex) {
            return 0;
        }
    }
}
