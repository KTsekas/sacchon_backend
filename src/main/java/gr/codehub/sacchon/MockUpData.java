package gr.codehub.sacchon;

import gr.codehub.sacchon.model.*;
import gr.codehub.sacchon.services.*;
import gr.codehub.sacchon.util.JpaUtil;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import com.github.javafaker.*;
import gr.codehub.sacchon.util.ResourceHelper;

public class MockUpData {
    static Random rand = new Random();
    public static void mockMe(){

        Faker f = new Faker();


        Doctor d = new Doctor();
        d.setFirstName("doctor");
        d.setLastName("sarakis");
        d.setEmail("doctor@gmail.com");
        d.setPassword("mpes");
        Patient p2 = new Patient();
        p2.setFirstName("patient");
        p2.setLastName("sarakis");
        p2.setEmail("patient@gmail.com");
        p2.setPassword("mpes");
        Reporter r = new Reporter();
        r.setFirstName("reporter");
        r.setLastName("sarakis");
        r.setEmail("reporter@gmail.com");
        r.setPassword("mpes");

        UserRepository repo = new UserRepository(JpaUtil.getEntityManager());
        repo.save(d);
        repo.save(p2);
        repo.save(r);
        for(int i=0;i<200;i++)
            repo.save(createPatient(f));
        for(int i=0;i<100;i++)
            repo.save(createDoctor(f));
        List<Patient> patient = JpaUtil.getEntityManager().createQuery("from Patient",Patient.class).getResultList();
        System.out.println(patient.size());
        patient.forEach(
                p ->{
                    GlucoseService g = new GlucoseService(p);
                    int c = rand.nextInt(64);
                    for( int i =0;i<c;i++)
                        g.post(createGlucose(p));
                    CarbService cb = new CarbService(p);
                    c = rand.nextInt(64);
                    for( int i =0;i<c;i++)
                        cb.post(createCarb(p));
                }
        );
        repo.getEntityManager().close();
    }
    public static void main(String[] args) {

//        mockMe();
//        Patient cl = em.find(Patient.class,10);
//        System.out.println(cl);
//        em.remove(cl);
//        UserService srv = new UserService();
//        Patient p = em.find(Patient.class,27);
//        System.out.println(p);
//        srv.deletePatient(p);
//        Doctor d = em.find(Doctor.class,1);
//        System.out.println(d);
//        srv.deleteDoctor(d);
//        ConsultationService srv = new ConsultationService();
//        srv.getPendingAll(0,Integer.MAX_VALUE).forEach(System.out::println);
        EntityManager em = JpaUtil.getEntityManager();
////        List<Object> res =em.createNativeQuery("SELECT d.id as id FROM users as d left JOIN consultation as c ON d.id = c.doctor_id WHERE d.role='doctor' GROUP BY d.id,c.id having count(c.id) = 0").getResultList();
////        res.forEach(System.out::println);
////        System.out.println(res.size());
//        List<Doctor> res = em.createNamedQuery("doctor.inactive",Doctor.class).getResultList();
//        System.out.println(res.size());
//        List<Patient> p1 = em.createQuery("from Patient where id in (select p.id from Patient p left join p.carbs c group by p having count(c)=0)",Patient.class).getResultList();
//        List<Patient> p2 = em.createQuery("(select p.id from Patient p left join p.glucoseLevels c group by p having count(c)=0)",Patient.class).getResultList();
//        p1.forEach(System.out::println);
//        p2.forEach(System.out::println);
//        List<Doctor> d1 = em.createNamedQuery("doctor.inactive",Doctor.class).getResultList();
        List<Patient> res = em.createNamedQuery("patient.inactive",Patient.class)
                .setParameter(1,LocalDate.of(2022,1,1))
                .setParameter(2,LocalDate.of(2040,1,1))
                .getResultList();
        res.forEach(System.out::println);
//        d1.forEach(System.out::println);
//        List<Patient> pl = em.createQuery("from Patient where id not in(select p.id from Patient p left join p.carbs c where c.date between ?1 and ?2 group by p having count(c)>0)",Patient.class)
//                .setParameter(1,LocalDate.of(2022,1,1))
//                .setParameter(2,LocalDate.of(2040,1,1))
//                .getResultList();
//
//        pl.forEach(System.out::println);

    }
    private static LocalDate randomDate(){
        return LocalDate.now().minusMonths(rand.nextInt(12)+1).minusDays(rand.nextInt(25)+1);
    }
    private static GlucoseRecord createGlucose(Patient p){
        GlucoseRecord record = new GlucoseRecord();
        record.setDate(randomDate());
        record.setGlucoseLevel(rand.nextDouble());
        record.setTime(LocalTime.of(rand.nextInt(24),rand.nextInt(60)));
        record.setPatient(p);
        return record;
    }
    private static CarbRecord createCarb(Patient p){
        CarbRecord record = new CarbRecord();
        record.setDate(randomDate());
        record.setCarbIntake(rand.nextDouble());
        record.setPatient(p);
        return record;
    }

    private static Doctor createDoctor(Faker f){
        Doctor d = new Doctor();
        d.setFirstName(f.name().firstName());
        d.setLastName(f.name().lastName());
        d.setEmail(f.name().username() +"@" + f.name().firstName());
        d.setPassword("mpes");
        return d;
    }

    private static Patient createPatient(Faker f ){
        Patient p = new Patient();
        p.setFirstName(f.name().firstName());
        p.setLastName(f.name().lastName());
        p.setEmail(f.name().username()+"@" + f.name().firstName());
        p.setPassword("mpes");
        return p;
    }
}
