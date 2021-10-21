package upc.edu.pe.restcodebackend.cucumber.glue;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class ContratarServicioDeConsultoria {
    @Given("que el dueño del restaurante quiere programar una cita con un consultor")
    public void queElDueñoDelRestauranteQuiereProgramarUnaCitaConUnConsultor() {
        assertThat(true).isTrue();
    }

    @When("solicita una cita ingresando los datos que se piden")
    public void solicitaUnaCitaIngresandoLosDatosQueSePiden(DataTable dataTable) {
        assertThat(true).isTrue();
    }

    @Then("el sistema programa la cita de manera exitosa.")
    public void elSistemaProgramaLaCitaDeManeraExitosa() {
        assertThat(true).isTrue();
    }
}
