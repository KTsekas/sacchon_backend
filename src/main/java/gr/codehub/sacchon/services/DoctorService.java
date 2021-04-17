package gr.codehub.sacchon.services;

import gr.codehub.sacchon.model.CarbRecord;
import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.model.GlucoseRecord;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DoctorService{
    private Doctor doctor;

    public DoctorService(Doctor doctor){
        this.doctor=doctor;
    }

    public Optional<Patient> pickPatient(int id){
        EntityManager em = JpaUtil.getEntityManager();
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
        }finally{
            em.close();
        }
    }
    public List<CarbRecord> getCarbs(int offset, int limit){
        if ( doctor.getPatient() == null )
            return new ArrayList<>();
        CarbService carb = new CarbService(doctor.getPatient());
        List<CarbRecord> items = carb.getList(offset,limit);
        carb.close();
        return items;
    }
    public List<GlucoseRecord> getGlucoseLevels(int offset, int limit){
        if ( doctor.getPatient() == null )
            return new ArrayList<>();
        GlucoseService carb = new GlucoseService(doctor.getPatient());
        List<GlucoseRecord> items = carb.getList(offset,limit);
        carb.close();
        return items;
    }
}
