package upc.edu.pe.restcodebackend.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotNull
    public Date publishedDate;
    @NotNull
    public String description;

    // Relations
//    Publication
//    Owner
    // Consultant
}
