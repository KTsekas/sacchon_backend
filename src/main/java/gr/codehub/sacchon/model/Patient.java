package gr.codehub.sacchon.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue(UserRole.PATIENT)
public class Patient extends User{

    @ManyToOne(cascade = CascadeType.ALL,fetch =FetchType.EAGER)
    private Doctor doctor;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Consultation> consultations;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CarbRecord> carbs;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private List<GlucoseRecord> glucoseLevels;

    @Override
    public String toString() {
        return "User{" + super.toString() +"}";
    }
}
