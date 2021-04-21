package gr.codehub.sacchon.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;



@Data
@Entity
@NamedQueries({
        @NamedQuery(
                name = "consult.pending",
                query ="select p from Patient p where id in (select p.id from Patient p left join p.consultations c " +
                "where p.doctor is null or p.doctor is ?1 group by p "+
                "having MAX(c.expirationDate) < ?2 or (COUNT(c.expirationDate) = 0 and size(p.carbs) >= 30 and size(p.glucoseLevels) >=30))"
        ),
        @NamedQuery(
                name = "consult.pending.all",
                query ="select p.id,p.firstName,p.lastName,MAX(c.expirationDate),MAX(cb.date),MAX(g.date) from Patient p left join p.consultations c " +
                        "inner join p.carbs cb inner join p.glucoseLevels g "+
                "group by p "+
                "having MAX(c.expirationDate) < ?2 or (COUNT(c.expirationDate) = 0 and size(p.carbs) >= 30 and size(p.glucoseLevels) >=30)"
//                query ="select p.firstName,p.lastName,MAX(c.expirationDate),MAX(cb.date),MAX(g.date) from Patient p " +
//                        "inner join p.carbs cb " +
//                        "inner join p.consultations c " +
//                        "inner join p.glucoseLevels g where p.id in " +
//                        "(select p.id from Patient p left join p.consultations c " +
//                "group by p "+
//                "having MAX(c.expirationDate) < ?2 or (COUNT(c.expirationDate) = 0 and size(p.carbs) >= 30 and size(p.glucoseLevels) >=30)) group by p.id"

        )
})
public class Consultation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctor;

    @Column(nullable = false)
    private String consultationText;

    @Column(name="dateAdded")
    private LocalDate date;
    private LocalDate expirationDate;
}
