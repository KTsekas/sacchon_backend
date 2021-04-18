package gr.codehub.sacchon.services;

import gr.codehub.sacchon.model.CarbRecord;
import gr.codehub.sacchon.model.Patient;

public class CarbService extends FieldService<CarbRecord> {

    @Override
    String getName() {
        return CarbRecord.class.getName();
    }

    @Override
    Class<CarbRecord> getRClass() {
        return CarbRecord.class;
    }

    @Override
    String getAverageAggregator() {
        return "carbIntake";
    }

    public CarbService(Patient patient) {
        super(patient);
    }
}
