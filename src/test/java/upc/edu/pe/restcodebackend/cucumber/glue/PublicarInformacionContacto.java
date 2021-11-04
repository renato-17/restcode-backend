package upc.edu.pe.restcodebackend.cucumber.glue;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import upc.edu.pe.restcodebackend.domain.model.Consultant;
import upc.edu.pe.restcodebackend.resource.AppointmentResource;
import upc.edu.pe.restcodebackend.resource.ConsultantResource;
import upc.edu.pe.restcodebackend.resource.save.SaveAppointmentResource;
import upc.edu.pe.restcodebackend.resource.save.SaveConsultancyResource;
import upc.edu.pe.restcodebackend.resource.save.SaveConsultantResource;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PublicarInformacionContacto {
    private List<ConsultantResource> resources = new ArrayList<>();

    @LocalServerPort
    private String port;
    private final RestTemplate restTemplate = new RestTemplate();
    private final String postUrl = "http://localhost:9090/restcode-backend/";

    private ConsultantResource consultant;
    private String url;

    @Given("que el consultor de negocios se encuentra en la seccion Perfil")
    public void queElConsultorDeNegociosSeEncuentraEnLaSeccionPerfil() {
        url = postUrl + "api/users/consultants";
    }

    @When("ingresa su Información de Contacto")
    public void ingresaSuInformaciónDeContacto(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        url = postUrl + "api/appointments?owner=2&consultant=3";

        for (Map<String, String> columns : rows) {
            SaveConsultantResource consultant = new SaveConsultantResource();
            consultant.setUserName(columns.get("userName"));
            consultant.setFirstName(columns.get("firstName"));
            consultant.setLastName(columns.get("lastName"));
            consultant.setCellphone(columns.get("cellphone"));
            consultant.setEmail(columns.get("email"));
            consultant.setPassword(columns.get("password"));
            consultant.setLinkedinLink(columns.get("linkedinLink"));
            ResponseEntity<ConsultantResource> consultantResource = restTemplate.postForEntity(url,consultant,ConsultantResource.class);

            assertThat(consultantResource.getStatusCode()).isEqualTo(HttpStatus.OK);
            resources.add(consultantResource.getBody());
        }
    }

    @Then("su informacion se agrega a su perfil")
    public void suInformacionSeAgregaASuPerfil() {
        assertThat(resources.size()).isNotZero();
    }
}
