package upc.edu.pe.restcodebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import upc.edu.pe.restcodebackend.domain.model.Consultancy;
import upc.edu.pe.restcodebackend.domain.repository.AppointmentRepository;
import upc.edu.pe.restcodebackend.domain.repository.ConsultancyRepository;
import upc.edu.pe.restcodebackend.domain.service.ConsultancyService;
import upc.edu.pe.restcodebackend.exception.ResourceNotFoundException;
@Service
public class ConsultancyServiceImpl implements ConsultancyService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ConsultancyRepository consultancyRepository;

    @Override
    public Page<Consultancy> getAllConsultancies(Pageable pageable) {
        return consultancyRepository.findAll(pageable);
    }


    @Override
    public Consultancy getConsultancyById(Long consultancyId) {
        return consultancyRepository.findById(consultancyId)
                .orElseThrow(()-> new ResourceNotFoundException("Consultancy","Id",consultancyId));
    }

    @Override
    public Consultancy createConsultancy(Consultancy consultancy, Long appointmentId) {
        return appointmentRepository.findById(appointmentId).map(appointment ->{
            consultancy.setAppointment(appointment);
            return consultancyRepository.save(consultancy);
        }).orElseThrow(()->new ResourceNotFoundException("Appointment","Id",appointmentId));
    }

    @Override
    public Consultancy updateConsultancy(Long consultancyId, Consultancy consultancyRequest) {
        Consultancy consultancy = consultancyRepository.findById(consultancyId)
                .orElseThrow(()-> new ResourceNotFoundException("Consultancy","Id",consultancyId));

        consultancy.setDiagnosis(consultancyRequest.getDiagnosis());
        consultancy.setRecommendation(consultancyRequest.getRecommendation());
        return consultancyRepository.save(consultancy);
    }

    @Override
    public ResponseEntity<?> deleteConsultancy(Long consultancyId) {
        Consultancy consultancy = consultancyRepository.findById(consultancyId)
                .orElseThrow(()-> new ResourceNotFoundException("Consultancy","Id",consultancyId));
        consultancyRepository.delete(consultancy);
        return ResponseEntity.ok().build();
    }
}
