package upc.edu.pe.restcodebackend.resource.save;

import javax.validation.constraints.NotNull;

public class SaveAssignmentResource {
    @NotNull
    private Boolean state;

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

}
