package gr.codehub.sacchon.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@NamedQueries({
        @NamedQuery(
                name = "consult.pending",
                query = "select c from Doctor d inner join d.consultations c where c.doctor is ?1 group by c.patient having max(c.date) = c.date"
        ),
        @NamedQuery(
                name = "consult.pending.all",
                query = "select c from Doctor d inner join d.consultations c group by c.patient having max(c.date) = c.date"
        )
})
public class Consultation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    private Doctor doctor;

    @Column(nullable = false)
    private String consultationText;

    @Column(name="dateAdded")
    private LocalDate date;
}
