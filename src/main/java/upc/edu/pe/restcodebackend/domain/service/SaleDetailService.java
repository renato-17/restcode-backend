package upc.edu.pe.restcodebackend.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import upc.edu.pe.restcodebackend.domain.model.SaleDetail;

public interface SaleDetailService {
    Page<SaleDetail> getAllSaleDetails(Pageable pageable);
    SaleDetail getSaleDetailById(Long saleDetailId);
    SaleDetail createSaleDetail(SaleDetail saleDetail, Long productId, Long saleId);
    SaleDetail updateSaleDetail(Long saleDetailId, SaleDetail saleDetailRequest);
    ResponseEntity<?> deleteSaleDetail(Long saleDetailId);
}
