package upc.edu.pe.restcodebackend.nunit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import upc.edu.pe.restcodebackend.domain.model.Appointment;
import upc.edu.pe.restcodebackend.domain.model.Consultant;
import upc.edu.pe.restcodebackend.domain.model.Owner;
import upc.edu.pe.restcodebackend.domain.repository.AppointmentRepository;
import upc.edu.pe.restcodebackend.domain.repository.ConsultantRepository;
import upc.edu.pe.restcodebackend.domain.repository.OwnerRepository;
import upc.edu.pe.restcodebackend.domain.service.AppointmentService;
import upc.edu.pe.restcodebackend.exception.ResourceNotFoundException;
import upc.edu.pe.restcodebackend.service.AppointmentServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AppointmentServiceImplIntegrationTest {
    @MockBean
    private AppointmentRepository appointmentRepository;

    @MockBean
    private OwnerRepository ownerRepository;

    @MockBean
    private ConsultantRepository consultantRepository;

    @Autowired
    private AppointmentService appointmentService;

    @TestConfiguration
    static class AppointmentServiceImplTestConfiguration {
        @Bean
        public AppointmentService appointmentService() {
            return new AppointmentServiceImpl();
        }
    }

    @Test
    @DisplayName("When AppointmentById With Valid Id Then Returns Appointment")
    public void whenGetAppointmentByIdWithValidIdThenReturnsAppointment() {
        //Arrange
        Long id = 1L;
        String link = "Link de Prueba";
        Appointment appointment = new Appointment();
        appointment.setMeetLink(link);
        appointment.setId(id);

        when(appointmentRepository.findById(id))
                .thenReturn(Optional.of(appointment));
        //Act
        Appointment foundAppointment = appointmentService.getAppointmentById(id);

        //Assert
        assertThat(foundAppointment.getMeetLink()).isEqualTo(link);
    }

    @Test
    @DisplayName("When AppointmentById With Not Valid Id Then Returns Not Found Exception")
    public void whenGetAppointmentByIdWithNotValidIdThenReturnsNotFoundException() {
        //Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(appointmentRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Appointment", "Id", id);

        //Act
        Throwable exception = catchThrowable(() -> {
            Appointment foundAppointment = appointmentService.getAppointmentById(id);
        });

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("Create Appointment With Valid OwnerId Then Returns New Appointment")
    public void createAppointmentWithValidOwnerIdThenReturnNewAppointment() {
        //Arrange
        Long ownerId = 1L;
        String ownerUsername = "username owner";
        Owner owner = new Owner();
        owner.setId(ownerId);
        owner.setUserName(ownerUsername);

        Long consultantId = 1L;
        String consultantUsername = "Username consultant";
        Consultant consultant = new Consultant();
        consultant.setId(consultantId);
        consultant.setUserName(consultantUsername);

        when(consultantRepository.findById(consultantId))
                .thenReturn(Optional.of(consultant));

        when(ownerRepository.findById(ownerId))
                .thenReturn(Optional.of(owner));

        String link = "Prueba Link";
        Appointment appointment = new Appointment();
        appointment.setMeetLink(link);
        appointment.setOwner(owner);
        appointment.setConsultant(consultant);

        when(appointmentRepository.save(appointment))
                .thenReturn(appointment);

        //Act
        Appointment foundAppointment = appointmentService.createAppointment(appointment,ownerId,consultantId);
        //Assert
        assertThat(foundAppointment.getOwner()).isEqualTo(owner);
        assertThat(foundAppointment.getConsultant()).isEqualTo(consultant);
    }
}
