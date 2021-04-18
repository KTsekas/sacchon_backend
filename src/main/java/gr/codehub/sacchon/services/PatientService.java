package gr.codehub.sacchon.services;

import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.util.PaginationTuple;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class PatientService extends BaseService {


    public PaginationTuple<Patient> getPatients(Doctor d, int offset, int limit) {
        return getPagination(em.createQuery("from Patient where doctor=?1", Patient.class).setParameter(1, d), offset, limit);
    }

    public PaginationTuple<Patient> getFreePatients(int offset, int limit) {
        return getPagination(em.createNamedQuery("patient.free", Patient.class), offset, limit);
    }

    public Optional<Patient> getPatient(Doctor d, int id) {
        return Optional.ofNullable(
                em.createQuery("from Patient p where p.doctor is ?1 and p.id=?2 ", Patient.class)
                        .setParameter(1, d)
                        .setParameter(2, id)
                        .getSingleResult());
    }

    public PaginationTuple<Patient> getInactive(LocalDate start, LocalDate end, int offset, int limit) {
        return getPagination(em.createNamedQuery("patient.inactive", Patient.class)
                        .setParameter(1, start)
                        .setParameter(2, end),
                offset, limit);
    }

    public PaginationTuple<Patient> getPending(Doctor d, int offset, int limit) {
        List<Consultation> consults = em.createNamedQuery("consult.pending", Consultation.class)
                .setParameter(1, d)
                .getResultList();
        return new PaginationTuple<>(
                consults.stream()
                        .filter(s -> s.getDate().plusMonths(1).compareTo(LocalDate.now()) < 0)
                        .skip(offset)
                        .limit(limit)
                        .map(Consultation::getPatient)
                        .collect(Collectors.toList()), offset);
    }

    public PaginationTuple<Consultation> getPendingAll(int offset, int limit) {
        List<Consultation> consults = em.createNamedQuery("consult.pending.all", Consultation.class)
                .getResultList();
        int maxItems = consults.size();
        return new PaginationTuple<>(
                consults.stream()
                        .filter(s -> s.getDate().plusMonths(1).compareTo(LocalDate.now()) < 0)
                        .skip(offset)
                        .limit(limit)
                        .collect(Collectors.toList()), offset);
    }
}
