package MAT.gominsageori.service;

import MAT.gominsageori.domain.Menu;
import MAT.gominsageori.domain.Restaurant;
import MAT.gominsageori.domain.RestaurantMenu;
import MAT.gominsageori.repository.MenuRepository;
import MAT.gominsageori.repository.RestaurantMenuRepository;
import MAT.gominsageori.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class RestaurantMenuServiceTest {
    @Autowired RestaurantMenuService restaurantMenuService;
    @Autowired RestaurantMenuRepository restaurantMenuRepository;
    @Autowired RestaurantRepository restaurantRepository;
    @Autowired MenuRepository menuRepository;

    @Test
    @Commit
    public void 레스토랑_메뉴_추가() {
        Optional<Restaurant> restaurant = restaurantRepository.findById(5L);
        Optional<Menu> menu = menuRepository.findById(1L);
        RestaurantMenu restaurantMenu = new RestaurantMenu();
        restaurantMenu.setRestaurant(restaurant.get());
        restaurantMenu.setMenu(menu.get());

        restaurantMenuService.addRestaurantMenu(restaurantMenu);

        //RestaurantMenu findRestaurantMenu = restaurantMenuService;
    }

}
