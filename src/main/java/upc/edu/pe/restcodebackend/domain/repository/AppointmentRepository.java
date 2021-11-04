package upc.edu.pe.restcodebackend.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.pe.restcodebackend.domain.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    Page<Appointment> findAllByOwnerId(Pageable pageable,Long ownerId);
    Page<Appointment> findAllByConsultantId(Pageable pageable,Long consultantId);
}
