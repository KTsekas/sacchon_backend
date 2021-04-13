package gr.codehub.sacchon;

import gr.codehub.sacchon.jpautil.JpaUtil;
import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.model.Patient;

import javax.persistence.EntityManager;

public class MainApp {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
//        Patient pt = new Patient();
//        pt.setFirstName("vaggelis");
//        pt.setEmail("vaggelakis5@gmail.com");
//        pt.setPassword("");
//        Doctor doc = new Doctor();
//
//        doc.setFirstName("doctor vaggelis");
//        doc.setEmail("vaggelai2kis@gmail.com");
//        doc.setPassword("");
//        pt.setDoctor(doc);
//        em.getTransaction().begin();
//        em.persist(pt);
//        em.persist(doc);
//        em.getTransaction().commit();
//        for(int i=0;i<10;i++){
//            Patient patient = em.find(Patient.class,i);
//            if( patient==null)
//                continue;
//            System.out.println(patient);
//            System.out.println(patient.getDoctor());
//        }
//        em.getTransaction().begin();
//        Reporter rep = new Reporter();
//        rep.setFirstName("vaggelakis");
//        rep.setEmail("reporter@vgaggelis.com");
//        rep.setPassword("pwd");
//        em.persist(rep);
//        em.getTransaction().commit();
//        System.out.println(rep);
//        em.close();
        em.getTransaction().begin();
        Doctor doc = em.createQuery("from Doctor where email=?1",Doctor.class).setParameter(1,"vaggelai2kis@gmail.com").getSingleResult();
        Patient p = em.createQuery("from Patient where doctor=?1",Patient.class).setParameter(1,doc).getSingleResult();
        em.getTransaction().commit();
        System.out.println(doc.getFirstName());
        System.out.println(p.getFirstName());
    }


}
