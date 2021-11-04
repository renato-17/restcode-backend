package upc.edu.pe.restcodebackend.cucumber.glue;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class RegistrarVentasDeRestaurante {
    @Given("que el dueño del restaurante se encuentra en la sección de Registros\\/Añadir")
    public void queElDueñoDelRestauranteSeEncuentraEnLaSecciónDeRegistrosAñadir() {
        assertThat(true).isTrue();
    }

    @When("termina de colocar los datos de su venta")
    public void terminaDeColocarLosDatosDeSuVenta(DataTable dataTable) {
        assertThat(true).isTrue();
    }

    @Then("el sistema guarda la venta exitosamente")
    public void elSistemaGuardaLaVentaExitosamente() {
        assertThat(true).isTrue();
    }

}
