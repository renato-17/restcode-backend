package upc.edu.pe.restcodebackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.restcodebackend.domain.model.Sale;
import upc.edu.pe.restcodebackend.domain.service.SaleService;

import upc.edu.pe.restcodebackend.resource.SaleResource;
import upc.edu.pe.restcodebackend.resource.save.SaveSaleResource;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SaleController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SaleService saleService;

    @Operation(summary = "Get All Sales", description = "Get all sales", tags = {"sales"})
    @GetMapping("/sales")
    public Page<SaleResource> getAllSales(Pageable pageable){
        Page<Sale> salePage = saleService.getAllSales(pageable);

        List<SaleResource> resources = salePage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get Sale By Id", description = "Get Sale By Id", tags = {"sales"})
    @GetMapping("/sales/{saleId}")
    public SaleResource getSaleById(@PathVariable Long saleId){
        return convertToResource(saleService.getSaleById(saleId));
    }

    @Operation(summary = "Create Sale", description = "Create a new sale", tags = {"sales"})
    @PostMapping("restaurants/{restaurantId}/sales")
    public SaleResource createSale(@Valid @RequestBody SaveSaleResource resource,@PathVariable Long restaurantId){
        Sale sale = convertToEntity(resource);
        return convertToResource(saleService.createSale(sale,restaurantId));
    }

    @Operation(summary = "Update Sale", description = "Update a sale", tags = {"sales"})
    @PutMapping("/sales/{saleId}")
    public SaleResource updateSale(
            @PathVariable Long saleId,
            @RequestBody @Valid SaveSaleResource resource){
        Sale sale = convertToEntity(resource);
        return convertToResource(saleService.updateSale(saleId, sale));
    }

    @Operation(summary = "Delete a sale", description = "Delete a sale", tags = {"sales"})
    @DeleteMapping("/sales/{saleId}")
    public ResponseEntity<?> deleteSale(@PathVariable Long saleId){
        return saleService.deleteSale(saleId);
    }

    private  Sale convertToEntity(SaveSaleResource resource){return mapper.map(resource,Sale.class);}
    private  SaleResource convertToResource(Sale entity){return mapper.map(entity,SaleResource.class);}
}
