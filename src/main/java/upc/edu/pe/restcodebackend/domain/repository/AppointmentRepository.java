package upc.edu.pe.restcodebackend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.pe.restcodebackend.domain.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
}
