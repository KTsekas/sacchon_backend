package gr.codehub.sacchon.resources;

import gr.codehub.sacchon.jpautil.JpaUtil;
import gr.codehub.sacchon.model.GlucoseRecord;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.repository.PatientGlucoseRepository;
import gr.codehub.sacchon.representations.forms.GlucoseFormRepresentation;
import org.restlet.data.Status;
import org.restlet.resource.Delete;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;

public class PatientGlucoseResource extends AuthResource {

    @Post("json")
    public void insert(GlucoseFormRepresentation repr){
        PatientGlucoseRepository repo = new PatientGlucoseRepository(JpaUtil.getEntityManager());

        GlucoseRecord record = new GlucoseRecord();
        record.setPatient((Patient)this.getUser());
        record.setGlucoseLevel(repr.getGlucoseLevel());
        record.setDate(repr.getLocalDateTime());

        record = repo.save(record);
        if ( record  == null) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL,"unable to save record in database");
        }
    }
    @Put("json")
    public void update(GlucoseFormRepresentation repr){
        PatientGlucoseRepository repo = new PatientGlucoseRepository(JpaUtil.getEntityManager());
        if ( repr.getId().isEmpty() )
            throw new ResourceException(Status.CLIENT_ERROR_UNPROCESSABLE_ENTITY,"Id field missing from form");
        GlucoseRecord record = repo.read(repr.getId().get());
        if( record == null )
            throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND,"no record found with this id");
        record.setGlucoseLevel(repr.getGlucoseLevel());
        record.setDate(repr.getLocalDateTime());
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
        PatientGlucoseRepository repo = new PatientGlucoseRepository(JpaUtil.getEntityManager());
        if (!repo.delete(id))
           throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND,"unable to delete record");
    }
}
