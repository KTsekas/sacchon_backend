package gr.codehub.sacchon.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
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
//                query = "select p from Patient p inner join p.carbs c inner join p.glucoseLevels g " +
//                        "where c.date between ?1 and ?2 and " +
//                        "g.date between ?1 and ?2 " +
//                        "group by p " +
//                        "having size(p.carbs) = 0 and size(p.glucoseLevels) = 0"
                query = "from Patient"
        )
})
public class Patient extends User {

    @ManyToOne( fetch = FetchType.EAGER)
    private Doctor doctor;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Consultation> consultations;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CarbRecord> carbs;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GlucoseRecord> glucoseLevels;

    @Override
    public String toString() {
        return "User{" + super.toString() + "}";
    }
}
