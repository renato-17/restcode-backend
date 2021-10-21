package upc.edu.pe.restcodebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import upc.edu.pe.restcodebackend.domain.model.Product;
import upc.edu.pe.restcodebackend.domain.repository.CategoryRepository;
import upc.edu.pe.restcodebackend.domain.repository.ProductRepository;
import upc.edu.pe.restcodebackend.domain.service.CategoryService;
import upc.edu.pe.restcodebackend.domain.service.ProductService;
import upc.edu.pe.restcodebackend.exception.ResourceNotFoundException;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> getAllByCategoryId(Long categoryId, Pageable pageable) {
        return productRepository.findAllByCategoryId(categoryId,pageable);
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product","Id",productId));
    }

    @Override
    public Product createProduct(Product product, Long categoryId) {
        return categoryRepository.findById(categoryId).map(category ->{
            product.setCategory(category);
            return productRepository.save(product);
        }).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));

    }

    @Override
    public Product updateProduct(Long productId, Product productRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product","Id",productId));

        product.setPrice(productRequest.getPrice());
        product.setName(productRequest.getName());

        return productRepository.save(product);
    }

    @Override
    public ResponseEntity<?> deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product","Id",productId));
        productRepository.delete(product);
        return ResponseEntity.ok().build();
    }
}
