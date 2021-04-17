import gr.codehub.sacchon.model.*;
import gr.codehub.sacchon.services.CarbService;
import gr.codehub.sacchon.services.GlucoseService;
import gr.codehub.sacchon.services.UserRepository;
import gr.codehub.sacchon.util.JpaUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MockUpData {
    static Random rand = new Random();
    public static void main(String[] args) {
        UserRepository repo = new UserRepository(JpaUtil.getEntityManager());
//        for(int i=0;i<100;i++)
//            repo.saveOther(createPatient());
//        for(int i=0;i<50;i++)
//            repo.save(createDoctor());
        List<Patient> patient = JpaUtil.getEntityManager().createQuery("from Patient").getResultList();
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
