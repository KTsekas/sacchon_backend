package gr.codehub.sacchon.resources.patient;

import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.representations.ConsultationRepresentation;
import gr.codehub.sacchon.resources.AuthResource;
import gr.codehub.sacchon.services.ConsultationService;
import gr.codehub.sacchon.util.ResourceHelper;
import org.restlet.resource.Get;

import java.util.List;
import java.util.stream.Collectors;

public class ConsultationResource extends AuthResource {

    @Get("json")
    public List<ConsultationRepresentation> getConsults(){
        int offset = ResourceHelper.parseIntOrDef("offset", 0, this);
        int limit = ResourceHelper.parseIntOrDef("limit", Integer.MAX_VALUE, this);
        ConsultationService srv = new ConsultationService();
        setService(srv);
        return srv.get((Patient)getUser(),offset,limit).stream().map(ConsultationRepresentation::new).collect(Collectors.toList());
    }

}

