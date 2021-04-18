package gr.codehub.sacchon.resources.doctor;

import gr.codehub.sacchon.model.CarbRecord;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.representations.patient.CarbRepresentation;
import gr.codehub.sacchon.services.CarbService;
import gr.codehub.sacchon.services.FieldService;

public class PatientCarbResource extends PatientFieldResource<CarbRecord,CarbRepresentation> {

    @Override
    FieldService<CarbRecord> getService(Patient p) {
        CarbService srv = new CarbService(p);
        setService(srv);
        return srv;
    }

    @Override
    CarbRepresentation getRepresentation(CarbRecord item) {
        return new CarbRepresentation(item);
    }
}
