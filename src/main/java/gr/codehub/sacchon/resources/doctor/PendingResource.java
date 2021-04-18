package gr.codehub.sacchon.resources.doctor;

import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.representations.PaginationListRepresentation;
import gr.codehub.sacchon.representations.doctor.PatientInfoRepresentation;
import gr.codehub.sacchon.resources.AuthResource;
import gr.codehub.sacchon.services.PatientService;
import gr.codehub.sacchon.util.ResourceHelper;
import org.restlet.resource.Get;

import java.util.stream.Collectors;

public class PendingResource extends AuthResource {

    @Get("json")
    public PaginationListRepresentation<PatientInfoRepresentation> getPending() {
        int offset = ResourceHelper.parseIntOrDef("offset", 0, this);
        int limit = ResourceHelper.parseIntOrDef("limit", Integer.MAX_VALUE, this);

        PatientService srv = new PatientService();
        setService(srv);
        return new PaginationListRepresentation<>(
                srv.getPending((Doctor) getUser(), offset, limit).getItems().stream()
                        .map(PatientInfoRepresentation::new)
                        .collect(Collectors.toList()),
                offset);
    }
}
