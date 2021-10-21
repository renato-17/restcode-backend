package upc.edu.pe.restcodebackend.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import upc.edu.pe.restcodebackend.domain.model.Assignment;

public interface AssignmentService {
    Page<Assignment> getAllAssignments(Pageable pageable);
    Assignment getAssignmentById(Long assignmentId);
    Assignment createAssignment(Assignment assignment, Long consultantId, Long restaurantId);
    Assignment updateAssignment(Long assignmentId, Assignment assignmentRequest);
    ResponseEntity<?> deleteAssignment(Long assignmentId);
}
