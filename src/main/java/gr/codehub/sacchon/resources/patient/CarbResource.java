package gr.codehub.sacchon.resources.patient;

import gr.codehub.sacchon.model.CarbRecord;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.representations.forms.CarbForm;
import gr.codehub.sacchon.representations.patient.CarbRepresentation;
import gr.codehub.sacchon.services.CarbService;

public class CarbResource extends FieldResource<CarbRecord,CarbForm,CarbRepresentation> {

    protected CarbService getService() {
        CarbService srv = new CarbService((Patient) getUser());
        setService(srv);
        return srv;
    }

    @Override
    CarbRepresentation getRepresentation(CarbRecord item) {
        return new CarbRepresentation(item);
    }

}