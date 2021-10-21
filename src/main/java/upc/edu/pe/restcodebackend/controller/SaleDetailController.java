package upc.edu.pe.restcodebackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.restcodebackend.domain.model.SaleDetail;
import upc.edu.pe.restcodebackend.domain.service.SaleDetailService;

import upc.edu.pe.restcodebackend.resource.SaleDetailResource;
import upc.edu.pe.restcodebackend.resource.save.SaveSaleDetailResource;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SaleDetailController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SaleDetailService saleDetailService;

    @Operation(summary = "Get All SaleDetails", description = "Get all sales details", tags = {"sales details"})
    @GetMapping("/sale-details")
    public Page<SaleDetailResource> getAllSaleDetails(Pageable pageable){
        Page<SaleDetail> saleDetailPage = saleDetailService.getAllSaleDetails(pageable);

        List<SaleDetailResource> resources = saleDetailPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get SaleDetail By Id", description = "Get SaleDetail By Id", tags = {"sales details"})
    @GetMapping("/sale-details/{saleDetailId}")
    public SaleDetailResource getSaleDetailById(@PathVariable Long saleDetailId){
        return convertToResource(saleDetailService.getSaleDetailById(saleDetailId));
    }

    @Operation(summary = "Create SaleDetail", description = "Create a new saleDetail", tags = {"sales details"})
    @PostMapping("/sale-details")
    public SaleDetailResource createSaleDetail(@Valid @RequestBody SaveSaleDetailResource resource,
                                               @RequestParam("product") Long productId,
                                               @RequestParam("sale") Long saleId){
        SaleDetail saleDetail = convertToEntity(resource);
        return convertToResource(saleDetailService.createSaleDetail(saleDetail,productId,saleId));
    }

    @Operation(summary = "Update SaleDetail", description = "Update a saleDetail", tags = {"sales details"})
    @PutMapping("/sale-details/{saleDetailId}")
    public SaleDetailResource updateSaleDetail(
            @PathVariable Long saleDetailId,
            @RequestBody @Valid SaveSaleDetailResource resource){
        SaleDetail saleDetail = convertToEntity(resource);
        return convertToResource(saleDetailService.updateSaleDetail(saleDetailId, saleDetail));
    }

    @Operation(summary = "Delete a saleDetail", description = "Delete a saleDetail", tags = {"sales details"})
    @DeleteMapping("/sale-details/{saleDetailId}")
    public ResponseEntity<?> deleteSaleDetail(@PathVariable Long saleDetailId){
        return saleDetailService.deleteSaleDetail(saleDetailId);
    }

    private  SaleDetail convertToEntity(SaveSaleDetailResource resource){return mapper.map(resource,SaleDetail.class);}
    private  SaleDetailResource convertToResource(SaleDetail entity){return mapper.map(entity,SaleDetailResource.class);}
}
