package upc.edu.pe.restcodebackend.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import upc.edu.pe.restcodebackend.domain.model.Consultant;


public interface ConsultantService {
    Page<Consultant> getAllConsultants(Pageable pageable);
    Consultant getByEmail(String email);
    Consultant getConsultantById(Long consultantId);
    Consultant createConsultant(Consultant consultant);
    Consultant updateConsultant(Long consultantId, Consultant consultantRequest);
    ResponseEntity<?> deleteConsultant(Long consultantId);
}
