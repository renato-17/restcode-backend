package upc.edu.pe.restcodebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import upc.edu.pe.restcodebackend.domain.model.Assignment;
import upc.edu.pe.restcodebackend.domain.model.Consultant;
import upc.edu.pe.restcodebackend.domain.model.Owner;
import upc.edu.pe.restcodebackend.domain.model.Restaurant;
import upc.edu.pe.restcodebackend.domain.repository.AssignmentRepository;
import upc.edu.pe.restcodebackend.domain.repository.ConsultantRepository;
import upc.edu.pe.restcodebackend.domain.repository.RestaurantRepository;
import upc.edu.pe.restcodebackend.domain.service.AssignmentService;
import upc.edu.pe.restcodebackend.exception.ResourceNotFoundException;
@Service
public class AssignmentServiceImpl implements AssignmentService {
    @Autowired
    private ConsultantRepository consultantRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;

    @Override
    public Page<Assignment> getAllAssignments(Pageable pageable) {
        return assignmentRepository.findAll(pageable);
    }

    @Override
    public Assignment getAssignmentById(Long assignmentId) {
        return assignmentRepository.findById(assignmentId)
                .orElseThrow(()-> new ResourceNotFoundException("Assignment","Id",assignmentId));
    }

    @Override
    public Assignment createAssignment(Assignment assignment, Long consultantId, Long restaurantId) {
        Consultant consultant =  consultantRepository.findById(consultantId)
                .orElseThrow(()->new ResourceNotFoundException("Consultant","Id",consultantId));
        Restaurant restaurant =  restaurantRepository.findById(restaurantId)
                .orElseThrow(()->new ResourceNotFoundException("Restaurant","Id",restaurantId));
        assignment.setConsultant(consultant);
        assignment.setRestaurant(restaurant);
        return assignmentRepository.save(assignment);
    }

    @Override
    public Assignment updateAssignment(Long assignmentId, Assignment assignmentRequest) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(()-> new ResourceNotFoundException("Assignment","Id",assignmentId));

        assignment.setState(assignmentRequest.getState());
        assignment.setConsultant(assignmentRequest.getConsultant());
        assignment.setRestaurant(assignmentRequest.getRestaurant());
        return assignmentRepository.save(assignment);
    }

    @Override
    public ResponseEntity<?> deleteAssignment(Long assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(()-> new ResourceNotFoundException("Assignment","Id",assignmentId));
        assignmentRepository.delete(assignment);
        return ResponseEntity.ok().build();
    }
}
