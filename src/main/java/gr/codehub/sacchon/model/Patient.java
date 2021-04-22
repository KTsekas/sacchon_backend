package gr.codehub.sacchon.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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
                query = "from Patient where id in (select p.id from Patient p inner join p.carbs c inner join p.glucoseLevels g " +
                        "where c.date between ?1 and ?2 and " +
                        "g.date between ?1 and ?2 " +
                        "group by p " +
                        "having count(c.id) = 0 and count(g.id) = 0)"
        )
})
public class Patient extends User {
    public static final String CONSULTATION_STATUS_NEW ="NEW";
    public static final String CONSULTATION_STATUS_UPDATED ="UPDATED";
    public static final String CONSULTATION_STATUS_READ ="READ";

    @ManyToOne( fetch = FetchType.EAGER)
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
        if( o.getClass() != Patient.class)
            return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
