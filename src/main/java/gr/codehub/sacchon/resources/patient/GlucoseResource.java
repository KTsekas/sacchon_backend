package gr.codehub.sacchon.resources.patient;

import gr.codehub.sacchon.model.GlucoseRecord;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.representations.forms.GlucoseForm;
import gr.codehub.sacchon.services.GlucoseService;
import gr.codehub.sacchon.util.ResourceHelper;
import org.restlet.data.Status;
import org.restlet.resource.Delete;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;

import java.util.Optional;

public class GlucoseResource extends AuthResource {


    @Post("json")
    public void insert(GlucoseForm frm) {
        if (frm == null)
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "This request needs a body");
        GlucoseService srv = new GlucoseService((Patient) getUser());
        if (srv.post(frm.create()).isEmpty())
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, "unable to save record in database");
    }

    @Put("json")
    public void update(GlucoseForm frm) {
        if (frm == null)
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "This request needs a body");
        if (frm.getId() == GlucoseForm.MISSING_ID_VALUE)
            throw new ResourceException(Status.CLIENT_ERROR_UNPROCESSABLE_ENTITY, "Id field missing from form");
        GlucoseService srv = new GlucoseService((Patient) getUser());
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
        GlucoseService srv = new GlucoseService((Patient) getUser());
        if (!srv.del(id))
            throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "unable to delete record");
    }
}
