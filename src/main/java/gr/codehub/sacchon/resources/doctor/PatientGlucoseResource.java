package gr.codehub.sacchon.resources.doctor;

import gr.codehub.sacchon.model.CarbRecord;
import gr.codehub.sacchon.model.GlucoseRecord;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.representations.patient.CarbRepresentation;
import gr.codehub.sacchon.representations.patient.GlucoseRepresentation;
import gr.codehub.sacchon.services.CarbService;
import gr.codehub.sacchon.services.FieldService;
import gr.codehub.sacchon.services.GlucoseService;

public class PatientGlucoseResource extends PatientFieldResource<GlucoseRecord, GlucoseRepresentation> {

    @Override
    FieldService<GlucoseRecord> getService() {
        GlucoseService srv = new GlucoseService((Patient)getUser());
        setService(srv);
        return srv;
    }

    @Override
    GlucoseRepresentation getRepresentation(GlucoseRecord item) {
        return new GlucoseRepresentation(item);
    }

}
