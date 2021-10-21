package upc.edu.pe.restcodebackend.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.pe.restcodebackend.domain.model.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Page<Category> findAllByRestaurantId(Long restaurantId, Pageable pageable);
}
