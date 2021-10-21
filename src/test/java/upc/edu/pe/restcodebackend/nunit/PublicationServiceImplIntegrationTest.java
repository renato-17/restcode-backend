package upc.edu.pe.restcodebackend.nunit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import upc.edu.pe.restcodebackend.domain.model.Consultant;
import upc.edu.pe.restcodebackend.domain.model.Publication;
import upc.edu.pe.restcodebackend.domain.repository.ConsultantRepository;
import upc.edu.pe.restcodebackend.domain.repository.PublicationRepository;
import upc.edu.pe.restcodebackend.domain.service.PublicationService;
import upc.edu.pe.restcodebackend.exception.ResourceNotFoundException;
import upc.edu.pe.restcodebackend.service.PublicationServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class PublicationServiceImplIntegrationTest {
    @MockBean
    private PublicationRepository publicationRepository;

    @MockBean
    private ConsultantRepository consultantRepository;

    @Autowired
    private PublicationService publicationService;

    @TestConfiguration
    static class PublicationServiceImplTestConfiguration {
        @Bean
        public PublicationService publicationService() {
            return new PublicationServiceImpl();
        }
    }
    @Test
    @DisplayName("When PublicationById With Valid Id Then Returns Publication")
    public void whenGetPublicationByIdWithValidIdThenReturnsPublication() {
        //Arrange
        Long id = 1L;
        String description = "Prueba";
        Publication publication = new Publication();
        publication.setDescription(description);
        publication.setId(id);

        when(publicationRepository.findById(id))
                .thenReturn(Optional.of(publication));
        //Act
        Publication foundPublication = publicationService.getPublicationById(id);

        //Assert
        assertThat(foundPublication.getDescription()).isEqualTo(description);
    }

    @Test
    @DisplayName("When PublicationById With Not Valid Id Then Returns Not Found Exception")
    public void whenGetPublicationByIdWithNotValidIdThenReturnsNotFoundException() {
        //Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(publicationRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Publication", "Id", id);

        //Act
        Throwable exception = catchThrowable(() -> {
            Publication foundPublication = publicationService.getPublicationById(id);
        });

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("Create Publication With Valid ConsultantId Then Returns New Publication")
    public void createPublicationWithValidConsultantIdThenReturnNewPublication() {
        //Arrange
        Long id = 1L;
        String username = "user";
        Consultant consultant = new Consultant();
        consultant.setId(id);
        consultant.setUserName(username);

        when(consultantRepository.findById(id))
                .thenReturn(Optional.of(consultant));

        String description = "Prueba";
        Publication publication = new Publication();
        publication.setDescription(description);
        publication.setConsultant(consultant);

        when(publicationRepository.save(publication))
                .thenReturn(publication);

        //Act
        Publication foundPublication = publicationService.createPublication(publication,id);
        //Assert
        assertThat(foundPublication.getDescription()).isEqualTo(description);
    }
}
