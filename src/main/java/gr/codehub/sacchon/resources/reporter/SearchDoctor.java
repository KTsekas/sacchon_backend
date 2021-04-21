package gr.codehub.sacchon.resources.reporter;


import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.representations.reporter.DoctorInfoRepresentation;
import gr.codehub.sacchon.representations.reporter.PatientInfoRepresentation;
import gr.codehub.sacchon.resources.AuthResource;
import gr.codehub.sacchon.services.DoctorService;
import gr.codehub.sacchon.services.PatientService;
import org.restlet.data.Status;
import org.restlet.resource.Get;

import java.util.Optional;

public class SearchDoctor extends AuthResource {

    @Get("json")
    public DoctorInfoRepresentation searchPatient(){
        String email = getQueryValue("email");
        if( email == null ){
            setStatus(Status.CLIENT_ERROR_BAD_REQUEST,"Need email to search for user");
            return null;
        }
        DoctorService srv = new DoctorService();
        setService(srv);
        Optional<Doctor> d = srv.getDoctor(email);
        if ( d.isEmpty() ){
            setStatus(Status.CLIENT_ERROR_NOT_FOUND,"No doctor found with that email");
            return null;
        }
        return new DoctorInfoRepresentation(d.get());
    }
}
