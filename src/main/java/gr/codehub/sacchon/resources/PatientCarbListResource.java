package gr.codehub.sacchon.resources;

import gr.codehub.sacchon.model.CarbRecord;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.representations.patient.PatientCarbListRepresentation;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

import java.util.List;

public class PatientCarbListResource extends AuthResource {

    private int offset;
    private int limit;

    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
        String offset = this.getQueryValue("offset");
        String limit = this.getQueryValue("limit");
        try {
            this.offset = Integer.parseInt(offset);
            this.limit = Integer.parseInt(limit);
        } catch (NumberFormatException ex) {
            throw new ResourceException(Status.SERVER_ERROR_BAD_GATEWAY, "invalid offset/limit");
        }

    }

    @Get("json")
    public PatientCarbListRepresentation getList() {
        Patient p = (Patient) getUser();
        List<CarbRecord> records = p.getCarbs();
        int count = records.size();
        int end_bound = offset + limit;
        if (end_bound >= count)
            end_bound = count - 1;
        if (offset > end_bound)
            return new PatientCarbListRepresentation(null,count);
        return new PatientCarbListRepresentation(records.subList(offset, end_bound), count);
    }
}