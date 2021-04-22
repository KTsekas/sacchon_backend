package gr.codehub.sacchon.services;

import gr.codehub.sacchon.model.*;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class DoctorService extends BaseService{

    public Optional<Doctor> getDoctor( int id) {
        return Optional.ofNullable(em.find(Doctor.class,id));
    }
    public Optional<Doctor> getDoctor( String email ){
        try{
            return Optional.ofNullable(em.createQuery("from Doctor where email = ?1",Doctor.class).setParameter(1,email).getSingleResult());
        }catch(NoResultException ex){
            return Optional.empty();
        }
    }

    public List<Doctor> getInactiveDoctor(LocalDate start, LocalDate end, int offset, int limit){
        return getSubList(em.createNamedQuery("doctor.inactive",Doctor.class)
                .setParameter(1,start)
                .setParameter(2,start),
                offset,limit);

    }
}
