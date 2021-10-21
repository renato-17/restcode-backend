package upc.edu.pe.restcodebackend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.pe.restcodebackend.domain.model.Consultant;

import java.util.Optional;

public interface ConsultantRepository extends JpaRepository<Consultant,Long> {
    Optional<Consultant> findByEmail(String email);
}
