package MAT.gominsageori.service;

import MAT.gominsageori.domain.Address;
import MAT.gominsageori.domain.Menu;
import MAT.gominsageori.domain.Restaurant;
import MAT.gominsageori.repository.AddressRepository;
import MAT.gominsageori.repository.JpaRestaurantRepository;
import MAT.gominsageori.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class RestaurantServiceTest {
    @Autowired AddressRepository addressRepository;
    @Autowired RestaurantRepository restaurantRepository;
    @Autowired RestaurantService restaurantService;
    @Autowired MenuService menuService;

    @Test
    @Commit
    public void register() throws Exception{
        Restaurant restaurant = new Restaurant();
        restaurant.setName("서울대매운탕");
        Address address = new Address();
        address.setLocation("서울대");
        restaurant.setAddress(address);
        address.setRestaurant(restaurant);
        Menu menu = new Menu();
        Optional<Menu> findmenu;
        findmenu = menuService.findMenuByName("매운탕");
        if(findmenu.isPresent())
            menu = findmenu.get();
        restaurant.setBestMenu(menu);

        Long id = restaurantService.register(restaurant);
        addressRepository.save(address);

        Restaurant findresult = restaurantRepository.findById(id).get();
        assertEquals(restaurant.getName(),findresult.getName());
    }

    @Test
    void findAllRestaurantByLocation() {
        String location = "신촌";
        List<Restaurant> find_restaurants = restaurantService.findAllByLocation(location);
        Restaurant restaurant = find_restaurants.get(2);
        System.out.println(restaurant.getName());
    }
}
