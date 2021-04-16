package gr.codehub.sacchon.resources.patient;

import gr.codehub.sacchon.util.JpaUtil;
import gr.codehub.sacchon.model.CarbRecord;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.services.PatientCarbRepository;
import gr.codehub.sacchon.representations.forms.CarbForm;
import org.restlet.data.Status;
import org.restlet.resource.Delete;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;

import java.time.LocalDate;

public class CarbResource extends AuthResource {

    @Post("json")
    public void insert(CarbForm frm){
        if ( frm == null )
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,"This request needs a body");
        PatientCarbRepository repo = new PatientCarbRepository(JpaUtil.getEntityManager());
        CarbRecord record = new CarbRecord();
        record.setPatient((Patient)this.getUser());
        record.setCarbIntake(frm.getCarbIntake());
        LocalDate date = frm.getLocalDate();
        if (date == null )
            throw new ResourceException(Status.CLIENT_ERROR_UNPROCESSABLE_ENTITY,"invalid date format in body");
        record.setDateCreated(date);
        record = repo.save(record);
        if ( record  == null)
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL,"unable to save record in database");
    }
    @Put("json")
    public void update(CarbForm frm){
        if ( frm == null )
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,"This request needs a body");
        PatientCarbRepository repo = new PatientCarbRepository(JpaUtil.getEntityManager());
        if ( frm.getId() == CarbForm.MISSING_ID_VALUE )
            throw new ResourceException(Status.CLIENT_ERROR_UNPROCESSABLE_ENTITY,"Id field missing from form");
        CarbRecord record = repo.read(frm.getId());
        if( record == null )
            throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND,"no record found with this id");
        record.setCarbIntake(frm.getCarbIntake());
        LocalDate date = frm.getLocalDate();
        if (date == null )
            throw new ResourceException(Status.CLIENT_ERROR_UNPROCESSABLE_ENTITY,"invalid date format in body");
        record.setDateCreated(date);
        record = repo.update(record);
        if ( record == null )
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL,"unable to update record in database");
    }
    @Delete("json")
    public void deleteRecord(){
        int id;
        try {
            id = Integer.parseInt(this.getAttribute("id"));
        }
        catch(NumberFormatException ex ){
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,"invalid id attribute");
        }
        PatientCarbRepository repo = new PatientCarbRepository(JpaUtil.getEntityManager());
        if (!repo.delete(id))
            throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND,"unable to delete record");
    }
}