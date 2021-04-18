import gr.codehub.sacchon.model.*;
import gr.codehub.sacchon.services.UserRepository;
import gr.codehub.sacchon.util.JpaUtil;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

public class MockUpData {
    static Random rand = new Random();
    public static void main(String[] args) {
        UserRepository repo = new UserRepository(JpaUtil.getEntityManager());
//        for(int i=0;i<100;i++)
//            repo.saveOther(createPatient());
//        for(int i=0;i<50;i++)
//            repo.save(createDoctor());
//        List<Patient> patient = JpaUtil.getEntityManager().createQuery("from Patient").getResultList();
//        patient.forEach(
//                p ->{
//                    GlucoseService g = new GlucoseService(p);
//                    int c = rand.nextInt(64);
//                    for( int i =0;i<c;i++)
//                        g.post(createGlucose(p));
//                    CarbService cb = new CarbService(p);
//                    c = rand.nextInt(64);
//                    for( int i =0;i<c;i++)
//                        cb.post(createCarb(p));
//                }
//        );
        EntityManager em = JpaUtil.getEntityManager();

//        List<Patient> p = em.createQuery(
//                "select p from Patient p inner join p.carbs c" +
//                "inner join p.glucoseLevels g" +
//                        " group by p having count(c) >30").getResultList();
//        List<Patient> p = em.createQuery("from Patient p where size(p.carbs) >=30 and size(p.glucoseLevels)>= 30 and doctor is null",Patient.class).getResultList();
//        List<Patient> k = em.createQuery("from Patient p where size(p.carbs) >=30 and doctor is not null",Patient.class).getResultList();
//        List<Patient> l = em.createQuery("from Patient p where size(p.glucoseLevels)>= 30 and doctor is not null",Patient.class).getResultList();
////        List<Patient> s = em.createQuery("from Patient",Patient.class).getResultList();
//        System.out.println(p.size());
//        System.out.println(k.size());
//        System.out.println(l.size())
//        D;

        Doctor d = (Doctor)em.find(User.class,135);
        System.out.println(d);
        String q2= "select p from Patient p left join p.consultations c " +
                "where p.doctor is null or p.doctor is ?1 group by p "+
                "having MAX(c.expirationDate) > ?2 or (MAX(c.expirationDate) is null and size(p.carbs) >= 30 and size(p.glucoseLevels) >=30)";
        String q3= "select p from Patient p left join p.consultations c " +
                "where (p.doctor is null) or (p.doctor is ?1) group by p "+
                "having MAX(c.expirationDate) >= ?2";
        System.out.println(LocalDate.now());
        List<Patient> date = em.createQuery(q2,Patient.class)
                .setParameter(1,d)
                .setParameter(2,LocalDate.now())
                .getResultList();
        date.forEach(System.out::println);
        System.out.println(date.size());
//        System.out.println(s.size());
//        Doctor doc = em.createQuery("from Doctor where id=118",Doctor.class).getSingleResult();
//        System.out.println(doc);
//        doc.getPatient().forEach(System.out::println);
//        doc.getConsultations().forEach(System.out::println);
//        System.out.println("-----------------------");
//        List<Consultation> expired = em.createQuery("select c from Doctor d inner join d.consultations c where c.doctor is ?1 group by c.patient having max(c.date) = c.date",Consultation.class)
//                .setParameter(1,doc)
//                .getResultList();
//        expired.forEach(s -> {
//            if (s.getDate().plusMonths(1).compareTo(LocalDate.now()) < 0)
//                System.out.println(s);
//                });
//        List<Consultation> consult;

    }
    private static GlucoseRecord createGlucose(Patient p){
        GlucoseRecord record = new GlucoseRecord();
        record.setDate(LocalDate.of(2021,rand.nextInt(12)+1, rand.nextInt(25)+1));
        record.setGlucoseLevel(rand.nextDouble());
        record.setTime(LocalTime.of(rand.nextInt(24),rand.nextInt(60)));
        record.setPatient(p);
        return record;
    }
    private static CarbRecord createCarb(Patient p){
        CarbRecord record = new CarbRecord();
        record.setDate(LocalDate.of(2021,rand.nextInt(12)+1, rand.nextInt(25)+1));
        record.setCarbIntake(rand.nextDouble());
        record.setPatient(p);
        return record;
    }

    private static Doctor createDoctor(){
        Doctor d = new Doctor();
        d.setFirstName("makis" + rand.nextInt());
        d.setLastName("sarakis" + rand.nextInt());
        d.setEmail(String.format("doctor%d@gmail.com",rand.nextInt()));
        d.setPassword("koulis12345");
        return d;
    }

    private static Patient createPatient(){
        Patient p = new Patient();
        p.setFirstName("makis" + rand.nextInt());
        p.setLastName("sarakis" + rand.nextInt());
        p.setEmail(String.format("patient%d@gmail.com",rand.nextInt()));
        p.setPassword("koulis12345");
        return p;
    }
}
