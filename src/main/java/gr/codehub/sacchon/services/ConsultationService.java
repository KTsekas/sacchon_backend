package gr.codehub.sacchon.services;

import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.representations.reporter.PendingConsultation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ConsultationService extends BaseService {

    public List<Consultation> get(Patient p, int offset, int limit) {
        return getSubList(em.createQuery("from Consultation c where c.patient is ?1 order by c.date desc ", Consultation.class)
                .setParameter(1, p),offset,limit);
    }
    public List<Consultation> get(Doctor p, int offset, int limit) {
        return getSubList(em.createQuery("from Consultation c where c.doctor is ?1 order by c.date desc", Consultation.class)
                .setParameter(1, p),offset,limit);
    }

    public List<Consultation> getByDate(Patient p, LocalDate start, LocalDate end, int offset, int limit) {
        return getSubList(em.createQuery("from Consultation c where c.patient is ?1 and c.date between ?2 and ?3 order by c.date desc", Consultation.class)
                .setParameter(1, p)
                .setParameter(2,start)
                .setParameter(3,end),
                offset,limit);
    }
    public List<Consultation> getByDate(Doctor p, LocalDate start,LocalDate end ,int offset, int limit) {
        return getSubList(em.createQuery("from Consultation c where c.doctor is ?1 and c.date between ?2 and ?3 order by c.date desc", Consultation.class)
                .setParameter(1, p)
                .setParameter(2,start)
                .setParameter(3,end),
                offset,limit);
    }


    public Optional<Consultation> get(int id){
        return Optional.ofNullable(em.find(Consultation.class,id));
    }

    public Optional<Consultation> save(Consultation c,Patient p) {
        try {
            em.getTransaction().begin();
            em.merge(p);
            em.persist(c);
            em.getTransaction().commit();
            return Optional.of(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    public Optional<Consultation> update(Consultation c) {
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return Optional.of(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    public List<Object[]> getPendingAll(int offset, int limit) {
        return getSubList(em.createNamedQuery("consult.pending.all",Object[].class)
                .setParameter(2, LocalDate.now()),offset, limit);
    }
}
