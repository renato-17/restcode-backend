package upc.edu.pe.restcodebackend.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.pe.restcodebackend.domain.model.Comment;
import upc.edu.pe.restcodebackend.domain.model.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findAllByCategoryId(Long categoryId, Pageable pageable);
}
