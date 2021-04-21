package gr.codehub.sacchon.resources.reporter;

import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.representations.reporter.PatientInfoRepresentation;
import gr.codehub.sacchon.resources.AuthResource;
import gr.codehub.sacchon.services.PatientService;
import org.restlet.data.Status;
import org.restlet.resource.Get;

import java.util.Optional;

public class SearchPatient extends AuthResource {

    @Get("json")
    public PatientInfoRepresentation searchPatient(){
        String email = getQueryValue("email");
        if( email == null ){
            setStatus(Status.CLIENT_ERROR_BAD_REQUEST,"Need email to search for user");
            return null;
        }
        PatientService srv = new PatientService();
        setService(srv);
        Optional<Patient> p = srv.getPatient(email);
        if ( p.isEmpty() ){
            setStatus(Status.CLIENT_ERROR_NOT_FOUND,"No patient found with that email");
            return null;
        }
        return new PatientInfoRepresentation(p.get());
    }
}
