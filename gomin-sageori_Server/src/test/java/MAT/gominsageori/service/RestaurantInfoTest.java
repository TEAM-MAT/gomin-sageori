package MAT.gominsageori.service;

import MAT.gominsageori.domain.Restaurant;
import MAT.gominsageori.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class RestaurantInfoTest {
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired RestaurantService restaurantService;

    @Test
    void infoTest() {
        Long id = Long.valueOf(1);
        Restaurant restaurant = restaurantService.findOnebyId(id);
    }
}
