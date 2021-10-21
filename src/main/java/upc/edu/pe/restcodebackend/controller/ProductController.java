package upc.edu.pe.restcodebackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.restcodebackend.domain.model.Product;
import upc.edu.pe.restcodebackend.domain.service.ProductService;

import upc.edu.pe.restcodebackend.resource.ProductResource;
import upc.edu.pe.restcodebackend.resource.save.SaveProductResource;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProductService productService;

    @Operation(summary = "Get All Products", description = "Get all products", tags = {"products"})
    @GetMapping("/products")
    public Page<ProductResource> getAllProducts(Pageable pageable){
        Page<Product> productPage = productService.getAllProducts(pageable);

        List<ProductResource> resources = productPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get Product By Id", description = "Get Product By Id", tags = {"products"})
    @GetMapping("/products/{productId}")
    public ProductResource getProductById(@PathVariable Long productId){
        return convertToResource(productService.getProductById(productId));
    }

    @Operation(summary = "Create Product", description = "Create a new product", tags = {"products"})
    @PostMapping("categories/{categoryId}/products")
    public ProductResource createProduct(@Valid @RequestBody SaveProductResource resource,@PathVariable Long categoryId){
        Product product = convertToEntity(resource);
        return convertToResource(productService.createProduct(product,categoryId));
    }

    @Operation(summary = "Update Product", description = "Update a product", tags = {"products"})
    @PutMapping("/products/{productId}")
    public ProductResource updateProduct(
            @PathVariable Long productId,
            @RequestBody @Valid SaveProductResource resource){
        Product product = convertToEntity(resource);
        return convertToResource(productService.updateProduct(productId, product));
    }

    @Operation(summary = "Delete a product", description = "Delete a product", tags = {"products"})
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId){
        return productService.deleteProduct(productId);
    }

    private  Product convertToEntity(SaveProductResource resource){return mapper.map(resource,Product.class);}
    private  ProductResource convertToResource(Product entity){return mapper.map(entity,ProductResource.class);}
}
