package upc.edu.pe.restcodebackend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.pe.restcodebackend.domain.model.Publication;

public interface PublicationRepository extends JpaRepository<Publication,Long> {
}
