package gr.codehub.sacchon.resources.patient;

import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.resources.AuthResource;
import gr.codehub.sacchon.services.PatientService;
import org.restlet.data.Status;
import org.restlet.resource.Post;

public class NotifiedResource extends AuthResource{

    @Post("json")
    public void notified(){
        PatientService patient = new PatientService();
        if( patient.notified((Patient)getUser()))
            setStatus(Status.SERVER_ERROR_INTERNAL,"Couldn't update the user consultation status");
    }
}
