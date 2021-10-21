package upc.edu.pe.restcodebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import upc.edu.pe.restcodebackend.domain.model.Publication;
import upc.edu.pe.restcodebackend.domain.repository.ConsultantRepository;
import upc.edu.pe.restcodebackend.domain.repository.PublicationRepository;
import upc.edu.pe.restcodebackend.domain.service.PublicationService;
import upc.edu.pe.restcodebackend.exception.ResourceNotFoundException;
@Service
public class PublicationServiceImpl implements PublicationService {
    @Autowired
    private ConsultantRepository consultantRepository;
    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public Page<Publication> getAllPublications(Pageable pageable) {
        return publicationRepository.findAll(pageable);
    }

    @Override
    public Publication getPublicationById(Long publicationId) {
        return publicationRepository.findById(publicationId)
                .orElseThrow(()-> new ResourceNotFoundException("Publication","Id",publicationId));
    }

    @Override
    public Publication createPublication(Publication publication, Long consultantId) {
        return consultantRepository.findById(consultantId).map(consultant ->{
            publication.setConsultant(consultant);
            return publicationRepository.save(publication);
        }).orElseThrow(()->new ResourceNotFoundException("Consultant","Id",consultantId));
    }

    @Override
    public Publication updatePublication(Long publicationId, Publication publicationRequest) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(()-> new ResourceNotFoundException("Publication","Id",publicationId));

        publication.setDescription(publicationRequest.getDescription());
        publication.setPublishedDate(publicationRequest.getPublishedDate());

        return publicationRepository.save(publication);
    }

    @Override
    public ResponseEntity<?> deletePublication(Long publicationId) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(()-> new ResourceNotFoundException("Publication","Id",publicationId));
        publicationRepository.delete(publication);
        return ResponseEntity.ok().build();
    }
}
