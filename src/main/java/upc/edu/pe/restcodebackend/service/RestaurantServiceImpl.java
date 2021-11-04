package upc.edu.pe.restcodebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import upc.edu.pe.restcodebackend.domain.model.Restaurant;
import upc.edu.pe.restcodebackend.domain.repository.OwnerRepository;
import upc.edu.pe.restcodebackend.domain.repository.RestaurantRepository;
import upc.edu.pe.restcodebackend.domain.service.RestaurantService;
import upc.edu.pe.restcodebackend.exception.ResourceNotFoundException;
@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Page<Restaurant> getAllRestaurants(Pageable pageable) {
        return restaurantRepository.findAll(pageable);
    }

    @Override
    public Restaurant getRestaurantById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(()-> new ResourceNotFoundException("Restaurant","Id",restaurantId));
    }

    @Override
    public Restaurant getRestaurantByOwnerId(Long ownerId) {
        return restaurantRepository.findByOwnerId(ownerId)
                .orElseThrow(()-> new ResourceNotFoundException("Restaurant","Owner Id",ownerId));
    }

    @Override
    public Restaurant createRestaurant(Restaurant restaurant, Long ownerId) {
        return ownerRepository.findById(ownerId).map(owner ->{
            restaurant.setOwner(owner);
            return restaurantRepository.save(restaurant);
        }).orElseThrow(()->new ResourceNotFoundException("Owner","Id",ownerId));

    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, Restaurant restaurantRequest) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(()-> new ResourceNotFoundException("Restaurant","Id",restaurantId));

        restaurant.setName(restaurantRequest.getName());
        restaurant.setAddress(restaurantRequest.getAddress());
        restaurant.setCellPhoneNumber(restaurantRequest.getCellPhoneNumber());

        return restaurantRepository.save(restaurant);
    }

    @Override
    public ResponseEntity<?> deleteRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(()-> new ResourceNotFoundException("Restaurant","Id",restaurantId));
        restaurantRepository.delete(restaurant);
        return ResponseEntity.ok().build();
    }
}
