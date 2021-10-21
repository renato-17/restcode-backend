package upc.edu.pe.restcodebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import upc.edu.pe.restcodebackend.domain.model.*;
import upc.edu.pe.restcodebackend.domain.repository.ProductRepository;
import upc.edu.pe.restcodebackend.domain.repository.SaleDetailRepository;
import upc.edu.pe.restcodebackend.domain.repository.SaleRepository;
import upc.edu.pe.restcodebackend.domain.service.SaleDetailService;
import upc.edu.pe.restcodebackend.exception.ResourceNotFoundException;
@Service
public class SaleDetailServiceImpl implements SaleDetailService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private SaleDetailRepository saleDetailRepository;

    @Override
    public Page<SaleDetail> getAllSaleDetails(Pageable pageable) {
        return saleDetailRepository.findAll(pageable);
    }

    @Override
    public SaleDetail getSaleDetailById(Long saleDetailId) {
        return saleDetailRepository.findById(saleDetailId)
                .orElseThrow(()-> new ResourceNotFoundException("SaleDetail","Id",saleDetailId));
    }

    @Override
    public SaleDetail createSaleDetail(SaleDetail saleDetail, Long productId, Long saleId) {
        Product product =  productRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("Product","Id",productId));
        Sale sale =  saleRepository.findById(saleId)
                .orElseThrow(()->new ResourceNotFoundException("Sale","Id",saleId));

        saleDetail.setProduct(product);
        saleDetail.setSale(sale);
        return saleDetailRepository.save(saleDetail);
    }

    @Override
    public SaleDetail updateSaleDetail(Long saleDetailId, SaleDetail saleDetailRequest) {
        SaleDetail saleDetail = saleDetailRepository.findById(saleDetailId)
                .orElseThrow(()-> new ResourceNotFoundException("SaleDetail","Id",saleDetailId));

        saleDetail.setDescription(saleDetailRequest.getDescription());
        saleDetail.setQuantity(saleDetailRequest.getQuantity());

        return saleDetailRepository.save(saleDetail);
    }

    @Override
    public ResponseEntity<?> deleteSaleDetail(Long saleDetailId) {
        SaleDetail saleDetail = saleDetailRepository.findById(saleDetailId)
                .orElseThrow(()-> new ResourceNotFoundException("SaleDetail","Id",saleDetailId));
        saleDetailRepository.delete(saleDetail);
        return ResponseEntity.ok().build();
    }
}
