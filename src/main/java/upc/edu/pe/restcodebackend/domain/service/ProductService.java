package upc.edu.pe.restcodebackend.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import upc.edu.pe.restcodebackend.domain.model.Product;

public interface ProductService {
    Page<Product> getAllProducts(Pageable pageable);
    Page<Product> getAllByCategoryId(Long categoryId, Pageable pageable);
    Product getProductById(Long productId);
    Product createProduct(Product product, Long categoryId);
    Product updateProduct(Long productId, Product productRequest);
    ResponseEntity<?> deleteProduct(Long productId);
}
