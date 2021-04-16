package gr.codehub.sacchon.resources.patient;

import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.representations.SingleValueRepresentation;
import gr.codehub.sacchon.resources.AuthResource;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

public class CountResource extends AuthResource {


    @Get("json")
    public SingleValueRepresentation<Integer> getCount(){
        String type = getAttribute("type");
        if ( type == null )
            // false routing this should never happen
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL,"False routing from server");
        final Patient usr = (Patient)getUser();
        int count;
        if ( type.equals("carb"))
            count = usr.getCarbs().size();
        else if ( type.equals(""))
            count = usr.getGlucoseLevels().size();
        else
            count=0;
        return new SingleValueRepresentation<>(count);
    }
}
