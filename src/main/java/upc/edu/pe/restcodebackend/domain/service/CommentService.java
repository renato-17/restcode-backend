package upc.edu.pe.restcodebackend.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import upc.edu.pe.restcodebackend.domain.model.Comment;

public interface CommentService {
    Page<Comment> getAllComments(Pageable pageable);
    Page<Comment> getAllByPublicationId(Long publicationId, Pageable pageable);
    Comment getCommentById(Long commentId);
    Comment createComment(Comment comment, Long publicationId, Long ownerId, Long consultantId);
    Comment updateComment(Long commentId, Comment commentRequest);
    ResponseEntity<?> deleteComment(Long commentId);
}
