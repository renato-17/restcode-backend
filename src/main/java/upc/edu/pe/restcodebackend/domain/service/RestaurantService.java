package upc.edu.pe.restcodebackend.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import upc.edu.pe.restcodebackend.domain.model.Restaurant;

public interface RestaurantService {
    Page<Restaurant> getAllRestaurants(Pageable pageable);
    Restaurant getRestaurantById(Long restaurantId);
    Restaurant getRestaurantByOwnerId(Long ownerId);
    Restaurant createRestaurant(Restaurant restaurant, Long ownerId);
    Restaurant updateRestaurant(Long restaurantId, Restaurant restaurantRequest);
    ResponseEntity<?> deleteRestaurant(Long restaurantId);
}
