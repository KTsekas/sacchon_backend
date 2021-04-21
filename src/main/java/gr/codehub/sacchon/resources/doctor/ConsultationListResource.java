package gr.codehub.sacchon.resources.doctor;

import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.representations.doctor.ConsultationRepresentation;
import gr.codehub.sacchon.representations.doctor.PatientInfoRepresentation;
import gr.codehub.sacchon.resources.AuthResource;
import gr.codehub.sacchon.services.ConsultationService;
import gr.codehub.sacchon.services.PatientService;
import gr.codehub.sacchon.util.ResourceHelper;
import org.restlet.resource.Get;

import java.util.List;
import java.util.stream.Collectors;

public class ConsultationListResource extends AuthResource {

    @Get("json")
    public List<ConsultationRepresentation> getPatients(){
        int offset = ResourceHelper.parseIntOrDef("offset", 0, this);
        int limit = ResourceHelper.parseIntOrDef("limit", Integer.MAX_VALUE, this);
        ConsultationService srv = new ConsultationService();
        setService(srv);
        return srv.get((Doctor)getUser(),offset,limit).stream().map(ConsultationRepresentation::new).collect(Collectors.toList());
    }
}
