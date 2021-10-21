package upc.edu.pe.restcodebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import upc.edu.pe.restcodebackend.domain.model.Consultant;
import upc.edu.pe.restcodebackend.domain.repository.ConsultantRepository;
import upc.edu.pe.restcodebackend.domain.service.ConsultantService;
import upc.edu.pe.restcodebackend.exception.ResourceNotFoundException;
@Service
public class ConsultantServiceImpl implements ConsultantService {
    @Autowired
    private ConsultantRepository consultantRepository;

    @Override
    public Page<Consultant> getAllConsultants(Pageable pageable) {
        return consultantRepository.findAll(pageable);
    }

    @Override
    public Consultant getByEmail(String email) {
        return consultantRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("Consultant","Email",email));
    }


    @Override
    public Consultant getConsultantById(Long consultantId) {
        return consultantRepository.findById(consultantId)
                .orElseThrow(()-> new ResourceNotFoundException("Consultant","Id",consultantId));
    }

    @Override
    public Consultant createConsultant(Consultant consultant) {
        return consultantRepository.save(consultant);
    }

    @Override
    public Consultant updateConsultant(Long consultantId, Consultant consultantRequest) {
        Consultant consultant = consultantRepository.findById(consultantId)
                .orElseThrow(()-> new ResourceNotFoundException("Consultant","Id",consultantId));

        consultant.setUserName(consultantRequest.getUserName());
        consultant.setPassword(consultantRequest.getPassword());
        consultant.setFirstName(consultantRequest.getFirstName());
        consultant.setLastName(consultantRequest.getLastName());
        consultant.setCellphone(consultantRequest.getCellphone());
        consultant.setEmail(consultantRequest.getEmail());
        consultant.setLinkedinLink(consultantRequest.getLinkedinLink());

        return consultantRepository.save(consultant);
    }

    @Override
    public ResponseEntity<?> deleteConsultant(Long consultantId) {
        Consultant consultant = consultantRepository.findById(consultantId)
                .orElseThrow(()-> new ResourceNotFoundException("Consultant","Id",consultantId));
        consultantRepository.delete(consultant);
        return ResponseEntity.ok().build();
    }
}
