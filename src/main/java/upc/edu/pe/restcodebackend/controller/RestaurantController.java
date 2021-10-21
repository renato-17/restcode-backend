package upc.edu.pe.restcodebackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.restcodebackend.domain.model.Restaurant;
import upc.edu.pe.restcodebackend.domain.service.RestaurantService;

import upc.edu.pe.restcodebackend.resource.RestaurantResource;
import upc.edu.pe.restcodebackend.resource.save.SaveRestaurantResource;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RestaurantController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RestaurantService restaurantService;

    @Operation(summary = "Get All Restaurants", description = "Get all restaurants", tags = {"restaurants"})
    @GetMapping("/restaurants")
    public Page<RestaurantResource> getAllRestaurants(Pageable pageable){
        Page<Restaurant> restaurantPage = restaurantService.getAllRestaurants(pageable);

        List<RestaurantResource> resources = restaurantPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get Restaurant By Id", description = "Get Restaurant By Id", tags = {"restaurants"})
    @GetMapping("/restaurants/{restaurantId}")
    public RestaurantResource getRestaurantById(@PathVariable Long restaurantId){
        return convertToResource(restaurantService.getRestaurantById(restaurantId));
    }

    @Operation(summary = "Create Restaurant", description = "Create a new restaurant", tags = {"restaurants"})
    @PostMapping("owners/{ownerId}/restaurants")
    public RestaurantResource createRestaurant(@Valid @RequestBody SaveRestaurantResource resource,@PathVariable Long ownerId){
        Restaurant restaurant = convertToEntity(resource);
        return convertToResource(restaurantService.createRestaurant(restaurant,ownerId));
    }

    @Operation(summary = "Update Restaurant", description = "Update a restaurant", tags = {"restaurants"})
    @PutMapping("/restaurants/{restaurantId}")
    public RestaurantResource updateRestaurant(
            @PathVariable Long restaurantId,
            @RequestBody @Valid SaveRestaurantResource resource){
        Restaurant restaurant = convertToEntity(resource);
        return convertToResource(restaurantService.updateRestaurant(restaurantId, restaurant));
    }

    @Operation(summary = "Delete a restaurant", description = "Delete a restaurant", tags = {"restaurants"})
    @DeleteMapping("/restaurants/{restaurantId}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable Long restaurantId){
        return restaurantService.deleteRestaurant(restaurantId);
    }

    private  Restaurant convertToEntity(SaveRestaurantResource resource){return mapper.map(resource,Restaurant.class);}
    private  RestaurantResource convertToResource(Restaurant entity){return mapper.map(entity,RestaurantResource.class);}
}
