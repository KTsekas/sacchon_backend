import com.github.javafaker.Faker;
import gr.codehub.sacchon.model.*;
import gr.codehub.sacchon.services.CarbService;
import gr.codehub.sacchon.services.GlucoseService;
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
        Faker f = new Faker();

        Doctor d = new Doctor();
        d.setFirstName("doctor");
        d.setLastName("sarakis");
        d.setEmail("doctor@gmail.com");
        d.setPassword("mpes");
        Patient p2 = new Patient();
        p2.setFirstName("doctor");
        p2.setLastName("sarakis");
        p2.setEmail("doctor@gmail.com");
        p2.setPassword("mpes");

        UserRepository repo = new UserRepository(JpaUtil.getEntityManager());
        repo.save(d);
        repo.save(p2);
        for(int i=0;i<200;i++)
            repo.save(createPatient(f));
        for(int i=0;i<100;i++)
            repo.save(createDoctor(f));
        List<Patient> patient = JpaUtil.getEntityManager().createQuery("from Patient",Patient.class).getResultList();
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
        d.setEmail(f.name().username()+"@" + f.name().firstName());
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
