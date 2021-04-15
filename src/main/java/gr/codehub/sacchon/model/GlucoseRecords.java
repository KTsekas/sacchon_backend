package gr.codehub.sacchon.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class GlucoseRecords {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,optional = false)
    private Patient patient;

    @Column(nullable = false)
    private double glucoseLevel;

    @Column(nullable=false)
    private LocalDate date;
}
