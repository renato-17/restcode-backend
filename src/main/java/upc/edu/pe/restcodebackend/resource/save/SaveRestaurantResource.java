package upc.edu.pe.restcodebackend.resource.save;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveRestaurantResource {
    @NotNull
    @NotBlank
    @Size(max = 60)
    private String name;
    @NotNull
    @NotBlank
    @Size(max = 100)
    public String address;
    @NotNull
    @NotBlank
    @Size(max = 12)
    private String cellPhoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

}
