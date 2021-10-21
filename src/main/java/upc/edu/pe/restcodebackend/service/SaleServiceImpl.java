package upc.edu.pe.restcodebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import upc.edu.pe.restcodebackend.domain.model.Sale;
import upc.edu.pe.restcodebackend.domain.repository.RestaurantRepository;
import upc.edu.pe.restcodebackend.domain.repository.SaleRepository;
import upc.edu.pe.restcodebackend.domain.service.SaleService;
import upc.edu.pe.restcodebackend.exception.ResourceNotFoundException;
@Service
public class SaleServiceImpl implements SaleService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private SaleRepository saleRepository;

    @Override
    public Page<Sale> getAllSales(Pageable pageable) {
        return saleRepository.findAll(pageable);
    }

    @Override
    public Sale getSaleById(Long saleId) {
        return saleRepository.findById(saleId)
                .orElseThrow(()-> new ResourceNotFoundException("Sale","Id",saleId));
    }

    @Override
    public Sale createSale(Sale sale, Long restaurantId) {
        return restaurantRepository.findById(restaurantId).map(restaurant ->{
            sale.setRestaurant(restaurant);
            return saleRepository.save(sale);
        }).orElseThrow(()->new ResourceNotFoundException("Restaurant","Id",restaurantId));

    }

    @Override
    public Sale updateSale(Long saleId, Sale saleRequest) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(()-> new ResourceNotFoundException("Sale","Id",saleId));

        sale.setDateAndTime(saleRequest.getDateAndTime());
        sale.setClientFullName(saleRequest.getClientFullName());
        return saleRepository.save(sale);
    }

    @Override
    public ResponseEntity<?> deleteSale(Long saleId) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(()-> new ResourceNotFoundException("Sale","Id",saleId));
        saleRepository.delete(sale);
        return ResponseEntity.ok().build();
    }
}
