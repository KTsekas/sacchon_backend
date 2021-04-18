package gr.codehub.sacchon.resources.doctor;

import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.model.PatientField;
import gr.codehub.sacchon.resources.AuthResource;
import gr.codehub.sacchon.services.FieldService;
import gr.codehub.sacchon.services.PatientService;
import gr.codehub.sacchon.util.ResourceHelper;
import org.restlet.data.Status;
import org.restlet.resource.Get;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class PatientFieldResource<T extends PatientField, R> extends AuthResource {

    abstract FieldService<T> getService(Patient p);
    abstract R getRepresentation(T item);

    @Get("json")
    public List<R> getCarbs(){
        int offset = ResourceHelper.parseIntOrDef("offset", 0, this);
        int limit = ResourceHelper.parseIntOrDef("limit", Integer.MAX_VALUE, this);
        int id = ResourceHelper.parseIntOrDef("id", -1, this);
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
        FieldService<T> nSrv = getService(patient.get());
        setService(srv);
        return nSrv.getList(offset,limit).stream().map(this::getRepresentation).collect(Collectors.toList());
    }
}
