package gr.codehub.sacchon.resources.reporter;

import gr.codehub.sacchon.model.CarbRecord;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.representations.patient.CarbRepresentation;
import gr.codehub.sacchon.services.CarbService;
import gr.codehub.sacchon.services.FieldService;

public class CarbResource extends FieldResource<CarbRecord,CarbRepresentation> {

    @Override
    FieldService<CarbRecord> getService(Patient p) {
        return new CarbService(p);
    }

    @Override
    CarbRepresentation getRepresentation(CarbRecord record) {
        return new CarbRepresentation(record);
    }
}
