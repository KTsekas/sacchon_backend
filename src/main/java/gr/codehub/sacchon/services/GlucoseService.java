package gr.codehub.sacchon.services;

import gr.codehub.sacchon.model.GlucoseRecord;
import gr.codehub.sacchon.model.Patient;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
