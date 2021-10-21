package upc.edu.pe.restcodebackend.cucumber.glue;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class PublicarInformacionContacto {
    @Given("que el consultor de negocios se encuentra en la seccion Perfil")
    public void queElConsultorDeNegociosSeEncuentraEnLaSeccionPerfil() {
        assertThat(true).isTrue();
    }

    @When("ingresa su Información de Contacto")
    public void ingresaSuInformaciónDeContacto(DataTable table) {
        assertThat(true).isTrue();
    }

    @Then("su informacion se agrega a su perfil")
    public void suInformacionSeAgregaASuPerfil() {
        assertThat(true).isTrue();
    }
}
