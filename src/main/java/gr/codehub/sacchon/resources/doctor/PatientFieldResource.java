package gr.codehub.sacchon.resources.doctor;

import gr.codehub.sacchon.forms.FieldForm;
import gr.codehub.sacchon.model.CarbRecord;
import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.model.PatientField;
import gr.codehub.sacchon.representations.PaginationListRepresentation;
import gr.codehub.sacchon.representations.patient.CarbRepresentation;
import gr.codehub.sacchon.resources.AuthResource;
import gr.codehub.sacchon.services.CarbService;
import gr.codehub.sacchon.services.FieldService;
import gr.codehub.sacchon.services.PatientService;
import gr.codehub.sacchon.util.PaginationTuple;
import gr.codehub.sacchon.util.ResourceHelper;
import org.restlet.data.Status;
import org.restlet.resource.Get;

import java.util.Optional;
import java.util.stream.Collectors;

public abstract class PatientFieldResource<T extends PatientField, R> extends AuthResource {



    abstract FieldService<T> getService();
    abstract R getRepresentation(T item);

    @Get("json")
    public PaginationListRepresentation<R> getCarbs(){
        int offset = ResourceHelper.parseIntOrDef("offset", 0, this);
        int limit = ResourceHelper.parseIntOrDef("limit", Integer.MAX_VALUE, this);
        int id = ResourceHelper.parseIntOrDef("limit", -1, this);
        if ( id == -1){
            setStatus(Status.CLIENT_ERROR_BAD_REQUEST,"No patient id specified");
            return null;
        }
        PatientService srv = new PatientService();
        Optional<Patient> patient = srv.getPatient((Doctor)getUser(),id);
        if (patient.isEmpty()){
            setStatus(Status.CLIENT_ERROR_NOT_FOUND,"No patient found with those criteria");
            return null;
        }
        srv.close();
        FieldService<T> nSrv = getService();
        PaginationTuple<T> items = nSrv.getList(offset,limit);
        return new PaginationListRepresentation<>(offset,items.getMaxItems(),
                items.getItems().stream().map(this::getRepresentation).collect(Collectors.toList()));
    }
}
