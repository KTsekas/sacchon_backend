package gr.codehub.sacchon.resources.patient;

import gr.codehub.sacchon.model.CarbRecord;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.representations.forms.CarbForm;
import gr.codehub.sacchon.representations.patient.CarbRepresentation;
import gr.codehub.sacchon.resources.AuthResource;
import gr.codehub.sacchon.services.CarbService;
import gr.codehub.sacchon.util.ResourceHelper;
import org.restlet.data.Status;
import org.restlet.resource.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CarbResource extends AuthResource {

    private CarbService getService(){
        CarbService srv= new CarbService((Patient)getUser());
        setService(srv);
        return srv;
    }

    @Get("json")
    public List<CarbRepresentation> getList(){
        int offset = ResourceHelper.parseIntOrDef("offset",0, this);
        int limit = ResourceHelper.parseIntOrDef("limit",Integer.MAX_VALUE,this);
        return getService().getList(offset,limit).stream().map(CarbRepresentation::new).collect(Collectors.toList());
    }

    @Post("json")
    public void insert(CarbForm frm){
        if ( frm == null )
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,"This request needs a body");
        CarbRecord rec = frm.create();
        if( rec.getDateCreated() == null )
            throw new ResourceException(Status.CLIENT_ERROR_UNPROCESSABLE_ENTITY,"invalid date format in body");
        if (getService().post(rec).isEmpty())
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL,"unable to save record in database");
    }
    @Put("json")
    public void update(CarbForm frm){
        if ( frm == null )
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,"This request needs a body");
        if ( frm.getId() == CarbForm.MISSING_ID_VALUE )
            throw new ResourceException(Status.CLIENT_ERROR_UNPROCESSABLE_ENTITY,"Id field missing from form");
        if ( frm.getLocalDate().isEmpty() )
            throw new ResourceException(Status.CLIENT_ERROR_UNPROCESSABLE_ENTITY,"invalid date format in body");
        CarbService srv = getService();
        Optional<CarbRecord> rec = srv.get(frm.getId());
        if(rec.isEmpty())
            throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND,"no record found with this id");
        frm.update(rec.get());
        if ( srv.put(rec.get()).isEmpty())
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL,"unable to update record in database");
    }
    @Delete("json")
    public void deleteRecord(){
        int id = ResourceHelper.parseIntOrDef("id",-1,this);
        if( id == -1)
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,"invalid id attribute");
        if (!getService().del(id))
            throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND,"unable to delete record");
    }
}