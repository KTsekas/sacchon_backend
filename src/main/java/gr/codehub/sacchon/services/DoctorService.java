package gr.codehub.sacchon.services;

import gr.codehub.sacchon.model.*;
import gr.codehub.sacchon.util.PaginationTuple;

import javax.persistence.NamedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@NamedQuery(
        name ="doctor_inactive",
        query="select d doctor d inner join d.consultations c " +
                "where c.date between ?1 and ?2 and " +
                "group by d" +
                "having size(d.carbs) = 0"
)

public class DoctorService extends BaseService{

    public Optional<Patient> pickPatient(Doctor d,int id){
        Patient p = em.find(Patient.class,id);
        try{
            if ( p.getDoctor() != null )
                return Optional.empty();
            p.setDoctor(d);
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return Optional.of(p);
        }catch(Exception ex) {
            return Optional.empty();
        }
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

    public PaginationTuple<Doctor> getInactiveDoctor(LocalDate start, LocalDate end,int offset,int limit){
        return getPagination(em.createNamedQuery("doctor_inactive",Doctor.class)
                .setParameter(1,start)
                .setParameter(2,start),
                offset,limit);

    }
}
