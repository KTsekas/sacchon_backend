package gr.codehub.sacchon.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class CarbRecord {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Patient patient;
    private double carbIntake;

    private LocalDate dateCreated;
}
