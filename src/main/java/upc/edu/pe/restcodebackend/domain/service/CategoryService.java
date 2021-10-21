package upc.edu.pe.restcodebackend.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import upc.edu.pe.restcodebackend.domain.model.Category;

public interface CategoryService {
    Page<Category> getAllCategories(Pageable pageable);
    Page<Category> getAllByRestaurantId(Long restaurantId, Pageable pageable);
    Category getCategoryById(Long categoryId);
    Category createCategory(Category category, Long restaurantId);
    Category updateCategory(Long categoryId, Category categoryRequest);
    ResponseEntity<?> deleteCategory(Long categoryId);
}
