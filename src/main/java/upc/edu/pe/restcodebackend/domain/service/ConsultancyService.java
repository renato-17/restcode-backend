package upc.edu.pe.restcodebackend.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import upc.edu.pe.restcodebackend.domain.model.Consultancy;

public interface ConsultancyService {
    Page<Consultancy> getAllConsultancies(Pageable pageable);
    Consultancy getConsultancyById(Long consultancyId);
    Consultancy getConsultancyByAppointmentId(Long appointmentId);
    Consultancy createConsultancy(Consultancy consultancy, Long appointmentId);
    Consultancy updateConsultancy(Long consultancyId, Consultancy consultancyRequest);
    ResponseEntity<?> deleteConsultancy(Long consultancyId);
}
