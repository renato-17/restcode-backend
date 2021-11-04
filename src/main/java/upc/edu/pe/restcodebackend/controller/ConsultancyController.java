package upc.edu.pe.restcodebackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.restcodebackend.domain.model.Consultancy;
import upc.edu.pe.restcodebackend.domain.service.ConsultancyService;

import upc.edu.pe.restcodebackend.resource.ConsultancyResource;
import upc.edu.pe.restcodebackend.resource.save.SaveConsultancyResource;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ConsultancyController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ConsultancyService consultancyService;

    @Operation(summary = "Get All Consultancies", description = "Get all consultancies", tags = {"consultancies"})
    @GetMapping("/consultancies")
    public Page<ConsultancyResource> getAllConsultancies(Pageable pageable){
        Page<Consultancy> consultancyPage = consultancyService.getAllConsultancies(pageable);

        List<ConsultancyResource> resources = consultancyPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get Consultancy By Id", description = "Get Consultancy By Id", tags = {"consultancies"})
    @GetMapping("/consultancies/{consultancyId}")
    public ConsultancyResource getConsultancyById(@PathVariable Long consultancyId){
        return convertToResource(consultancyService.getConsultancyById(consultancyId));
    }

    @Operation(summary = "Get Consultancy By Id", description = "Get Consultancy By Id", tags = {"consultancies"})
    @GetMapping("appointments/{appointmentId}/consultancies")
    public ConsultancyResource getConsultancyByAppointmentId(@PathVariable Long appointmentId){
        return convertToResource(consultancyService.getConsultancyByAppointmentId(appointmentId));
    }

    @Operation(summary = "Create Consultancy", description = "Create a new consultancy", tags = {"consultancies"})
    @PostMapping("appointments/{appointmentId}/consultancies")
    public ConsultancyResource createConsultancy(@Valid @RequestBody SaveConsultancyResource resource,@PathVariable Long appointmentId){
        Consultancy consultancy = convertToEntity(resource);
        return convertToResource(consultancyService.createConsultancy(consultancy,appointmentId));
    }

    @Operation(summary = "Update Consultancy", description = "Update a consultancy", tags = {"consultancies"})
    @PutMapping("/consultancies/{consultancyId}")
    public ConsultancyResource updateConsultancy(
            @PathVariable Long consultancyId,
            @RequestBody @Valid SaveConsultancyResource resource){
        Consultancy consultancy = convertToEntity(resource);
        return convertToResource(consultancyService.updateConsultancy(consultancyId, consultancy));
    }

    @Operation(summary = "Delete a consultancy", description = "Delete a consultancy", tags = {"consultancies"})
    @DeleteMapping("/consultancies/{consultancyId}")
    public ResponseEntity<?> deleteConsultancy(@PathVariable Long consultancyId){
        return consultancyService.deleteConsultancy(consultancyId);
    }

    private  Consultancy convertToEntity(SaveConsultancyResource resource){return mapper.map(resource,Consultancy.class);}
    private  ConsultancyResource convertToResource(Consultancy entity){return mapper.map(entity,ConsultancyResource.class);}
}
