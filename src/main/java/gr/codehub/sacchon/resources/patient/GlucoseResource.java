package gr.codehub.sacchon.resources.patient;

import gr.codehub.sacchon.model.GlucoseRecord;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.representations.forms.GlucoseForm;
import gr.codehub.sacchon.representations.patient.GlucoseRepresentation;
import gr.codehub.sacchon.resources.AuthResource;
import gr.codehub.sacchon.services.GlucoseService;
import gr.codehub.sacchon.util.ResourceHelper;
import org.restlet.data.Status;
import org.restlet.resource.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GlucoseResource extends AuthResource {

    private GlucoseService getService(){
        GlucoseService srv = new GlucoseService((Patient)getUser());
        this.setService(srv);
        return srv;
    }

    @Get
    public List<GlucoseRepresentation> getList(){
        int offset = ResourceHelper.parseIntOrDef("offset",0, this);
        int limit = ResourceHelper.parseIntOrDef("limit",Integer.MAX_VALUE,this);
        return getService().getList(offset,limit).stream().map(GlucoseRepresentation::new).collect(Collectors.toList());
    }

    @Post("json")
    public void insert(GlucoseForm frm) {
        if (frm == null)
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "This request needs a body");
        if (getService().post(frm.create()).isEmpty())
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, "unable to save record in database");
    }

    @Put("json")
    public void update(GlucoseForm frm) {
        if (frm == null)
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "This request needs a body");
        if (frm.getId() == GlucoseForm.MISSING_ID_VALUE)
            throw new ResourceException(Status.CLIENT_ERROR_UNPROCESSABLE_ENTITY, "Id field missing from form");
        GlucoseService srv = getService();
        Optional<GlucoseRecord> rec = srv.get(frm.getId());
        if ( rec.isEmpty() )
            throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "no record found with this id");
        if (srv.put(frm.update(rec.get())).isEmpty())
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, "unable to update record in database");
    }

    @Delete("json")
    public void deleteRecord() {
        int id = ResourceHelper.parseIntOrDef("id",-1,this);
        if( id == -1)
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,"invalid id attribute");
        if (!getService().del(id))
            throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "unable to delete record");
    }
}
