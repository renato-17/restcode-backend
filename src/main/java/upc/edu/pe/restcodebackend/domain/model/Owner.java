package upc.edu.pe.restcodebackend.domain.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("owner")
public class Owner  extends  Profile{
    public String ruc;

    //Relations
    //Restaurants
    //Appointments
    //Comments
}
