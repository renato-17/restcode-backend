package upc.edu.pe.restcodebackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.restcodebackend.domain.model.Owner;
import upc.edu.pe.restcodebackend.domain.service.OwnerService;

import upc.edu.pe.restcodebackend.resource.OwnerResource;
import upc.edu.pe.restcodebackend.resource.save.SaveOwnerResource;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OwnerController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private OwnerService ownerService;

    @Operation(summary = "Get All Owners", description = "Get all owners", tags = {"owners"})
    @GetMapping("api/owners")
    public Page<OwnerResource> getAllOwners(Pageable pageable){
        Page<Owner> ownerPage = ownerService.getAllOwners(pageable);

        List<OwnerResource> resources = ownerPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get Owner By Id", description = "Get Owner By Id", tags = {"owners"})
    @GetMapping("api/owners/{ownerId}")
    public OwnerResource getOwnerById(@PathVariable Long ownerId){
        return convertToResource(ownerService.getOwnerById(ownerId));
    }

    @Operation(summary = "Create Owner", description = "Create a new owner", tags = {"owners"})
    @PostMapping("users/owners")
    public OwnerResource createOwner(@Valid @RequestBody SaveOwnerResource resource){
        Owner owner = convertToEntity(resource);
        return convertToResource(ownerService.createOwner(owner));
    }

    @Operation(summary = "Update Owner", description = "Update a owner", tags = {"owners"})
    @PutMapping("api/owners/{ownerId}")
    public OwnerResource updateOwner(
            @PathVariable Long ownerId,
            @RequestBody @Valid SaveOwnerResource resource){
        Owner owner = convertToEntity(resource);
        return convertToResource(ownerService.updateOwner(ownerId, owner));
    }

    @Operation(summary = "Delete a owner", description = "Delete a owner", tags = {"owners"})
    @DeleteMapping("api/owners/{ownerId}")
    public ResponseEntity<?> deleteOwner(@PathVariable Long ownerId){
        return ownerService.deleteOwner(ownerId);
    }

    private  Owner convertToEntity(SaveOwnerResource resource){return mapper.map(resource,Owner.class);}
    private  OwnerResource convertToResource(Owner entity){return mapper.map(entity,OwnerResource.class);}
}
