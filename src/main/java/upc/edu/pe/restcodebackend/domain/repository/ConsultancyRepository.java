package upc.edu.pe.restcodebackend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.pe.restcodebackend.domain.model.Consultancy;

import java.util.Optional;

public interface ConsultancyRepository extends JpaRepository<Consultancy,Long> {
    Optional<Consultancy> getConsultancyByAppointmentId(Long appointmentId);
}
