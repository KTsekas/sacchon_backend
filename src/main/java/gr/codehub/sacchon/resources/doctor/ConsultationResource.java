package gr.codehub.sacchon.resources.doctor;

import gr.codehub.sacchon.forms.ConsultationForm;
import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.representations.ConsultationRepresentation;
import gr.codehub.sacchon.representations.PaginationListRepresentation;
import gr.codehub.sacchon.resources.AuthResource;
import gr.codehub.sacchon.services.ConsultationService;
import gr.codehub.sacchon.util.PaginationTuple;
import gr.codehub.sacchon.util.ResourceHelper;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;

import java.util.Optional;
import java.util.stream.Collectors;

public class ConsultationResource extends AuthResource {

    @Get("json")
    public PaginationListRepresentation<ConsultationRepresentation> getConsultations() {
        int id = ResourceHelper.parseIntAttributeOrFail("id", this);
        Doctor doc = (Doctor) getUser();
        Optional<Patient> patient = doc.getPatient().stream().filter(p -> p.getId() == id).findFirst();
        if (patient.isEmpty()) {
            setStatus(Status.CLIENT_ERROR_NOT_FOUND, "No patient associated with this doctor found");
            return null;
        }
        int offset = ResourceHelper.parseIntOrDef("offset", 0, this);
        int limit = ResourceHelper.parseIntOrDef("limit", Integer.MAX_VALUE, this);
        ConsultationService srv = new ConsultationService();
        PaginationTuple<Consultation> items = srv.get(patient.get(), offset, limit);
        return new PaginationListRepresentation<>(offset, items.getMaxItems(),
                items.getItems().stream().map(ConsultationRepresentation::new).collect(Collectors.toList()));
    }

    @Post("json")
    public void submitConsult(ConsultationForm frm) {
        if (frm == null) {
            setStatus(Status.CLIENT_ERROR_BAD_REQUEST, "This request needs a body");
            return;
        }
        Consultation consultation = frm.create();
        ConsultationService srv = new ConsultationService();
        setService(srv);
        if (srv.save(consultation, (Doctor) getUser(), frm.getId()).isEmpty())
            setStatus(Status.SERVER_ERROR_INTERNAL, "Unable to save in database");
    }

    @Put("json")
    public void updateConsult(ConsultationForm frm) {
        if (frm == null) {
            setStatus(Status.CLIENT_ERROR_BAD_REQUEST, "This request needs a body");
            return;
        }
        if (frm.getId() == ConsultationForm.MISSING_VALUE) {
            setStatus(Status.CLIENT_ERROR_BAD_REQUEST, "Missing id from body");
            return;
        }
        ConsultationService srv = new ConsultationService();
        Optional<Consultation> consultation = srv.get(frm.getId());
        if ( consultation.isEmpty()){
            setStatus(Status.CLIENT_ERROR_NOT_FOUND,"No consultation found to update");
            return;
        }
        if ( srv.update(frm.update(consultation.get())).isEmpty() )
            setStatus(Status.SERVER_ERROR_INTERNAL, "Unable to save in database");
    }
}
