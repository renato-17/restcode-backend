package upc.edu.pe.restcodebackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.restcodebackend.domain.model.Appointment;
import upc.edu.pe.restcodebackend.domain.service.AppointmentService;
import upc.edu.pe.restcodebackend.resource.AppointmentResource;
import upc.edu.pe.restcodebackend.resource.save.SaveAppointmentResource;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AppointmentController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AppointmentService appointmentService;

    @Operation(summary = "Get All Appointments", description = "Get all appointments", tags = {"appointments"})
    @GetMapping("/appointments")
    public Page<AppointmentResource> getAllAppointments(Pageable pageable){
        Page<Appointment> appointmentPage = appointmentService.getAllAppointments(pageable);

        List<AppointmentResource> resources = appointmentPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get Appointment By Id", description = "Get Appointment By Id", tags = {"appointments"})
    @GetMapping("/appointments/{appointmentId}")
    public AppointmentResource getAppointmentById(@PathVariable Long appointmentId){
        return convertToResource(appointmentService.getAppointmentById(appointmentId));
    }

    @Operation(summary = "Create Appointment", description = "Create a new appointment", tags = {"appointments"})
    @PostMapping("/appointments")
    public AppointmentResource createAppointment(@Valid @RequestBody SaveAppointmentResource resource,
                                                 @RequestParam("owner") Long ownerId,
                                                 @RequestParam("consultant") Long consultantId){
        Appointment appointment = convertToEntity(resource);
        return convertToResource(appointmentService.createAppointment(appointment,ownerId,consultantId));
    }

    @Operation(summary = "Update Appointment", description = "Update a appointment", tags = {"appointments"})
    @PutMapping("/appointments/{appointmentId}")
    public AppointmentResource updateAppointment(
            @PathVariable Long appointmentId,
            @RequestBody @Valid SaveAppointmentResource resource){
        Appointment appointment = convertToEntity(resource);
        return convertToResource(appointmentService.updateAppointment(appointmentId, appointment));
    }

    @Operation(summary = "Delete a appointment", description = "Delete a appointment", tags = {"appointments"})
    @DeleteMapping("/appointments/{appointmentId}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long appointmentId){
        return appointmentService.deleteAppointment(appointmentId);
    }

    private  Appointment convertToEntity(SaveAppointmentResource resource){return mapper.map(resource,Appointment.class);}
    private  AppointmentResource convertToResource(Appointment entity){return mapper.map(entity,AppointmentResource.class);}
}
