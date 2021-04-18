package gr.codehub.sacchon.services;

import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.util.PaginationTuple;

import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ConsultationService extends BaseService {

    public PaginationTuple<Consultation> get(Patient p, int offset, int limit) {
        return getPagination(em.createQuery("from Consultation c where c.patient is ?1 order by c.date ", Consultation.class)
                .setParameter(1, p),offset,limit);
    }
    public PaginationTuple<Consultation> get(Doctor p, int offset, int limit) {
        return getPagination(em.createQuery("from Consultation c where c.doctor is ?1 order by c.date ", Consultation.class)
                .setParameter(1, p),offset,limit);
    }

    public PaginationTuple<Consultation> getByDate(Patient p, LocalDate start,LocalDate end,int offset, int limit) {
        return getPagination(em.createQuery("from Consultation c where c.patient is ?1 where c.date between ?2 and ?3 order by c.date ", Consultation.class)
                .setParameter(1, p)
                .setParameter(2,start)
                .setParameter(3,end),
                offset,limit);
    }
    public PaginationTuple<Consultation> getByDate(Doctor p, LocalDate start,LocalDate end ,int offset, int limit) {
        return getPagination(em.createQuery("from Consultation c where c.doctor is ?1 where c.date between ?2 and ?3 order by c.date ", Consultation.class)
                .setParameter(1, p)
                .setParameter(2,start)
                .setParameter(3,end),
                offset,limit);
    }

    public Optional<Consultation> save(Consultation p) {
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return Optional.of(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    public Optional<Consultation> update(Consultation p) {
        return save(p);
    }
}
