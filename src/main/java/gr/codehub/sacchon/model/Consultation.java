package gr.codehub.sacchon.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
public class Consultation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Doctor doctor;

    @Column(nullable = false)
    private String consultationText;

    @Column(name="dateAdded")
    private LocalDate date;
}
