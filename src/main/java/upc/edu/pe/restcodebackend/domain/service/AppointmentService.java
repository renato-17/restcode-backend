package upc.edu.pe.restcodebackend.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import upc.edu.pe.restcodebackend.domain.model.Appointment;

public interface AppointmentService {
    Page<Appointment> getAllAppointments(Pageable pageable);
    Page<Appointment> getAllAppointmentsByUserId(Pageable pageable, Long ownerId, Long consultantId);
    Appointment getAppointmentById(Long appointmentId);
    Appointment  createAppointment(Appointment appointment, Long ownerId, Long consultantId);
    Appointment updateAppointment(Long appointmentId, Appointment appointmentRequest);
    ResponseEntity<?> deleteAppointment(Long appointmentId);
}
