package upc.edu.pe.restcodebackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.restcodebackend.domain.model.Consultant;
import upc.edu.pe.restcodebackend.domain.service.ConsultantService;

import upc.edu.pe.restcodebackend.resource.ConsultantResource;
import upc.edu.pe.restcodebackend.resource.save.SaveConsultantResource;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ConsultantController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ConsultantService consultantService;

    @Operation(summary = "Get All Consultants", description = "Get all consultants", tags = {"consultants"})
    @GetMapping("/consultants")
    public Page<ConsultantResource> getAllConsultants(Pageable pageable){
        Page<Consultant> consultantPage = consultantService.getAllConsultants(pageable);

        List<ConsultantResource> resources = consultantPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get Consultant By Id", description = "Get Consultant By Id", tags = {"consultants"})
    @GetMapping("/consultants/{consultantId}")
    public ConsultantResource getConsultantById(@PathVariable Long consultantId){
        return convertToResource(consultantService.getConsultantById(consultantId));
    }

    @Operation(summary = "Create Consultant", description = "Create a new consultant", tags = {"consultants"})
    @PostMapping("/consultants")
    public ConsultantResource createConsultant(@Valid @RequestBody SaveConsultantResource resource){
        Consultant consultant = convertToEntity(resource);
        return convertToResource(consultantService.createConsultant(consultant));
    }

    @Operation(summary = "Update Consultant", description = "Update a consultant", tags = {"consultants"})
    @PutMapping("/consultants/{consultantId}")
    public ConsultantResource updateConsultant(
            @PathVariable Long consultantId,
            @RequestBody @Valid SaveConsultantResource resource){
        Consultant consultant = convertToEntity(resource);
        return convertToResource(consultantService.updateConsultant(consultantId, consultant));
    }

    @Operation(summary = "Delete a consultant", description = "Delete a consultant", tags = {"consultants"})
    @DeleteMapping("/consultants/{consultantId}")
    public ResponseEntity<?> deleteConsultant(@PathVariable Long consultantId){
        return consultantService.deleteConsultant(consultantId);
    }

    private  Consultant convertToEntity(SaveConsultantResource resource){return mapper.map(resource,Consultant.class);}
    private  ConsultantResource convertToResource(Consultant entity){return mapper.map(entity,ConsultantResource.class);}
}
