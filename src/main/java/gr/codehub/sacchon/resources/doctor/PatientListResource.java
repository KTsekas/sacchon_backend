package gr.codehub.sacchon.resources.doctor;

import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.representations.PaginationListRepresentation;
import gr.codehub.sacchon.representations.doctor.PatientInfoRepresentation;
import gr.codehub.sacchon.resources.AuthResource;
import gr.codehub.sacchon.services.PatientService;
import gr.codehub.sacchon.util.PaginationTuple;
import gr.codehub.sacchon.util.ResourceHelper;
import org.restlet.resource.Get;

import java.util.stream.Collectors;

public class PatientListResource extends AuthResource {

    @Get("json")
    public PaginationListRepresentation<PatientInfoRepresentation> getPatients(){
        int offset = ResourceHelper.parseIntOrDef("offset", 0, this);
        int limit = ResourceHelper.parseIntOrDef("limit", Integer.MAX_VALUE, this);
        PatientService srv = new PatientService();
        setService(srv);
        PaginationTuple<Patient> result = srv.getPatients((Doctor)getUser(),offset,limit);
        return new PaginationListRepresentation<>(
                result.getOffset(),
                result.getMaxItems(),
                result.getItems().stream().map(PatientInfoRepresentation::new).collect(Collectors.toList()));
    }
}

