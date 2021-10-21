package upc.edu.pe.restcodebackend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.pe.restcodebackend.domain.model.Consultancy;

public interface ConsultancyRepository extends JpaRepository<Consultancy,Long> {
}
