package gr.codehub.sacchon.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue(UserRole.DOCTOR)
public class Doctor extends User {

    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL,fetch =FetchType.LAZY)
    List<Consultation> consultations;

    @OneToMany(mappedBy ="doctor",cascade = CascadeType.ALL,fetch =FetchType.EAGER)
    List<Patient> patient;

    @Override
    public String toString() {
        return "Doctor{" + super.toString() +"}";
    }
}
