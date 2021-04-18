package gr.codehub.sacchon.services;

import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.model.Patient;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public class PatientService extends BaseService {


    public List<Patient> getPatients(Doctor d, int offset, int limit) {
        return getSubList(em.createQuery("from Patient where doctor=?1", Patient.class).setParameter(1, d), offset, limit);
    }

    public List<Patient> getFreePatients(int offset, int limit) {
        return getSubList(em.createNamedQuery("patient.free", Patient.class), offset, limit);
    }

    public Optional<Patient> getPatient(Doctor d, int id) {
        return Optional.ofNullable(
                em.createQuery("from Patient p where p.doctor is ?1 and p.id=?2 ", Patient.class)
                        .setParameter(1, d)
                        .setParameter(2, id)
                        .getSingleResult());
    }
    public Optional<Patient> getPatient( int id) {
        return Optional.ofNullable(em.find(Patient.class,id));
    }

    public List<Patient> getInactive(LocalDate start, LocalDate end, int offset, int limit) {
        return getSubList(em.createNamedQuery("patient.inactive", Patient.class)
                        .setParameter(1, start)
                        .setParameter(2, end),
                offset, limit);
    }

    public List<Patient> getPending(Doctor d, int offset, int limit) {
        return getSubList(em.createNamedQuery("consult.pending", Patient.class)
                        .setParameter(1, d)
                        .setParameter(2, LocalDate.now()),
                offset, limit);
    }

    public List<Patient> getPendingAll(int offset, int limit) {
        return getSubList(em.createNamedQuery("consult.pending.all", Patient.class)
                        .setParameter(1, LocalDate.now()),offset, limit);
    }
}
