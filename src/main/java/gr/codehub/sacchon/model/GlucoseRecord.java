package gr.codehub.sacchon.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class GlucoseRecord {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Patient patient;

    @Column(nullable = false)
    private double glucoseLevel;

    @Column(nullable=false)
    private LocalDateTime dateTime;
}
