package gr.codehub.sacchon.resources.patient;

import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.model.GlucoseRecord;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.representations.ConsultationRepresentation;
import gr.codehub.sacchon.representations.PaginationListRepresentation;
import gr.codehub.sacchon.representations.patient.GlucoseRepresentation;
import gr.codehub.sacchon.resources.AuthResource;
import gr.codehub.sacchon.resources.DateRangeResource;
import gr.codehub.sacchon.services.ConsultationService;
import gr.codehub.sacchon.util.PaginationTuple;
import gr.codehub.sacchon.util.ResourceHelper;
import org.restlet.resource.Get;

import java.util.stream.Collectors;

public class ConsultationResource extends AuthResource {

    @Get("json")
    public PaginationListRepresentation<ConsultationRepresentation> getConsults(){
        int offset = ResourceHelper.parseIntOrDef("offset", 0, this);
        int limit = ResourceHelper.parseIntOrDef("limit", Integer.MAX_VALUE, this);
        ConsultationService srv = new ConsultationService();
        setService(srv);
        PaginationTuple<Consultation> items = srv.get((Patient)getUser(),offset,limit);
        return new PaginationListRepresentation<>(offset,items.getMaxItems(),
                items.getItems().stream().map(ConsultationRepresentation::new).collect(Collectors.toList()));
    }

}

