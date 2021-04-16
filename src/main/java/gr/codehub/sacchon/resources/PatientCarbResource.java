package gr.codehub.sacchon.resources;

import gr.codehub.sacchon.jpautil.JpaUtil;
import gr.codehub.sacchon.model.CarbRecord;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.repository.PatientCarbRepository;
import gr.codehub.sacchon.representations.forms.PatientCarbFormRepresentation;
import org.restlet.data.Status;
import org.restlet.resource.Delete;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;

import java.time.LocalDate;

public class PatientCarbResource extends AuthResource {


    @Post("json")
    public void insert(PatientCarbFormRepresentation repr){
        if ( repr == null )
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,"This request needs a body");
        PatientCarbRepository repo = new PatientCarbRepository(JpaUtil.getEntityManager());

        CarbRecord record = new CarbRecord();
        record.setPatient((Patient)this.getUser());
        record.setCarbIntake(repr.getCarbIntake());
        LocalDate date = repr.getLocalDate();
        if (date == null )
            throw new ResourceException(Status.CLIENT_ERROR_UNPROCESSABLE_ENTITY,"invalid date format in body");
        record.setDate(date);
        record = repo.save(record);
        if ( record  == null)
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL,"unable to save record in database");
    }
    @Put("json")
    public void update(PatientCarbFormRepresentation repr){
        if ( repr == null )
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,"This request needs a body");
        PatientCarbRepository repo = new PatientCarbRepository(JpaUtil.getEntityManager());
        if ( repr.getId() == -1 )
            throw new ResourceException(Status.CLIENT_ERROR_UNPROCESSABLE_ENTITY,"Id field missing from form");
        CarbRecord record = repo.read(repr.getId());
        if( record == null )
            throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND,"no record found with this id");
        record.setCarbIntake(repr.getCarbIntake());
        LocalDate date = repr.getLocalDate();
        if (date == null )
            throw new ResourceException(Status.CLIENT_ERROR_UNPROCESSABLE_ENTITY,"invalid date format in body");
        record.setDate(date);
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
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,"invalid or not id provided");
        }
        PatientCarbRepository repo = new PatientCarbRepository(JpaUtil.getEntityManager());
        if (!repo.delete(id))
            throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND,"unable to delete record");
    }
}