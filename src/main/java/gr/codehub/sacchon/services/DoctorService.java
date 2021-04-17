package gr.codehub.sacchon.services;

import gr.codehub.sacchon.model.*;
import gr.codehub.sacchon.util.PaginationTuple;

import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public PaginationTuple<Patient> getPatients(int offset, int limit) {
        TypedQuery<Patient> q = em.createQuery("from Patient where doctor=?1",Patient.class)
            .setParameter(1,doctor);
        int maxItems = q.getMaxResults();
        return new PaginationTuple<>(
                q.setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList(),
                offset,maxItems);
    }
    // hard query
    public PaginationTuple<Patient> getFreePatients(int offset, int limit){
        TypedQuery<Patient> q = em.createQuery("from Patient p where size(p.carbs) >=30 and size(p.glucoseLevels) >=30 and doctor is null",Patient.class);
        int maxItems = q.getMaxResults();
        return new PaginationTuple<>(q
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList(),
                offset,maxItems);
    }
    public List<Patient> getPendingForConsulation(int offset,int limit){
        List<Consultation> consults = em.createQuery("select c from Doctor d inner join d.consultations c where doctor is ?1 group by c.patient having max(c.date) = c.date",Consultation.class)
                .setParameter(1,doctor)
                .getResultList();
        return consults.stream().filter(s-> s.getDate().plusMonths(1).compareTo(LocalDate.now()) < 0).map(Consultation::getPatient).collect(Collectors.toList());
    }

    public Optional<Patient> getPatient(int id){
        return Optional.ofNullable(
                em.createQuery("from Patient where doctor is ?1",Patient.class).setParameter(1,doctor).getSingleResult());
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
