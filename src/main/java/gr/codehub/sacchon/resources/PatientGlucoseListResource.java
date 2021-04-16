package gr.codehub.sacchon.resources;

import gr.codehub.sacchon.model.GlucoseRecord;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.representations.patient.PatientGlucoseListRepresentation;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

import java.util.List;

public class PatientGlucoseListResource extends AuthResource {

    private int offset;
    private int limit;

    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
        String offset = this.getQueryValue("offset");
        String limit = this.getQueryValue("limit");
        try{
            this.offset = Integer.parseInt(offset);
            this.limit = Integer.parseInt(limit);
        }catch(NumberFormatException ex){
            throw new ResourceException(Status.SERVER_ERROR_BAD_GATEWAY,"invalid offset/limit");
        }
    }

    @Get("json")
    public PatientGlucoseListRepresentation getList(){
        Patient p = (Patient)getUser();
        List<GlucoseRecord> records = p.getGlucoseLevels();
        int count = records.size();
        int end_bound = offset + limit;
        if ( end_bound >= count)
            end_bound = count-1;
        return new PatientGlucoseListRepresentation(
                p.getGlucoseLevels().subList(offset,end_bound),count);
    }
}
