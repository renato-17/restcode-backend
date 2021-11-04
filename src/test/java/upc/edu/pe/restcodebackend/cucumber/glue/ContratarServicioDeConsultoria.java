package upc.edu.pe.restcodebackend.cucumber.glue;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.*;


public class ContratarServicioDeConsultoria {
    private static WebDriver webDriver;
    private  String urlPage = "http://localhost:4200/sign-in";

    private static WebElement buttonAsesoria;
    private static WebElement buttonConsultor;



    @Given("el dueño del restaurante se encuentra en la vista principal después de haber iniciado sesión")
    public void elDueñoDelRestauranteSeEncuentraEnLaVistaPrincipalDespuésDeHaberIniciadoSesión(DataTable table) throws InterruptedException {
        String pathDriver = System.getProperty("user.dir") + "\\driver\\chromedriver_95.exe";
        System.setProperty("webdriver.chrome.driver", pathDriver);
        webDriver = new ChromeDriver();
        webDriver.get(urlPage);
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            //Email
            webDriver.findElement(By.id("login-email")).sendKeys(columns.get("Email"));
            //Password
            webDriver.findElement(By.id("login-password")).sendKeys(columns.get("Password"));
            //Button
            webDriver.findElement(By.id("button-login")).click();
        }
        Thread.sleep(2000);
    }
    private static String consultancyId;
    @When("selecciona el botón Asesorías y consultorías")
    public void seleccionaElBotónAsesoríasYConsultorías() {
        consultancyId = "consultancypg";
    }
    @Then("el sistema lo redirecciona a la vista “Asesorías y consultorías\".")
    public void elSistemaLoRedireccionaALaVistaAsesoríasYConsultorías() throws InterruptedException {
       webDriver.findElement(By.cssSelector("#consultancypg")).click();
       Thread.sleep(2000);
    }

    private static String consultancyXPath;
    @Given("el dueño del restaurante quiere programar una cita con un consultor")
    public void elDueñoDelRestauranteQuiereProgramarUnaCitaConUnConsultor() {
        consultancyXPath = "consultancy-3";
    }

    @When("elige un consultor y solicita una cita ingresando los datos que se piden")
    public void eligeUnConsultorYSolicitaUnaCitaIngresandoLosDatosQueSePiden(DataTable table) throws InterruptedException {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        webDriver.findElement(By.id(consultancyXPath)).click();

        for (Map<String, String> columns : rows) {
            //CurrentDate
            List<String> currentDate = Arrays.asList(columns.get("CurrentDateTime").split("T"));

            WebElement currenDateElement  = webDriver.findElement(By.id("add-currentDate"));
            currentDate.forEach((number)->{
                currenDateElement.sendKeys(number);
                currenDateElement.sendKeys(Keys.TAB);
            });

            //ScheduleDate
            List<String> scheduleDateTime = Arrays.asList(columns.get("ScheduleDateTime").split("T"));
            WebElement scheduleDateTimeElement  = webDriver.findElement(By.id("add-scheduleDateTime"));
            scheduleDateTime.forEach((number)->{
                scheduleDateTimeElement.sendKeys(number);
                scheduleDateTimeElement.sendKeys(Keys.TAB);
            });
            //Topic
            webDriver.findElement(By.id("add-topic")).sendKeys(columns.get("Topic"));
            //Link
            webDriver.findElement(By.id("add-meetLink")).sendKeys(columns.get("MeetLink"));
            //Button
            webDriver.findElement(By.id("add-button")).click();
        }
        Thread.sleep(1000);
    }

    @Then("el sistema programa la cita de manera exitosa.")
    public void elSistemaProgramaLaCitaDeManeraExitosa() {
        webDriver.findElement(By.id("correctDialogClose")).click();
    }
}