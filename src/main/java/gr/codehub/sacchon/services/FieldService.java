package gr.codehub.sacchon.services;

import gr.codehub.sacchon.model.PatientField;
import gr.codehub.sacchon.model.Patient;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public abstract class FieldService<R extends PatientField> extends BaseService {

    private final Patient patient;
    abstract String getName();
    abstract Class<R> getRClass();
    abstract String getAverageAggregator();

    public FieldService(Patient patient) {
        this.patient = patient;
    }

    public Optional<R> get(int id) {
        return Optional.ofNullable(em.find(getRClass(), id));
    }

    public boolean del(int id) {
        Optional<R> rec = get(id);
        if( rec.isEmpty() )
            return false;
        try {
            em.getTransaction().begin();
            em.createQuery("delete from " + getName() + " where patient=?1 and id=?2")
                    .setParameter(1,patient)
                    .setParameter(2,id)
                    .executeUpdate();
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public Optional<R> post(R rec) {
        rec.setPatient(patient);
        try {
            em.getTransaction().begin();
            em.persist(rec);
            em.getTransaction().commit();
            return Optional.of(rec);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    public Optional<R> put(R rec) {
        try {
            em.getTransaction().begin();
            em.createQuery("from " + getName() +" where patient=?1").setParameter(1, patient);
            em.getTransaction().commit();
            return Optional.of(rec);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<R> getListRange(LocalDate start,LocalDate end,int offset, int limit) {
        return getSubList(em.createQuery("from " +getName() +" g where patient = ?1 and date between ?2 and ?3 order by g.date desc",getRClass())
                .setParameter(1,patient)
                .setParameter(2,start)
                .setParameter(3,end),offset,limit);

    }


    public List<R> getList(int offset, int limit) {
        return getSubList(em.createQuery("from " +getName() +" g where patient = ?1 order by g.date desc",getRClass())
                .setParameter(1,patient),offset,limit);
    }


    public double getAverage(LocalDate start, LocalDate end) {
        try {
            return em.createQuery("select avg(g." + getAverageAggregator() + ") from " + getName() + " g where g.patient is ?3 and g.date between ?1 and ?2",Double.class)
                    .setParameter(1, start)
                    .setParameter(2, end)
                    .setParameter(3, patient).getSingleResult();
        } catch (NullPointerException ex) {
            return 0;
        }
    }


//    public long getMaxItems() {
//        try{
//            return em.createQuery("select count(*) from " + getName()+ " g where g.patient is ?1",Long.class)
//                    .setParameter(1,patient)
//                    .getSingleResult();
//        }catch(NoResultException ex){
//
//            return 0;
//        }
//    }
}
