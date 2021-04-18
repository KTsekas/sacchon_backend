package gr.codehub.sacchon.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue(UserRole.DOCTOR)
@NamedQuery(
        name ="doctor.inactive",
        query="select d from Doctor d inner join d.consultations c " +
                "where c.date between ?1 and ?2 " +
                "group by d " +
                "having size(d.consultations) = 0"
)
public class Doctor extends User {

    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL,fetch =FetchType.LAZY)
    List<Consultation> consultations;

    @OneToMany(mappedBy ="doctor",cascade = CascadeType.ALL,fetch =FetchType.LAZY)
    List<Patient> patient;

    @Override
    public String toString() {
        return "Doctor{" + super.toString() +"}";
    }
}
