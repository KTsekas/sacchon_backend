package gr.codehub.sacchon.resources.patient;

import gr.codehub.sacchon.model.GlucoseRecord;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.forms.GlucoseForm;
import gr.codehub.sacchon.representations.patient.GlucoseRepresentation;
import gr.codehub.sacchon.services.GlucoseService;

public class GlucoseResource extends FieldResource<GlucoseRecord,GlucoseForm,GlucoseRepresentation> {

    protected GlucoseService getService() {
        GlucoseService srv = new GlucoseService((Patient) getUser());
        this.setService(srv);
        return srv;
    }

    @Override
    GlucoseRepresentation getRepresentation(GlucoseRecord item) {
        return new GlucoseRepresentation(item);
    }
}
