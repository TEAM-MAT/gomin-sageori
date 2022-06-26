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

    public HashMap<String, Object> findOneById(Long restaurantId){
        Restaurant findResult;
        try{
            findResult = restaurantRepository.findById(restaurantId);
        } catch (Exception ex){
            HashMap<String, Object> returning = new HashMap<>();
            returning.put("result", ex.getMessage());
            return returning;
        }
        HashMap<String, Object> payLoad = new HashMap<>();
        payLoad.put("result", findResult);
        return payLoad;
    }

    public Optional<Restaurant> findOnebyName(String restaurantName){
        return restaurantRepository.findByName(restaurantName);
    }

    public List<Restaurant> recommandRestaurant(RecommandParam recommandParam) throws Exception {
        Menu menu = alterRecommandParamToMenu(recommandParam); // 추천 API에서 파라미터로 받은 내용을 Menu 객체에 옮겨담음.
        List<Restaurant> restaurantCandidates;
        try{
            restaurantCandidates = restaurantRepository
                    .recommendationQuery(recommandParam.getLocation(), recommandParam.getFranchise(), menu);
        } catch (Exception e){
            throw new NoSuchElementException("반환할 리스트가 없습니다.");
        }
        if(restaurantCandidates.isEmpty()) {
            throw new NoSuchElementException("반환할 리스트가 없습니다.");
        }
        Collections.shuffle(restaurantCandidates);
        return restaurantCandidates;
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
        return menu;
    }

}
