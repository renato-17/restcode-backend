package upc.edu.pe.restcodebackend.nunit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import upc.edu.pe.restcodebackend.domain.model.Owner;
import upc.edu.pe.restcodebackend.domain.model.Restaurant;
import upc.edu.pe.restcodebackend.domain.repository.RestaurantRepository;
import upc.edu.pe.restcodebackend.domain.repository.OwnerRepository;
import upc.edu.pe.restcodebackend.domain.service.RestaurantService;
import upc.edu.pe.restcodebackend.exception.ResourceNotFoundException;
import upc.edu.pe.restcodebackend.service.RestaurantServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class RestaurantServiceImplIntegrationTest {
    @MockBean
    private RestaurantRepository restaurantRepository;

    @MockBean
    private OwnerRepository ownerRepository;

    @Autowired
    private RestaurantService restaurantService;

    @TestConfiguration
    static class RestaurantServiceImplTestConfiguration {
        @Bean
        public RestaurantService restaurantService() {
            return new RestaurantServiceImpl();
        }
    }

    @Test
    @DisplayName("When RestaurantById With Valid Id Then Returns Restaurant")
    public void whenGetRestaurantByIdWithValidIdThenReturnsRestaurant() {
        //Arrange
        Long id = 1L;
        String name = "Restaurant name";
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setId(id);

        when(restaurantRepository.findById(id))
                .thenReturn(Optional.of(restaurant));
        //Act
        Restaurant foundRestaurant = restaurantService.getRestaurantById(id);

        //Assert
        assertThat(foundRestaurant.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("When RestaurantById With Not Valid Id Then Returns Not Found Exception")
    public void whenGetRestaurantByIdWithNotValidIdThenReturnsNotFoundException() {
        //Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(restaurantRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Restaurant", "Id", id);

        //Act
        Throwable exception = catchThrowable(() -> {
            Restaurant foundRestaurant = restaurantService.getRestaurantById(id);
        });

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("Create Restaurant With Valid PublicationId Then Returns New Restaurant")
    public void createRestaurantWithValidPublicationIdThenReturnNewRestaurant() {
        //Arrange
        Long ownerId = 1L;
        String ownerUsername = "username owner";
        Owner owner = new Owner();
        owner.setId(ownerId);
        owner.setUserName(ownerUsername);

        when(ownerRepository.findById(ownerId))
                .thenReturn(Optional.of(owner));


        String name = "Restaurant name";
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setOwner(owner);

        when(restaurantRepository.save(restaurant))
                .thenReturn(restaurant);

        //Act
        Restaurant foundRestaurant = restaurantService.createRestaurant(restaurant,ownerId);
        //Assert
        assertThat(foundRestaurant.getOwner()).isEqualTo(owner);
    }
}
