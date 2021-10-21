package upc.edu.pe.restcodebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import upc.edu.pe.restcodebackend.domain.model.Appointment;
import upc.edu.pe.restcodebackend.domain.model.Consultant;
import upc.edu.pe.restcodebackend.domain.model.Owner;
import upc.edu.pe.restcodebackend.domain.model.Publication;
import upc.edu.pe.restcodebackend.domain.repository.AppointmentRepository;
import upc.edu.pe.restcodebackend.domain.repository.ConsultantRepository;
import upc.edu.pe.restcodebackend.domain.repository.OwnerRepository;
import upc.edu.pe.restcodebackend.domain.service.AppointmentService;
import upc.edu.pe.restcodebackend.exception.ResourceNotFoundException;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private ConsultantRepository consultantRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Page<Appointment> getAllAppointments(Pageable pageable) {
        return appointmentRepository.findAll(pageable);
    }

    @Override
    public Appointment getAppointmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(()-> new ResourceNotFoundException("Appointment","Id",appointmentId));
    }

    @Override
    public Appointment createAppointment(Appointment appointment, Long ownerId, Long consultantId) {
        Owner owner =  ownerRepository.findById(ownerId)
                .orElseThrow(()->new ResourceNotFoundException("Owner","Id",ownerId));
        Consultant consultant =  consultantRepository.findById(consultantId)
                .orElseThrow(()->new ResourceNotFoundException("Consultant","Id",consultantId));

        appointment.setOwner(owner);
        appointment.setConsultant(consultant);
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Long appointmentId, Appointment appointmentRequest) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(()-> new ResourceNotFoundException("Appointment","Id",appointmentId));
        appointment.setCurrentDateTime(appointmentRequest.getCurrentDateTime());
        appointment.setScheduleDateTime(appointmentRequest.getScheduleDateTime());
        appointment.setTopic(appointmentRequest.getTopic());
        appointment.setMeetLink(appointmentRequest.getMeetLink());
        appointment.setConsultant(appointmentRequest.getConsultant());
        appointment.setOwner(appointmentRequest.getOwner());
        return appointmentRepository.save(appointment);
    }

    @Override
    public ResponseEntity<?> deleteAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(()-> new ResourceNotFoundException("Appointment","Id",appointmentId));
        appointmentRepository.delete(appointment);
        return ResponseEntity.ok().build();
    }
}
