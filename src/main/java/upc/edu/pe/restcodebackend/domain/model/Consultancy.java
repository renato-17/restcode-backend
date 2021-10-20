package upc.edu.pe.restcodebackend.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "consultancies")
public class Consultancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotNull
    public String diagnosis;
    @NotNull
    public String recommendation;

    //Relations
    //Appointments
}
