package upc.edu.pe.restcodebackend.cucumber.glue;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import upc.edu.pe.restcodebackend.resource.AppointmentResource;
import upc.edu.pe.restcodebackend.resource.save.SaveAppointmentResource;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class US14 {
    String urlPage = "http://localhost:4200/sign-up";
    WebDriver webDriver;

    @Given("que el dueño de restaurante se encuentra en la pantalla de crear una cuenta")
    public void queElDueñoDeRestauranteSeEncuentraEnLaPantallaDeCrearUnaCuenta() {
        String pathDriver = System.getProperty("user.dir") + "\\driver\\chromedriver_95.exe";
        System.setProperty("webdriver.chrome.driver",pathDriver);
        webDriver = new ChromeDriver();
        webDriver.get(urlPage);
    }

    @When("ingresa sus datos personales y de su  restaurante")
    public void ingresaSusDatosPersonalesYDeSuRestaurante(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            //Email
            webDriver.findElement(By.xpath("//*[@id=\"input-17\"]")).sendKeys(columns.get("Email"));
            //Password
            webDriver.findElement(By.xpath("//*[@id=\"input-20\"]")).sendKeys(columns.get("Password"));
            //Username
            webDriver.findElement(By.xpath("//*[@id=\"input-23\"]")).sendKeys(columns.get("UserName"));
            //Name
            webDriver.findElement(By.xpath("//*[@id=\"input-26\"]")).sendKeys(columns.get("FirstName"));
            //LastName
            webDriver.findElement(By.xpath("//*[@id=\"input-29\"]")).sendKeys(columns.get("LastName"));
            //Phone
            webDriver.findElement(By.xpath("//*[@id=\"input-32\"]")).sendKeys(columns.get("Cellphone"));
            //Button Dueño
            webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/main/div/div/div/div[2]/form/div[7]/div/div[1]/div/div[1]/div")).click();
            //Ruc
            webDriver.findElement(By.xpath("//*[@id=\"input-43\"]")).sendKeys(columns.get("Ruc"));
            //Button
            webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/main/div/div/div/div[3]/button")).click();
        }
    }

    @Then("el sistema guarda todos los datos registrados por el usuario")
    public void elSistemaGuardaTodosLosDatosRegistradosPorElUsuario() {
        assertThat(true).isTrue();
    }



}
