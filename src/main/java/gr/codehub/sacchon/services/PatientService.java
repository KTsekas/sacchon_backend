package gr.codehub.sacchon.services;

import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.util.JpaUtil;
import org.restlet.service.Service;

import javax.persistence.EntityManager;

public class PatientService extends Service {
    private EntityManager em;
    private Patient patient;

    public PatientService(Patient patient) {
        this.em = JpaUtil.getEntityManager();
        this.patient = patient;
    }

    public void close(){
        this.em.close();
    }

}
