package gr.codehub.sacchon.services;

import gr.codehub.sacchon.model.CarbRecord;
import gr.codehub.sacchon.model.Patient;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
