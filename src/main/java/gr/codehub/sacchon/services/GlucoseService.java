package gr.codehub.sacchon.services;

import gr.codehub.sacchon.model.GlucoseRecord;
import gr.codehub.sacchon.model.Patient;


public class GlucoseService extends FieldService<GlucoseRecord>{

    public GlucoseService(Patient patient) {
        super(patient);
    }

    @Override
    String getName() {
        return GlucoseRecord.class.getName();
    }

    @Override
    Class<GlucoseRecord> getRClass() {
        return GlucoseRecord.class;
    }

    @Override
    String getAverageAggregator() {
        return "glucoseLevel";
    }


}
