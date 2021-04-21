package gr.codehub.sacchon.resources.reporter;

import gr.codehub.sacchon.model.Reporter;
import gr.codehub.sacchon.representations.reporter.InfoRepresentation;
import gr.codehub.sacchon.resources.AuthResource;
import org.restlet.resource.Get;

public class InfoResource extends AuthResource {

    @Get("json")
    public InfoRepresentation getReporterInfo(){
        return new InfoRepresentation((Reporter)getUser());
    }
}
