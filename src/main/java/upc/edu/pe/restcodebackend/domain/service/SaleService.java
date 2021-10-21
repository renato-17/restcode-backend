package upc.edu.pe.restcodebackend.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import upc.edu.pe.restcodebackend.domain.model.Sale;

public interface SaleService {
    Page<Sale> getAllSales(Pageable pageable);
    Sale getSaleById(Long saleId);
    Sale createSale(Sale sale, Long restaurantId);
    Sale updateSale(Long saleId, Sale saleRequest);
    ResponseEntity<?> deleteSale(Long saleId);
}
