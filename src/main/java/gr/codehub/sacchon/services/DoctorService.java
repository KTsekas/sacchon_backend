package gr.codehub.sacchon.services;

import gr.codehub.sacchon.model.CarbRecord;
import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.model.GlucoseRecord;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class DoctorService extends BaseService{
    private final Doctor doctor;

    public DoctorService(Doctor doctor){
        this(doctor,true);
    }
    public DoctorService(Doctor doctor,boolean allocManager) {
        super(allocManager);
        this.doctor=doctor;
    }

    public Optional<Patient> pickPatient(int id){
        Patient p = em.find(Patient.class,id);
        try{
            if ( p.getDoctor() != null )
                return Optional.empty();
            p.setDoctor(doctor);
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return Optional.of(p);
        }catch(Exception ex) {
            return Optional.empty();
        }
    }
    @SuppressWarnings("all")
    public List<Patient> getConsultingPatientList(int offset, int limit) {
        return em.createQuery("from Patient where doctor=?1")
                .setParameter(1,doctor)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
    // hard query
    public List<Patient> getPatientsForConsulting(int offset,int limit){
        return null;
    }

    public Optional<Patient> getPatient(int id){
        return Optional.ofNullable(
                (Patient)em.createQuery("from Patient where doctor is ?1").setParameter(1,doctor).getSingleResult());
    }

    public List<CarbRecord> getCarbs(Patient p, int offset, int limit){
        CarbService carb = new CarbService(p);
        List<CarbRecord> items = carb.getList(offset,limit);
        carb.close();
        return items;
    }
    public List<GlucoseRecord> getGlucoseLevels(Patient p,int offset, int limit){
        GlucoseService carb = new GlucoseService(p);
        List<GlucoseRecord> items = carb.getList(offset,limit);
        carb.close();
        return items;
    }
}
