package gr.codehub.sacchon.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@DiscriminatorValue(UserRole.DOCTOR)
@NamedQuery(
        name ="doctor.inactive",
//query="from Patient"
        query="from Doctor where id not in (select d.id from Doctor d left join d.consultations c " +
                "where c.date between ?1 and ?2 " +
                "group by d " +
                "having count(c) > 0 )"
)
public class Doctor extends User {

    @Override
    public boolean equals(Object o) {
        if( o.getClass() != Doctor.class)
            return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), patient);
    }

    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL,fetch =FetchType.LAZY)
    List<Consultation> consultations;

    @OneToMany(mappedBy ="doctor",cascade = CascadeType.ALL,fetch =FetchType.LAZY)
    List<Patient> patient;

    @Override
    public String toString() {
        return "Doctor{" + super.toString() +"}";
    }
}
