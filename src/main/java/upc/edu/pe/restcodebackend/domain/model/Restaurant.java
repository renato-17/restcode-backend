package upc.edu.pe.restcodebackend.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotNull
    public String name;
    @NotNull
    public String address;
    @NotNull
    @Column(unique = true)
    public String cellPhoneNumber;

    //Relations
    // Owner
    // Categories
    // Assignments
    // Sales
}
