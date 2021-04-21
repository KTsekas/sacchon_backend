package gr.codehub.sacchon.resources.reporter;

import gr.codehub.sacchon.forms.FieldForm;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.model.PatientField;
import gr.codehub.sacchon.representations.patient.CarbRepresentation;
import gr.codehub.sacchon.resources.AuthResource;
import gr.codehub.sacchon.resources.DateRangeResource;
import gr.codehub.sacchon.services.CarbService;
import gr.codehub.sacchon.services.FieldService;
import gr.codehub.sacchon.services.PatientService;
import gr.codehub.sacchon.util.ResourceHelper;
import org.restlet.data.Status;
import org.restlet.resource.Get;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class FieldResource<T extends PatientField, R> extends DateRangeResource {

    abstract FieldService<T> getService(Patient p);

    abstract R getRepresentation(T record);


    @Get("json")
    public List<R> getCarb(){
        int id = ResourceHelper.parseIntQueryOrFail("id",this);
        int offset = ResourceHelper.parseIntOrDef("offset",0,this);
        int limit = ResourceHelper.parseIntOrDef("limit",0,this);
        PatientService srv = new PatientService();
        Optional<Patient> p = srv.getPatient(id);
        if( p.isEmpty()){
            setStatus(Status.CLIENT_ERROR_NOT_FOUND,"No patient found with that id");
            srv.close();
            return null;
        }
        FieldService<T> fieldSrv = getService(p.get());
        srv.close();
        setService(fieldSrv);
        return fieldSrv.getListRange(getStart(),getEnd(),offset,limit).stream().map(this::getRepresentation).collect(Collectors.toList());
    }
}
