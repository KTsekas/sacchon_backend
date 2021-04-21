package gr.codehub.sacchon.services;

import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.model.User;

import java.util.Optional;

public class UserService extends BaseService{


    public boolean deletePatient(Patient p) {
        try {
            em.getTransaction().begin();
            em.createQuery("delete from CarbRecord c where c.patient is ?1")
                    .setParameter(1, p).executeUpdate();
            em.createQuery("delete from GlucoseRecord g where g.patient is ?1")
                    .setParameter(1, p).executeUpdate();
            em.createQuery("delete from Patient p where p is ?1").setParameter(1, p).executeUpdate();
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public boolean deleteDoctor(Doctor d){
       try{
           em.getTransaction().begin();
           em.createQuery("update Patient p set p.doctor = null where p.doctor is ?1").setParameter(1,d).executeUpdate();
           em.createQuery("update Consultation c set c.doctor = null where c.doctor is ?1").setParameter(1,d).executeUpdate();
           em.createQuery("delete from Doctor d where d is ?1").setParameter(1,d).executeUpdate();
           em.getTransaction().commit();
           return true;
       }catch(Exception ex){
           ex.printStackTrace();
           return false;
       }
    }
    public Optional<User> getUser(int id){
        return Optional.ofNullable(em.find(User.class,id));
    }
    public boolean updateUser(User u){
        try{
            em.getTransaction().begin();
            em.merge(u);
            em.getTransaction().commit();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}
