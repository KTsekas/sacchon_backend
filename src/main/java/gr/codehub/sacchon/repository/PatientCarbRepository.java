package gr.codehub.sacchon.repository;

import gr.codehub.sacchon.model.CarbRecord;

import javax.persistence.EntityManager;

public class PatientCarbRepository extends Repository<CarbRecord,Integer> {

    public PatientCarbRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<CarbRecord> getEntityClass() {
        return CarbRecord.class;
    }

    @Override
    public String getClassName() {
        return CarbRecord.class.getName();
    }
}
