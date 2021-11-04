package upc.edu.pe.restcodebackend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.pe.restcodebackend.domain.model.Restaurant;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    Optional<Restaurant> findByOwnerId(Long ownerId);
}
