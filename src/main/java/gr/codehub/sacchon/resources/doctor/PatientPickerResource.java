package gr.codehub.sacchon.resources.doctor;

import gr.codehub.sacchon.forms.SingleValueForm;
import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.representations.PaginationListRepresentation;
import gr.codehub.sacchon.representations.doctor.PatientInfoRepresentation;
import gr.codehub.sacchon.resources.AuthResource;
import gr.codehub.sacchon.services.DoctorService;
import gr.codehub.sacchon.util.PaginationTuple;
import gr.codehub.sacchon.util.ResourceHelper;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import java.util.stream.Collectors;

public class PatientPickerResource extends AuthResource {

    @Get("json")
    public PaginationListRepresentation<PatientInfoRepresentation> findPatients() {
        int offset = ResourceHelper.parseIntOrDef("offset", 0, this);
        int limit = ResourceHelper.parseIntOrDef("limit", Integer.MAX_VALUE, this);
        DoctorService srv = new DoctorService((Doctor)getUser());
        setService(srv);
        PaginationTuple<Patient> result = srv.getFreePatients(offset,limit);
        return new PaginationListRepresentation<PatientInfoRepresentation>(
                result.getOffset(),
                result.getMaxItems(),
                result.getItems().stream().map(PatientInfoRepresentation::new).collect(Collectors.toList()));
    }
    @Post("json")
    public void pickPatient(SingleValueForm<Integer> frm){
        DoctorService srv = new DoctorService((Doctor)getUser());
        setService(srv);
        if( srv.pickPatient(frm.getValue()).isEmpty() )
            setStatus(Status.CLIENT_ERROR_EXPECTATION_FAILED,"Unable to assign doctor");
    }
}
