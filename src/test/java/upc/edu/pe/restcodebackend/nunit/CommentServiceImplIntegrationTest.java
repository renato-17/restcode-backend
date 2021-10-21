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
import upc.edu.pe.restcodebackend.domain.model.Comment;
import upc.edu.pe.restcodebackend.domain.repository.CommentRepository;
import upc.edu.pe.restcodebackend.domain.repository.ConsultantRepository;
import upc.edu.pe.restcodebackend.domain.repository.OwnerRepository;
import upc.edu.pe.restcodebackend.domain.repository.PublicationRepository;
import upc.edu.pe.restcodebackend.domain.service.CommentService;
import upc.edu.pe.restcodebackend.exception.ResourceNotFoundException;
import upc.edu.pe.restcodebackend.service.CommentServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CommentServiceImplIntegrationTest {
    @MockBean
    private PublicationRepository publicationRepository;

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private OwnerRepository ownerRepository;

    @MockBean
    private ConsultantRepository consultantRepository;

    @Autowired
    private CommentService commentService;

    @TestConfiguration
    static class CommentServiceImplTestConfiguration {
        @Bean
        public CommentService commentService() {
            return new CommentServiceImpl();
        }
    }
    
    @Test
    @DisplayName("When CommentById With Valid Id Then Returns Comment")
    public void whenGetCommentByIdWithValidIdThenReturnsComment() {
        //Arrange
        Long id = 1L;
        String description = "Comentario de Prueba";
        Comment comment = new Comment();
        comment.setDescription(description);
        comment.setId(id);

        when(commentRepository.findById(id))
                .thenReturn(Optional.of(comment));
        //Act
        Comment foundComment = commentService.getCommentById(id);

        //Assert
        assertThat(foundComment.getDescription()).isEqualTo(description);
    }

    @Test
    @DisplayName("When CommentById With Not Valid Id Then Returns Not Found Exception")
    public void whenGetCommentByIdWithNotValidIdThenReturnsNotFoundException() {
        //Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(commentRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Comment", "Id", id);

        //Act
        Throwable exception = catchThrowable(() -> {
            Comment foundComment = commentService.getCommentById(id);
        });

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("Create Comment With Valid PublicationId Then Returns New Comment")
    public void createCommentWithValidPublicationIdThenReturnNewComment() {
        //Arrange
        Long id = 1L;
        String description = "Prueba publication";
        Publication publication = new Publication();
        publication.setId(id);
        publication.setDescription(description);

        Long consultantId = 1L;
        String username = "user";
        Consultant consultant = new Consultant();
        consultant.setId(consultantId);
        consultant.setUserName(username);

        when(consultantRepository.findById(id))
                .thenReturn(Optional.of(consultant));

        when(publicationRepository.findById(id))
                .thenReturn(Optional.of(publication));

        String descriptionComment = "Prueba";
        Comment comment = new Comment();
        comment.setDescription(descriptionComment);
        comment.setPublication(publication);

        when(commentRepository.save(comment))
                .thenReturn(comment);

        //Act
        Comment foundComment = commentService.createComment(comment,id,null,consultantId);
        //Assert
        assertThat(foundComment.getDescription()).isEqualTo(descriptionComment);
    }
}
