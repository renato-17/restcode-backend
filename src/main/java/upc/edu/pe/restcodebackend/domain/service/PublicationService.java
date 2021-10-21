package upc.edu.pe.restcodebackend.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import upc.edu.pe.restcodebackend.domain.model.Publication;

public interface PublicationService {
    Page<Publication> getAllPublications(Pageable pageable);
    Publication getPublicationById(Long publicationId);
    Publication createPublication(Publication publication, Long consultantId);
    Publication updatePublication(Long publicationId, Publication publicationRequest);
    ResponseEntity<?> deletePublication(Long publicationId);
}
