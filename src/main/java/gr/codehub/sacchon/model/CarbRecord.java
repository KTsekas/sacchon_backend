package gr.codehub.sacchon.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class CarbRecord implements PatientField {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;
    private double carbIntake;

    @Column(name="dateCreated")
    private LocalDate date;
}
