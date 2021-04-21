package gr.codehub.sacchon.services;

import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.model.Patient;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public class PatientService extends BaseService {


    public List<Patient> getPatients(Doctor d, int offset, int limit) {
        return getSubList(em.createQuery("from Patient p where p.doctor is ?1", Patient.class).setParameter(1, d), offset, limit);
    }

    public List<Patient> getFreePatients(int offset, int limit) {
        return getSubList(em.createNamedQuery("patient.free", Patient.class), offset, limit);
    }

    public Optional<Patient> getPatient(Doctor d, int id) {
        try {
            return Optional.ofNullable(
                    em.createQuery("from Patient p where (p.doctor is ?1 or p.doctor is null) and p.id=?2", Patient.class)
                            .setParameter(1, d)
                            .setParameter(2, id)
                            .getSingleResult());
        }
        catch(NoResultException ex){
            return Optional.empty();
        }
    }
    public Optional<Patient> getPatient( int id) {
        return Optional.ofNullable(em.find(Patient.class,id));
    }
    public Optional<Patient> getPatient( String email ){
        try{
           return Optional.ofNullable(em.createQuery("from Patient where email = ?1",Patient.class).setParameter(1,email).getSingleResult());
        }catch(NoResultException ex){
            return Optional.empty();
        }
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


    public Optional<Patient> save(Patient p){
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

    public boolean notified(Patient p){
        em.getTransaction().begin();
        int res =em.createQuery("update Patient p set p.consultationStatus = ?1 where p.id =?2")
                .setParameter(1, Patient.CONSULTATION_STATUS_READ)
                .setParameter(2,p.getId()).executeUpdate();
        em.getTransaction().commit();
        return res==-1;
    }
}
