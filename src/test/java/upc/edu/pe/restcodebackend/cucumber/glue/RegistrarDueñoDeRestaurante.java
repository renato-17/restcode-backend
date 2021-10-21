package upc.edu.pe.restcodebackend.cucumber.glue;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class RegistrarDueñoDeRestaurante {
    @Given("que el dueño de restaurante se encuentra en la pantalla de crear una cuenta")
    public void queElDueñoDeRestauranteSeEncuentraEnLaPantallaDeCrearUnaCuenta() {
        assertThat(true).isTrue();
    }

    @When("ingresa sus datos personales")
    public void ingresaSusDatosPersonales(DataTable dataTable) {
        assertThat(true).isTrue();
    }

    @Then("el sistema guarda todos los datos registrados por el usuario")
    public void elSistemaGuardaTodosLosDatosRegistradosPorElUsuario() {
        assertThat(true).isTrue();
    }

}
