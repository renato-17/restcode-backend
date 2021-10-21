package upc.edu.pe.restcodebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import upc.edu.pe.restcodebackend.domain.model.Category;
import upc.edu.pe.restcodebackend.domain.repository.CategoryRepository;
import upc.edu.pe.restcodebackend.domain.repository.RestaurantRepository;
import upc.edu.pe.restcodebackend.domain.service.CategoryService;
import upc.edu.pe.restcodebackend.exception.ResourceNotFoundException;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Page<Category> getAllByRestaurantId(Long restaurantId, Pageable pageable) {
        return categoryRepository.findAllByRestaurantId(restaurantId,pageable);
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
    }

    @Override
    public Category createCategory(Category category, Long restaurantId) {
        return restaurantRepository.findById(restaurantId).map(restaurant ->{
            category.setRestaurant(restaurant);
            return categoryRepository.save(category);
        }).orElseThrow(()->new ResourceNotFoundException("Restaurant","Id",restaurantId));

    }

    @Override
    public Category updateCategory(Long categoryId, Category categoryRequest) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));

        category.setName(categoryRequest.getName());
        category.setRestaurant(categoryRequest.getRestaurant());
        return categoryRepository.save(category);
    }

    @Override
    public ResponseEntity<?> deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
        categoryRepository.delete(category);
        return ResponseEntity.ok().build();
    }
}
