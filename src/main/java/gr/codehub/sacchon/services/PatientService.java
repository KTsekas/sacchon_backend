package gr.codehub.sacchon.services;

import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.util.PaginationTuple;

import javax.persistence.NamedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NamedQuery(
        name = "patient_get_free",
        query = "from Patient p where size(p.carbs) >=30 and size(p.glucoseLevels) >=30 and doctor is null"
)
@NamedQuery(
        name = "patient_inactive",
        query = "select p from Patient p inner join p.carbs c inner join p.glucoseLevels g " +
                "where c.date between ?1 and ?2 and " +
                "g.data between ?1 and ?2 " +
                "group by p" +
                "having size(p.carbs) = 0 and size(c.carbs) = 0"
)
@NamedQuery(
        name = "patient_pending_consult",
        query = "select c from Doctor d inner join d.consultations c where doctor is ?1 group by c.patient having max(c.date) = c.date"
)
@NamedQuery(
        name = "patient_pending_consult_all",
        query = "select c from Doctor d inner join d.consultations c group by c.patient having max(c.date) = c.date"
)
public class PatientService extends BaseService {


    public PaginationTuple<Patient> getPatients(Doctor d, int offset, int limit) {
        return getPagination(em.createQuery("from Patient where doctor=?1", Patient.class).setParameter(1, d), offset, limit);
    }

    public PaginationTuple<Patient> getFreePatients(int offset, int limit) {
        return getPagination(em.createNamedQuery("patient_get_free", Patient.class), offset, limit);
    }

    public Optional<Patient> getPatient(Doctor d, int id) {
        return Optional.ofNullable(
                em.createQuery("from Patient where doctor is ?1", Patient.class).setParameter(1, d).getSingleResult());
    }

    public PaginationTuple<Patient> getInactive(LocalDate start, LocalDate end, int offset, int limit) {
        return getPagination(em.createNamedQuery("patient_inactive", Patient.class)
                        .setParameter(1, start)
                        .setParameter(2, end),
                offset, limit);
    }

    public PaginationTuple<Consultation> getPending(Doctor d, int offset, int limit) {
        List<Consultation> consults = em.createNamedQuery("patient_pending_consult", Consultation.class)
                .setParameter(1, d)
                .getResultList();
        int maxItems = consults.size();
        return new PaginationTuple<>(
                consults.stream()
                .filter(s -> s.getDate().plusMonths(1).compareTo(LocalDate.now()) < 0)
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList()),offset,maxItems);
    }

    public PaginationTuple<Consultation> getPendingAll(int offset, int limit) {
        List<Consultation> consults = em.createNamedQuery("patient_pending_consult", Consultation.class)
                .getResultList();
        int maxItems = consults.size();
        return new PaginationTuple<>(
                consults.stream()
                        .filter(s -> s.getDate().plusMonths(1).compareTo(LocalDate.now()) < 0)
                        .skip(offset)
                        .limit(limit)
                        .collect(Collectors.toList()),offset,maxItems);
    }
}
