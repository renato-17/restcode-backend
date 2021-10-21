package upc.edu.pe.restcodebackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.restcodebackend.domain.model.Category;
import upc.edu.pe.restcodebackend.domain.service.CategoryService;
import upc.edu.pe.restcodebackend.resource.CategoryResource;
import upc.edu.pe.restcodebackend.resource.save.SaveCategoryResource;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "Get All Categories", description = "Get all categories", tags = {"categories"})
    @GetMapping("/categories")
    public Page<CategoryResource> getAllCategories(Pageable pageable){
        Page<Category> categoryPage = categoryService.getAllCategories(pageable);

        List<CategoryResource> resources = categoryPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get Category By Id", description = "Get Category By Id", tags = {"categories"})
    @GetMapping("/categories/{categoryId}")
    public CategoryResource getCategoryById(@PathVariable Long categoryId){
        return convertToResource(categoryService.getCategoryById(categoryId));
    }

    @Operation(summary = "Create Category", description = "Create a new category", tags = {"categories"})
    @PostMapping("restaurant/{restaurantId}/categories")
    public CategoryResource createCategory(@Valid @RequestBody SaveCategoryResource resource,@PathVariable Long restaurantId){
        Category category = convertToEntity(resource);
        return convertToResource(categoryService.createCategory(category,restaurantId));
    }

    @Operation(summary = "Update Category", description = "Update a category", tags = {"categories"})
    @PutMapping("/categories/{categoryId}")
    public CategoryResource updateCategory(
            @PathVariable Long categoryId,
            @RequestBody @Valid SaveCategoryResource resource){
        Category category = convertToEntity(resource);
        return convertToResource(categoryService.updateCategory(categoryId, category));
    }

    @Operation(summary = "Delete a category", description = "Delete a category", tags = {"categories"})
    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId){
        return categoryService.deleteCategory(categoryId);
    }

    private  Category convertToEntity(SaveCategoryResource resource){return mapper.map(resource,Category.class);}
    private  CategoryResource convertToResource(Category entity){return mapper.map(entity,CategoryResource.class);}
}
