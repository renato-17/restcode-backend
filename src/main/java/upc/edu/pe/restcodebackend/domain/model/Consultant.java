package upc.edu.pe.restcodebackend.domain.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("consultant")
public class Consultant extends Profile{
    public String linkedinLink;

    //Relations
    //Assignments
    //Publications
    //Appointments
    //Comments
}
