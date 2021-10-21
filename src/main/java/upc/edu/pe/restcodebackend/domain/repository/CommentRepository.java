package upc.edu.pe.restcodebackend.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.pe.restcodebackend.domain.model.Category;
import upc.edu.pe.restcodebackend.domain.model.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    Page<Comment> findAllByPublicationId(Long publicationId, Pageable pageable);
}
