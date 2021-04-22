package gr.codehub.sacchon.resources.reporter;

import gr.codehub.sacchon.representations.reporter.PatientInfoRepresentation;
import gr.codehub.sacchon.resources.DateRangeResource;
import gr.codehub.sacchon.services.PatientService;
import gr.codehub.sacchon.util.ResourceHelper;
import org.restlet.resource.Get;

import java.util.List;
import java.util.stream.Collectors;

public class ActivityPatient extends DateRangeResource {

    @Get("json")
    public List<PatientInfoRepresentation> getInactive(){
        int offset = ResourceHelper.parseIntOrDef("offset", 0, this);
        int limit = ResourceHelper.parseIntOrDef("limit", Integer.MAX_VALUE, this);
        PatientService srv = new PatientService();
        setService(srv);
        return srv.getInactive(getStart(),getEnd(),offset,limit).stream().map(PatientInfoRepresentation::new).collect(Collectors.toList());
    }
}
