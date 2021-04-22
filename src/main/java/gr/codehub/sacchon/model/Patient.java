package gr.codehub.sacchon.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@DiscriminatorValue(UserRole.PATIENT)
@NamedQueries({

        @NamedQuery(
                name = "patient.free",
                query = "from Patient p where size(p.carbs) >=30 and size(p.glucoseLevels) >=30 and doctor is null"
        ),
        @NamedQuery(
                name = "patient.inactive",
//                query="from Patient"
                query = "from Patient p where " +
                        "id not in (select p.id from Patient p inner join p.carbs c where c.date between ?1 and ?2 group by p having count(c)>0) "+
                        "and id not in (select p.id from Patient p inner join p.glucoseLevels c where c.date between ?1 and ?2 group by p having count(c)>0)"

        )
})
public class Patient extends User {
    public static final String CONSULTATION_STATUS_NEW = "NEW";
    public static final String CONSULTATION_STATUS_UPDATED = "UPDATED";
    public static final String CONSULTATION_STATUS_READ = "READ";

    @ManyToOne(fetch = FetchType.EAGER)
    private Doctor doctor;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Consultation> consultations;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CarbRecord> carbs;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GlucoseRecord> glucoseLevels;

    private String consultationStatus;

    @Override
    public String toString() {
        return "User{" + super.toString() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != Patient.class)
            return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
