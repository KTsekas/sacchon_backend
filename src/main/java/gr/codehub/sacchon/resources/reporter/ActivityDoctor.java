package gr.codehub.sacchon.resources.reporter;


import gr.codehub.sacchon.representations.reporter.DoctorInfoRepresentation;
import gr.codehub.sacchon.representations.reporter.PatientInfoRepresentation;
import gr.codehub.sacchon.resources.DateRangeResource;
import gr.codehub.sacchon.services.DoctorService;
import gr.codehub.sacchon.services.PatientService;
import gr.codehub.sacchon.util.ResourceHelper;
import org.restlet.resource.Get;

import java.util.List;
import java.util.stream.Collectors;

public class ActivityDoctor extends DateRangeResource {

    @Get("json")
    public List<DoctorInfoRepresentation> getInactive(){
        int offset = ResourceHelper.parseIntOrDef("offset", 0, this);
        int limit = ResourceHelper.parseIntOrDef("limit", 0, this);
        DoctorService srv = new DoctorService();
        setService(srv);
        return srv.getInactiveDoctor(getStart(),getEnd(),offset,limit).stream().map(DoctorInfoRepresentation::new).collect(Collectors.toList());
    }
}
