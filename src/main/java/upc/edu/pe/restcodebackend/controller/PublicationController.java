package upc.edu.pe.restcodebackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.restcodebackend.domain.model.Publication;
import upc.edu.pe.restcodebackend.domain.service.PublicationService;

import upc.edu.pe.restcodebackend.resource.PublicationResource;
import upc.edu.pe.restcodebackend.resource.save.SavePublicationResource;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PublicationController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PublicationService publicationService;

    @Operation(summary = "Get All Publications", description = "Get all publications", tags = {"publications"})
    @GetMapping("/publications")
    public Page<PublicationResource> getAllPublications(Pageable pageable){
        Page<Publication> publicationPage = publicationService.getAllPublications(pageable);

        List<PublicationResource> resources = publicationPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get Publication By Id", description = "Get Publication By Id", tags = {"publications"})
    @GetMapping("/publications/{publicationId}")
    public PublicationResource getPublicationById(@PathVariable Long publicationId){
        return convertToResource(publicationService.getPublicationById(publicationId));
    }

    @Operation(summary = "Create Publication", description = "Create a new publication", tags = {"publications"})
    @PostMapping("/consultants/{consultantId}/publications")
    public PublicationResource createPublication(@Valid @RequestBody SavePublicationResource resource,@PathVariable Long consultantId){
        Publication publication = convertToEntity(resource);
        return convertToResource(publicationService.createPublication(publication,consultantId));
    }

    @Operation(summary = "Update Publication", description = "Update a publication", tags = {"publications"})
    @PutMapping("/publications/{publicationId}")
    public PublicationResource updatePublication(
            @PathVariable Long publicationId,
            @RequestBody @Valid SavePublicationResource resource){
        Publication publication = convertToEntity(resource);
        return convertToResource(publicationService.updatePublication(publicationId, publication));
    }

    @Operation(summary = "Delete a publication", description = "Delete a publication", tags = {"publications"})
    @DeleteMapping("/publications/{publicationId}")
    public ResponseEntity<?> deletePublication(@PathVariable Long publicationId){
        return publicationService.deletePublication(publicationId);
    }

    private  Publication convertToEntity(SavePublicationResource resource){return mapper.map(resource,Publication.class);}
    private  PublicationResource convertToResource(Publication entity){return mapper.map(entity,PublicationResource.class);}
}
