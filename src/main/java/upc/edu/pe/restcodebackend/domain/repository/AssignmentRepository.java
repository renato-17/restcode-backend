package upc.edu.pe.restcodebackend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.pe.restcodebackend.domain.model.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment,Long> {
}
