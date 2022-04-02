package MAT.gominsageori.service;

import MAT.gominsageori.domain.Restaurant;
import MAT.gominsageori.repository.JpaRestaurantRepository;
import MAT.gominsageori.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class RestaurantServiceTest {
    @Autowired RestaurantRepository restaurantRepository;
    @Autowired RestaurantService restaurantService;

    @Test
    public void register() throws Exception{

        Restaurant restaurant = new Restaurant();
        restaurant.setName("고민사거리");

        Long id = restaurantService.register(restaurant);

        Restaurant findresult = restaurantRepository.findById(id).get();
        assertEquals(restaurant.getName(),findresult.getName());
    }

}
