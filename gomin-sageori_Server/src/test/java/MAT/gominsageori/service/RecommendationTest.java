package MAT.gominsageori.service;

import MAT.gominsageori.domain.Menu;
import MAT.gominsageori.transfer.RecommandParam;
import MAT.gominsageori.domain.Restaurant;
import MAT.gominsageori.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class RecommendationTest {
    @Autowired RestaurantRepository restaurantRepository;
    @Autowired RestaurantService restaurantService;

    @Test
    void 추천_테스트() {
        String location = "신촌";
        List<Restaurant> find_restaurants = restaurantService.findAllByLocation(location);
        Menu wantEatMenu = new Menu();
        wantEatMenu.setName("메뉴샘플"); wantEatMenu.setSoup(true); wantEatMenu.setSpicy(true); wantEatMenu.setHot(true); wantEatMenu.setHasSquid(true);

        restaurantService.FilteringCharacteristic(find_restaurants,wantEatMenu);
        restaurantService.FilteringAllergy(find_restaurants,wantEatMenu);

        for(Restaurant restaurantIter : find_restaurants) {
            System.out.println(restaurantIter.getName());
        }
    }

    @Test
    void 추천_테스트2() {
        RecommandParam recommandParam = new RecommandParam();
        recommandParam.setLocation("신촌");
        recommandParam.setFranchise(true);

        List<String> menuCharacters = new ArrayList<>();
        menuCharacters.add("hot");
        recommandParam.setCharacteristic(menuCharacters);

        List<String> menuAllergy = new ArrayList<>();
        recommandParam.setAllergic(menuAllergy);

        List<Restaurant> findRestaurants = restaurantService.recommandRestaurant(recommandParam);

        for(Restaurant restaurantIter : findRestaurants) {
            System.out.println(restaurantIter.getName());
        }
    }
}
