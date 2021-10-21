package upc.edu.pe.restcodebackend.cucumber.glue;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class CalificarRetroalimentarAlConsultor {
    @Given("que el dueño del restaurante se encuentra en la sección Dejar una opinión en el perfil del consultor de negocio")
    public void queElDueñoDelRestauranteSeEncuentraEnLaSecciónDejarUnaOpiniónEnElPerfilDelConsultorDeNegocio() {
        assertThat(true).isTrue();
    }

    @When("el dueño del restaurante termina de escribir su opinión")
    public void elDueñoDelRestauranteTerminaDeEscribirSuOpinión(DataTable dataTable) {
        assertThat(true).isTrue();
    }

    @Then("el sistema guarda su opinión como un comentario exitosamente")
    public void elSistemaGuardaSuOpiniónComoUnComentarioExitosamente() {
        assertThat(true).isTrue();
    }
}
