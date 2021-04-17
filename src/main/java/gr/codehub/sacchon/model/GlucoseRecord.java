package gr.codehub.sacchon.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class GlucoseRecord implements PatientField {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Patient patient;

    @Column(nullable = false)
    private double glucoseLevel;

    @Column(nullable=false,name="dateAdded")
    private LocalDate date;

    @Column(nullable=false,name="timeMeasured")
    private LocalTime time;
}
