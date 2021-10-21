package upc.edu.pe.restcodebackend.nunit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import upc.edu.pe.restcodebackend.domain.model.Assignment;
import upc.edu.pe.restcodebackend.domain.model.Consultant;
import upc.edu.pe.restcodebackend.domain.model.Restaurant;
import upc.edu.pe.restcodebackend.domain.repository.AssignmentRepository;
import upc.edu.pe.restcodebackend.domain.repository.ConsultantRepository;
import upc.edu.pe.restcodebackend.domain.repository.RestaurantRepository;
import upc.edu.pe.restcodebackend.domain.service.AssignmentService;
import upc.edu.pe.restcodebackend.exception.ResourceNotFoundException;
import upc.edu.pe.restcodebackend.service.AssignmentServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AssignmentServiceImplIntegrationTest {
    @MockBean
    private AssignmentRepository assignmentRepository;

    @MockBean
    private RestaurantRepository restaurantRepository;

    @MockBean
    private ConsultantRepository consultantRepository;

    @Autowired
    private AssignmentService assignmentService;

    @TestConfiguration
    static class AssignmentServiceImplTestConfiguration {
        @Bean
        public AssignmentService assignmentService() {
            return new AssignmentServiceImpl();
        }
    }

    @Test
    @DisplayName("When AssignmentById With Valid Id Then Returns Assignment")
    public void whenGetAssignmentByIdWithValidIdThenReturnsAssignment() {
        //Arrange
        Long id = 1L;
        Assignment assignment = new Assignment();
        assignment.setState(true);
        assignment.setId(id);

        when(assignmentRepository.findById(id))
                .thenReturn(Optional.of(assignment));
        //Act
        Assignment foundAssignment = assignmentService.getAssignmentById(id);

        //Assert
        assertThat(foundAssignment.getState()).isTrue();
    }

    @Test
    @DisplayName("When AssignmentById With Not Valid Id Then Returns Not Found Exception")
    public void whenGetAssignmentByIdWithNotValidIdThenReturnsNotFoundException() {
        //Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(assignmentRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Assignment", "Id", id);

        //Act
        Throwable exception = catchThrowable(() -> {
            Assignment foundAssignment = assignmentService.getAssignmentById(id);
        });

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("Create Assignment With Valid OwnerId Then Returns New Assignment")
    public void createAssignmentWithValidOwnerIdThenReturnNewAssignment() {
        //Arrange
        Long restaurantId = 1L;
        String restaurantName = "restaurante";
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);
        restaurant.setName(restaurantName);

        Long consultantId = 1L;
        String consultantUsername = "Username consultant";
        Consultant consultant = new Consultant();
        consultant.setId(consultantId);
        consultant.setUserName(consultantUsername);

        when(consultantRepository.findById(consultantId))
                .thenReturn(Optional.of(consultant));

        when(restaurantRepository.findById(restaurantId))
                .thenReturn(Optional.of(restaurant));

        Assignment assignment = new Assignment();
        assignment.setRestaurant(restaurant);
        assignment.setConsultant(consultant);

        when(assignmentRepository.save(assignment))
                .thenReturn(assignment);

        //Act
        Assignment foundAssignment = assignmentService.createAssignment(assignment,consultantId,restaurantId);
        //Assert
        assertThat(foundAssignment.getRestaurant()).isEqualTo(restaurant);
        assertThat(foundAssignment.getConsultant()).isEqualTo(consultant);
    }
}
