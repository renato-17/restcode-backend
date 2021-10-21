package upc.edu.pe.restcodebackend.resource.save;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveSaleDetailResource {
    @NotNull
    @NotBlank
    @Size(max = 100)
    private String description;
    @NotNull
    private Integer quantity;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
