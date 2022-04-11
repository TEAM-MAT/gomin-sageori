package MAT.gominsageori.service;

import MAT.gominsageori.domain.Menu;
import MAT.gominsageori.domain.RecommandParam;
import MAT.gominsageori.domain.Restaurant;
import MAT.gominsageori.repository.MenuRepository;
import MAT.gominsageori.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }
    /**
     * 식당등록
     */
    public Long register(Restaurant restaurant){
        validateDuplicateRestaurant(restaurant);
        restaurantRepository.save(restaurant);
        return restaurant.getId();

    }
    private void validateDuplicateRestaurant(Restaurant restaurant){
        restaurantRepository.findByName(restaurant.getName())
                .ifPresent(r -> {
                    throw new IllegalStateException("이미 존재하는 식당입니다.");
                });
    }

    public List<Restaurant> findAllByLocation(String location) {
        return restaurantRepository.findRestaurantByLocation(location);
    }

    /**
     *
     * 전체 식당 조회
     */
    public List<Restaurant> findAll(){
        return restaurantRepository.findAll();
    }
    public Optional<Restaurant> findOnebyId(Long restaurantId){
        return restaurantRepository.findById(restaurantId);
    }
    public Optional<Restaurant> findOnebyName(String restaurantName){
        return restaurantRepository.findByName(restaurantName);
    }

    public List<Restaurant> recommandRestaurant(RecommandParam recommandParam) {
        Menu menu = alterRecommandParamToMenu(recommandParam);
        List<Restaurant> findRestaurant = findAllByLocation(recommandParam.getLocation());
        if(recommandParam.getFranchise()) // RecommandParam 프랜차이즈 값이 true일때 프랜차이즈 식당 필터링
            FilteringFranchise(findRestaurant);
        FilteringCharacteristic(findRestaurant,menu); // 대표 메뉴의 특징을 바탕으로 필터링
        FilteringAllergy(findRestaurant,menu); // 알러지 정보를 바탕으로 필터링*/
        return findRestaurant;
    }

    public Menu alterRecommandParamToMenu(RecommandParam recommandParam) {
        Menu menu = new Menu();
        for(String str : recommandParam.getCharacteristic()) {
            if(str.equals("soup")) { menu.setSoup(true); }
            if(str.equals("spicy")) { menu.setSpicy(true); }
            if(str.equals("sweet")) { menu.setSweet(true); }
            if(str.equals("hot")) { menu.setHot(true); }
            if(str.equals("meat")) { menu.setMeat(true); }
            if(str.equals("noodle")) { menu.setNoodle(true); }
            if(str.equals("rice")) { menu.setRice(true); }
            if(str.equals("bread")) { menu.setBread(true); }
        }
        for(String str : recommandParam.getAllergic()) {
            if(str.equals("매밀")) { menu.setHasBuckwheat(true); }
            if(str.equals("밀")) { menu.setHasWheat(true); }
            if(str.equals("대두")) { menu.setHasSoybean(true); }
            if(str.equals("땅콩")) { menu.setHasPeanut(true); }
            if(str.equals("호두")) { menu.setHasWalnut(true); }
            if(str.equals("잣")) { menu.setHasPineNut(true); }
            if(str.equals("아황산류")) { menu.setHasSulFurousAcid(true); }
            if(str.equals("복숭아")) { menu.setHasPeach(true); }
            if(str.equals("토마토")) { menu.setHasTomato(true); }
            if(str.equals("난류")) { menu.setHasEgg(true); }
            if(str.equals("우유")) { menu.setHasMilk(true); }
            if(str.equals("새우")) { menu.setHasShrimp(true); }
            if(str.equals("고등어")) { menu.setHasMackerel(true); }
            if(str.equals("오징어")) { menu.setHasSquid(true); }
            if(str.equals("게")) { menu.setHasCrab(true); }
            if(str.equals("조개류")) { menu.setHasClam(true); }
            if(str.equals("돼지고기")) { menu.setHasPork(true); }
            if(str.equals("소고기")) { menu.setHasBeef(true); }
            if(str.equals("닭고기")) { menu.setHasChicken(true); }
        }
        return menu;
    }

    public void FilteringFranchise(List<Restaurant> restaurants) {
        for(Restaurant restaurantIter : restaurants) {
            if(restaurantIter.getFranchise()==null) continue;
            if(restaurantIter.getFranchise()) {
                restaurants.remove(restaurantIter);
            }
        }
    }

    public void FilteringCharacteristic(List<Restaurant> restaurants, Menu menu) {
        for(Restaurant restaurantIter : restaurants) {
            if(menu.isSoup() && !restaurantIter.getBestMenu().isSoup()) {
                restaurants.remove(restaurantIter);
                continue;
            }
            if(menu.isSpicy() && !restaurantIter.getBestMenu().isSpicy()) {
                restaurants.remove(restaurantIter);
                continue;
            }
            if(menu.isSweet() && !restaurantIter.getBestMenu().isSweet()) {
                restaurants.remove(restaurantIter);
                continue;
            }
            if(menu.isHot() && !restaurantIter.getBestMenu().isHot()) {
                restaurants.remove(restaurantIter);
                continue;
            }
            if(menu.isMeat() && !restaurantIter.getBestMenu().isMeat()) {
                restaurants.remove(restaurantIter);
                continue;
            }
            if(menu.isNoodle() && !restaurantIter.getBestMenu().isNoodle()) {
                restaurants.remove(restaurantIter);
                continue;
            }
            if(menu.isRice() && !restaurantIter.getBestMenu().isRice()) {
                restaurants.remove(restaurantIter);
                continue;
            }
            if(menu.isBread() && !restaurantIter.getBestMenu().isBread()) {
                restaurants.remove(restaurantIter);
                continue;
            }
        }
    }

    public void FilteringAllergy(List<Restaurant> restaurants, Menu menu) {
        for(Restaurant restaurantIter : restaurants) {
            if(menu.isHasBuckwheat() && restaurantIter.getBestMenu().isHasBuckwheat()) {
                restaurants.remove(restaurantIter);
                continue;
            }
            if(menu.isHasWheat() && restaurantIter.getBestMenu().isHasWheat()) {
                restaurants.remove(restaurantIter);
                continue;
            }
            if(menu.isHasSoybean() && restaurantIter.getBestMenu().isHasSoybean()) {
                restaurants.remove(restaurantIter);
                continue;
            }
            if(menu.isHasPeanut() && restaurantIter.getBestMenu().isHasPeanut()) {
                restaurants.remove(restaurantIter);
                continue;
            }
            if(menu.isHasWalnut() && restaurantIter.getBestMenu().isHasWalnut()) {
                restaurants.remove(restaurantIter);
                continue;
            }
            if(menu.isHasPineNut() && restaurantIter.getBestMenu().isHasPineNut()) {
                restaurants.remove(restaurantIter);
                continue;
            }
            if(menu.isHasSulFurousAcid() && restaurantIter.getBestMenu().isHasSulFurousAcid()) {
                restaurants.remove(restaurantIter);
                continue;
            }
            if(menu.isHasPeach() && restaurantIter.getBestMenu().isHasPeach()) {
                restaurants.remove(restaurantIter);
                continue;
            }
            if(menu.isHasTomato() && restaurantIter.getBestMenu().isHasTomato()) {
                restaurants.remove(restaurantIter);
                continue;
            }
            if(menu.isHasEgg() && restaurantIter.getBestMenu().isHasEgg()) {
                restaurants.remove(restaurantIter);
                continue;
            }
            if(menu.isHasMilk() && restaurantIter.getBestMenu().isHasMilk()) {
                restaurants.remove(restaurantIter);
                continue;
            }
            if(menu.isHasSquid() && restaurantIter.getBestMenu().isHasSquid()) {
                restaurants.remove(restaurantIter);
                continue;
            }
            if(menu.isHasMackerel() && restaurantIter.getBestMenu().isHasMackerel()) {
                restaurants.remove(restaurantIter);
                continue;
            }
            if(menu.isHasShrimp() && restaurantIter.getBestMenu().isHasShrimp()) {
                restaurants.remove(restaurantIter);
                continue;
            }
            if(menu.isHasCrab() && restaurantIter.getBestMenu().isHasCrab()) {
                restaurants.remove(restaurantIter);
                continue;
            }
            if(menu.isHasClam() && restaurantIter.getBestMenu().isHasClam()) {
                restaurants.remove(restaurantIter);
                continue;
            }
            if(menu.isHasPork() && restaurantIter.getBestMenu().isHasPork()) {
                restaurants.remove(restaurantIter);
                continue;
            }
            if(menu.isHasBeef() && restaurantIter.getBestMenu().isHasBeef()) {
                restaurants.remove(restaurantIter);
                continue;
            }
            if(menu.isHasChicken() && restaurantIter.getBestMenu().isHasChicken()) {
                restaurants.remove(restaurantIter);
                continue;
            }
        }
    }

}
