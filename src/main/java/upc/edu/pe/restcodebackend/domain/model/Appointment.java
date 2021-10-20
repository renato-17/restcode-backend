package upc.edu.pe.restcodebackend.domain.model;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    public Date currentDateTime;

    @NotNull
    public Date scheduleDateTime;

    @NotNull
    public String topic;

    @NotNull
    public String meetLink;

    //Relation
    //Consultant
    //Owner
    // Relation one to one with consultancy
}
