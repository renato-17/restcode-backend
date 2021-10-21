package upc.edu.pe.restcodebackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.restcodebackend.domain.model.Assignment;
import upc.edu.pe.restcodebackend.domain.service.AssignmentService;
import upc.edu.pe.restcodebackend.resource.AssignmentResource;
import upc.edu.pe.restcodebackend.resource.save.SaveAssignmentResource;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AssignmentController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AssignmentService assignmentService;

    @Operation(summary = "Get All Assignments", description = "Get all assignments", tags = {"assignments"})
    @GetMapping("/assignments")
    public Page<AssignmentResource> getAllAssignments(Pageable pageable){
        Page<Assignment> assignmentPage = assignmentService.getAllAssignments(pageable);

        List<AssignmentResource> resources = assignmentPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get Assignment By Id", description = "Get Assignment By Id", tags = {"assignments"})
    @GetMapping("/assignments/{assignmentId}")
    public AssignmentResource getAssignmentById(@PathVariable Long assignmentId){
        return convertToResource(assignmentService.getAssignmentById(assignmentId));
    }

    @Operation(summary = "Create Assignment", description = "Create a new assignment", tags = {"assignments"})
    @PostMapping("/assignments")
    public AssignmentResource createAssignment(@Valid @RequestBody SaveAssignmentResource resource,
                                               @RequestParam("consultant") Long consultantId,
                                               @RequestParam("restaurant") Long restaurantId){
        Assignment assignment = convertToEntity(resource);
        return convertToResource(assignmentService.createAssignment(assignment, consultantId,restaurantId));
    }

    @Operation(summary = "Update Assignment", description = "Update a assignment", tags = {"assignments"})
    @PutMapping("/assignments/{assignmentId}")
    public AssignmentResource updateAssignment(
            @PathVariable Long assignmentId,
            @RequestBody @Valid SaveAssignmentResource resource){
        Assignment assignment = convertToEntity(resource);
        return convertToResource(assignmentService.updateAssignment(assignmentId, assignment));
    }

    @Operation(summary = "Delete a assignment", description = "Delete a assignment", tags = {"assignments"})
    @DeleteMapping("/assignments/{assignmentId}")
    public ResponseEntity<?> deleteAssignment(@PathVariable Long assignmentId){
        return assignmentService.deleteAssignment(assignmentId);
    }

    private  Assignment convertToEntity(SaveAssignmentResource resource){return mapper.map(resource,Assignment.class);}
    private  AssignmentResource convertToResource(Assignment entity){return mapper.map(entity,AssignmentResource.class);}
}
