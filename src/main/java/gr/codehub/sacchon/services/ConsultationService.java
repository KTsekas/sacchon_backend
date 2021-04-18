package gr.codehub.sacchon.services;

import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.util.PaginationTuple;

import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ConsultationService extends BaseService {

    public PaginationTuple<Consultation> getConsultations(Patient p, int offset, int limit) {
        TypedQuery<Consultation> q = em.createQuery("from Consultation c where c.patient is ?1 order by c.date ", Consultation.class)
                .setParameter(1, p);
        int maxItems = q.getMaxResults();
        return new PaginationTuple<>(q
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList(),
                offset, maxItems);
    }

    public List<Patient> getPendingForConsultation(Doctor d, int offset, int limit) {
        List<Consultation> consults = em.createQuery("select c from Doctor d inner join d.consultations c where doctor is ?1 group by c.patient having max(c.date) = c.date", Consultation.class)
                .setParameter(1, d)
                .getResultList();
        return consults.stream().filter(s -> s.getDate().plusMonths(1).compareTo(LocalDate.now()) < 0).map(Consultation::getPatient).collect(Collectors.toList());
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
