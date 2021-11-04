package upc.edu.pe.restcodebackend.cucumber.glue;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import upc.edu.pe.restcodebackend.resource.AppointmentResource;
import upc.edu.pe.restcodebackend.resource.AuthUser;
import upc.edu.pe.restcodebackend.resource.save.AuthRequest;
import upc.edu.pe.restcodebackend.resource.save.SaveAppointmentResource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ContratarServicioDeConsultoria {
    List<AppointmentResource> appointmentResources = new ArrayList<>();
    List<SaveAppointmentResource> saveAppointmentResources = new ArrayList<>();;
    @LocalServerPort
    private String port;
    private final RestTemplate restTemplate = new RestTemplate();
    private final String postUrl = "http://localhost:9090/restcode-backend/";

    private String url;
    public HttpHeaders headers = new HttpHeaders();;
    private HttpEntity<SaveAppointmentResource> entity;

    private String token = "Bearer ";

    @Given("que el dueño del restaurante quiere programar una cita con un consultor")
    public void queElDueñoDelRestauranteQuiereProgramarUnaCitaConUnConsultor() {
        url = postUrl + "users/authentication";
        AuthRequest request = new AuthRequest();
        request.setEmail("renato@gmail.com");
        request.setPassword("renato");

        AuthUser user = restTemplate.postForObject(url,request,AuthUser.class);
        assert user != null;

        token += user.getToken();
        headers.set("Authorization", token);

        assertThat(user).isNotNull();
    }

    @When("solicita una cita ingresando los datos que se piden")
    public void solicitaUnaCitaIngresandoLosDatosQueSePiden(DataTable table) throws ParseException {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        url = postUrl + "api/appointments?owner=2&consultant=3";

        for (Map<String, String> columns : rows) {
            SaveAppointmentResource appointment = new SaveAppointmentResource();
            appointment.setCurrentDateTime(new SimpleDateFormat("dd/MM/yyyy").parse(columns.get("CurrentDateTime")));
            appointment.setCurrentDateTime(new SimpleDateFormat("dd/MM/yyyy").parse(columns.get("ScheduleDateTime")));
            appointment.setTopic(columns.get("Topic"));
            appointment.setTopic(columns.get("MeetLink"));
            saveAppointmentResources.add(appointment);
            entity = new HttpEntity<>(appointment,headers);
            ResponseEntity<AppointmentResource> appointmentResource = restTemplate.exchange(url,HttpMethod.POST,entity,AppointmentResource.class);

            assertThat(appointmentResource.getStatusCode()).isEqualTo(HttpStatus.OK);
            appointmentResources.add(appointmentResource.getBody());
        }
    }

    @Then("el sistema programa la cita de manera exitosa.")
    public void elSistemaProgramaLaCitaDeManeraExitosa() {
        assertThat(appointmentResources.size()).isNotZero();
    }
}
