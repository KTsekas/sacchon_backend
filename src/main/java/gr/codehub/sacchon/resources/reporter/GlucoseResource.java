package gr.codehub.sacchon.resources.reporter;

import gr.codehub.sacchon.model.GlucoseRecord;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.representations.patient.GlucoseRepresentation;
import gr.codehub.sacchon.services.FieldService;
import gr.codehub.sacchon.services.GlucoseService;

public class GlucoseResource extends FieldResource<GlucoseRecord, GlucoseRepresentation> {
    @Override
    FieldService<GlucoseRecord> getService(Patient p) {
        return new GlucoseService(p);
    }

    @Override
    GlucoseRepresentation getRepresentation(GlucoseRecord record) {
        return new GlucoseRepresentation(record);
    }
}
