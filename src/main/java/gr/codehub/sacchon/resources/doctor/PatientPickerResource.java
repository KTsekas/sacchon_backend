package gr.codehub.sacchon.resources.doctor;

import gr.codehub.sacchon.forms.SingleValueForm;
import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.representations.PaginationListRepresentation;
import gr.codehub.sacchon.representations.doctor.PatientInfoRepresentation;
import gr.codehub.sacchon.resources.AuthResource;
import gr.codehub.sacchon.services.DoctorService;
import gr.codehub.sacchon.services.PatientService;
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
        PatientService srv = new PatientService();
        setService(srv);
        PaginationTuple<Patient> result = srv.getFreePatients(offset,limit);
        return new PaginationListRepresentation<>(
                result.getItems().stream().map(PatientInfoRepresentation::new).collect(Collectors.toList()),
                offset);
    }
    @Post("json")
    public void pickPatient(SingleValueForm<Integer> frm){
        DoctorService srv = new DoctorService();
        setService(srv);
        if( srv.pickPatient((Doctor)getUser(),frm.getValue()).isEmpty() )
            setStatus(Status.CLIENT_ERROR_EXPECTATION_FAILED,"Unable to assign doctor");
    }
}
