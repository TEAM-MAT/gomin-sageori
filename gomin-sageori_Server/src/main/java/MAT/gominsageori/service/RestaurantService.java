package MAT.gominsageori.service;

import MAT.gominsageori.domain.Menu;
import MAT.gominsageori.transfer.RecommandParam;
import MAT.gominsageori.domain.Restaurant;
import MAT.gominsageori.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

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
    public List<Restaurant> findOnebyId(Long restaurantId){
        return restaurantRepository.findById(restaurantId);
    }
    public Optional<Restaurant> findOnebyName(String restaurantName){
        return restaurantRepository.findByName(restaurantName);
    }

    public List<Restaurant> recommandRestaurant(RecommandParam recommandParam) {
        Menu menu = alterRecommandParamToMenu(recommandParam);
        List<Restaurant> findRestaurant = findAllByLocation(recommandParam.getLocation());
        if(recommandParam.getFranchise()) { // RecommandParam 프랜차이즈 값이 true일때 프랜차이즈 식당 필터링
            FilteringFranchise(findRestaurant);
        }
        FilteringCharacteristic(findRestaurant,menu); // 대표 메뉴의 특징을 바탕으로 필터링
        //FilteringAllergy(findRestaurant,menu); // 알러지 정보를 바탕으로 필터링
        if(findRestaurant.isEmpty()) {
            throw new NoSuchElementException("반환할 리스트가 없습니다.");
        }
        Collections.shuffle(findRestaurant);
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
        /*for(String str : recommandParam.getAllergic()) {
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

         */
        return menu;
    }

    public void FilteringFranchise(List<Restaurant> restaurants) {
        for(Iterator<Restaurant> restaurantIter = restaurants.iterator(); restaurantIter.hasNext();) {
            Restaurant cmpRestaurant = restaurantIter.next();
            if(cmpRestaurant.getFranchise()==null) continue;
            if(cmpRestaurant.getFranchise()) {
                restaurantIter.remove();
            }
        }
    }

    public void FilteringCharacteristic(List<Restaurant> restaurants, Menu menu) {
        for(Iterator<Restaurant> restaurantIter = restaurants.iterator(); restaurantIter.hasNext();) {
            Restaurant cmpRestaurant = restaurantIter.next();
            if(cmpRestaurant.getBestMenu()==null) {
                restaurantIter.remove();
                continue;
            }
            if(menu.isSoup() && !cmpRestaurant.getBestMenu().isSoup()) {
                restaurantIter.remove();
                continue;
            }
            if(menu.isSpicy() && !cmpRestaurant.getBestMenu().isSpicy()) {
                restaurantIter.remove();
                continue;
            }
            if(menu.isSweet() && !cmpRestaurant.getBestMenu().isSweet()) {
                restaurantIter.remove();
                continue;
            }
            if(menu.isHot() && !cmpRestaurant.getBestMenu().isHot()) {
                restaurantIter.remove();
                continue;
            }
            if(menu.isMeat() && !cmpRestaurant.getBestMenu().isMeat()) {
                restaurantIter.remove();
                continue;
            }
            if(menu.isNoodle() && !cmpRestaurant.getBestMenu().isNoodle()) {
                restaurantIter.remove();
                continue;
            }
            if(menu.isRice() && !cmpRestaurant.getBestMenu().isRice()) {
                restaurantIter.remove();
                continue;
            }
            if(menu.isBread() && !cmpRestaurant.getBestMenu().isBread()) {
                restaurantIter.remove();
                continue;
            }
        }
    }
    /*
    public void FilteringAllergy(List<Restaurant> restaurants, Menu menu) {
        for(Iterator<Restaurant> restaurantIter = restaurants.iterator(); restaurantIter.hasNext();) {
            Restaurant cmpRestaurant = restaurantIter.next();
            if(menu.isHasBuckwheat() && cmpRestaurant.getBestMenu().isHasBuckwheat()) {
                restaurantIter.remove();
                continue;
            }
            if(menu.isHasWheat() && cmpRestaurant.getBestMenu().isHasWheat()) {
                restaurantIter.remove();
                continue;
            }
            if(menu.isHasSoybean() && cmpRestaurant.getBestMenu().isHasSoybean()) {
                restaurantIter.remove();
                continue;
            }
            if(menu.isHasPeanut() && cmpRestaurant.getBestMenu().isHasPeanut()) {
                restaurantIter.remove();
                continue;
            }
            if(menu.isHasWalnut() && cmpRestaurant.getBestMenu().isHasWalnut()) {
                restaurantIter.remove();
                continue;
            }
            if(menu.isHasPineNut() && cmpRestaurant.getBestMenu().isHasPineNut()) {
                restaurantIter.remove();
                continue;
            }
            if(menu.isHasSulFurousAcid() && cmpRestaurant.getBestMenu().isHasSulFurousAcid()) {
                restaurantIter.remove();
                continue;
            }
            if(menu.isHasPeach() && cmpRestaurant.getBestMenu().isHasPeach()) {
                restaurantIter.remove();
                continue;
            }
            if(menu.isHasTomato() && cmpRestaurant.getBestMenu().isHasTomato()) {
                restaurantIter.remove();
                continue;
            }
            if(menu.isHasEgg() && cmpRestaurant.getBestMenu().isHasEgg()) {
                restaurantIter.remove();
                continue;
            }
            if(menu.isHasMilk() && cmpRestaurant.getBestMenu().isHasMilk()) {
                restaurantIter.remove();
                continue;
            }
            if(menu.isHasSquid() && cmpRestaurant.getBestMenu().isHasSquid()) {
                restaurantIter.remove();
                continue;
            }
            if(menu.isHasMackerel() && cmpRestaurant.getBestMenu().isHasMackerel()) {
                restaurantIter.remove();
                continue;
            }
            if(menu.isHasShrimp() && cmpRestaurant.getBestMenu().isHasShrimp()) {
                restaurantIter.remove();
                continue;
            }
            if(menu.isHasCrab() && cmpRestaurant.getBestMenu().isHasCrab()) {
                restaurantIter.remove();
                continue;
            }
            if(menu.isHasClam() && cmpRestaurant.getBestMenu().isHasClam()) {
                restaurantIter.remove();
                continue;
            }
            if(menu.isHasPork() && cmpRestaurant.getBestMenu().isHasPork()) {
                restaurantIter.remove();
                continue;
            }
            if(menu.isHasBeef() && cmpRestaurant.getBestMenu().isHasBeef()) {
                restaurantIter.remove();
                continue;
            }
            if(menu.isHasChicken() && cmpRestaurant.getBestMenu().isHasChicken()) {
                restaurantIter.remove();
                continue;
            }
        }
    }

     */

}
