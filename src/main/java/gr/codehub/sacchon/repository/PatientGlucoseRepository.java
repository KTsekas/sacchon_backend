package gr.codehub.sacchon.repository;

import gr.codehub.sacchon.model.GlucoseRecord;

import javax.persistence.EntityManager;

public class PatientGlucoseRepository extends Repository<GlucoseRecord,Integer> {

    public PatientGlucoseRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<GlucoseRecord> getEntityClass() {
        return GlucoseRecord.class;
    }

    @Override
    public String getClassName() {
        return GlucoseRecord.class.getName();
    }

}
