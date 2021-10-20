package upc.edu.pe.restcodebackend.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sale_details")
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Lob
    @NotNull
    public String description;
    @NotNull
    public int Quantity;

    //Relations
    // Product
    // Sale
}
