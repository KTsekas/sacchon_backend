package gr.codehub.sacchon.services;

import gr.codehub.sacchon.model.CarbRecord;
import gr.codehub.sacchon.model.Patient;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CarbService extends BaseService {

    private Patient patient;

    public CarbService(Patient patient) {
        this.patient = patient;
    }

    public Optional<CarbRecord> get(int id) {
        return Optional.of(em.find(CarbRecord.class, id));
    }

    public boolean del(int id) {
        Optional<CarbRecord> rec = get(id);
        if (rec.isEmpty())
            return false;
        try {
            em.getTransaction().begin();
            em.remove(rec.get());
            em.createQuery("delete from CarbRecord where patient=?1 and id=?2")
                    .setParameter(1,patient)
                    .setParameter(2,id)
                    .executeUpdate();
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public Optional<CarbRecord> post(CarbRecord rec) {
        rec.setPatient(patient);
        try {
            em.getTransaction().begin();
            em.persist(rec);
            em.getTransaction().commit();
            return Optional.of(rec);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    public Optional<CarbRecord> put(CarbRecord rec) {
        try {
            em.getTransaction().begin();
            em.createQuery("from CarbRecord where patient=?1").setParameter(1, patient);
            em.getTransaction().commit();
            return Optional.of(rec);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @SuppressWarnings("all")
    public List<CarbRecord> getList(int offset, int limit) {
        return em.createQuery("from CarbRecord where patient=?1")
                .setParameter(1,patient)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public double getAverage(LocalDate start, LocalDate end) {
        try {
            return (Double) em.createQuery("select avg(c.carbIntake) from CarbRecord g where c.patient is ?3 and c.dateCreated between ?1 and ?2")
                    .setParameter(1, start)
                    .setParameter(2, end)
                    .setParameter(3, patient).getSingleResult();
        } catch (NullPointerException ex) {
            return 0;
        }
    }
}
