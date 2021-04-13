package gr.codehub.sacchon.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="Users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="role",discriminatorType = DiscriminatorType.INTEGER)
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;

    @Column(name="role",insertable = false,updatable = false)
    private int role;

    @Column(unique = true,nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;


}
