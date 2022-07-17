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
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }
    /**
     * 식당등록
     */
    public Long register(Restaurant restaurant) {
        validateDuplicateRestaurant(restaurant);
        restaurantRepository.save(restaurant);
        return restaurant.getId();
    }
    private void validateDuplicateRestaurant(Restaurant restaurant) {
        restaurantRepository.findByName(restaurant.getName())
                .ifPresent(r -> {
                    throw new IllegalStateException("이미 존재하는 식당입니다.");
                });
    }

    public List<Restaurant> findAllByLocation(String location) {
        return restaurantRepository.findRestaurantByLocation(location);
    }

    public Set<Restaurant> findSpecificDataById(List<Long> selectedIds) throws Exception {
        Set<Restaurant> restaurants = new HashSet<>();
        for(Long iter : selectedIds) {
            try {
                restaurants.add(restaurantRepository.findById(iter));
            }
            catch (Exception e) {
                throw new Exception("no Restaurant result");
            }
        }
        return restaurants;
    }

    /**
     *
     * 전체 식당 조회
     */
    public List<Restaurant> findAll(){
        return restaurantRepository.findAll();
    }

    public HashMap<String, Object> findOneById(Long restaurantId) {
        Restaurant findResult;
        try {
            findResult = restaurantRepository.findById(restaurantId);
        } catch (Exception ex) {
            HashMap<String, Object> returning = new HashMap<>();
            returning.put("result", ex.getMessage());
            return returning;
        }
        HashMap<String, Object> payLoad = new HashMap<>();
        payLoad.put("result", findResult);
        return payLoad;
    }

    public Optional<Restaurant> findOnebyName(String restaurantName) {
        return restaurantRepository.findByName(restaurantName);
    }

    public List<Restaurant> recommandRestaurant(RecommandParam recommandParam) throws Exception {
        Menu menu = alterRecommandParamToMenu(recommandParam); // 추천 API에서 파라미터로 받은 내용을 Menu 객체에 옮겨담음.
        List<Restaurant> restaurantCandidates;
        try{
            String menuFilteringQuery = makeMenuFilteringQuery(menu); //menu 필터링 쿼리를 입력받은 조건으로부터 만듦.
            restaurantCandidates = restaurantRepository
                    .recommendationQuery(recommandParam.getLocation(), recommandParam.getFranchise(), menuFilteringQuery);
        } catch (Exception e){
            if(e.getMessage() == "검색할 특징이 없음") {
                throw new Exception("검색할 특징이 없음");
            }
            throw new NoSuchElementException("반환할 리스트가 없습니다.");
        }
        if(restaurantCandidates.size() == 0) {
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
    //입력받은 조건에서 메뉴를 필터링하는 쿼리를 만듦.
    public String makeMenuFilteringQuery(Menu menu) throws Exception{
        String filteringQuery = "SELECT m.id " +
                "FROM Menu m " +
                "WHERE ( ";
        String breadFilter = "( m.isBread = true )";
        String meatFilter = "( m.isMeat = true )";
        String hotFilter = "( m.isHot = true )";
        String noodleFilter = "( m.isNoodle = true )";
        String riceFilter = "( m.isRice = true )";
        String spicyFilter = "( m.isSpicy = true )";
        String sweetFilter = "( m.Sweet = true )";
        String soupFilter = "( m.Sweet = true )";
        int count = 0;
        if (menu.isBread()) {
            filteringQuery += breadFilter;
            count += 1;
        }
        if (menu.isMeat()) {
            if (count != 0 ){
                filteringQuery += " AND ";
            }
            filteringQuery += meatFilter;
            count += 1;
        }
        if (menu.isHot()) {
            if (count != 0 ){
                filteringQuery += " AND ";
            }
            filteringQuery += hotFilter;
            count += 1;
        }
        if (menu.isNoodle()) {
            if (count != 0 ){
                filteringQuery += " AND ";
            }
            filteringQuery += noodleFilter;
            count += 1;
        }
        if (menu.isRice()) {
            if (count != 0 ){
                filteringQuery += " AND ";
            }
            filteringQuery += riceFilter;
            count += 1;
        }
        if (menu.isSpicy()) {
            if (count != 0 ){
                filteringQuery += " AND ";
            }
            filteringQuery += spicyFilter;
            count += 1;
        }
        if (menu.isSweet()) {
            if (count != 0 ){
                filteringQuery += " AND ";
            }
            filteringQuery += sweetFilter;
            count += 1;
        }
        if (menu.isSoup()) {
            if (count != 0 ){
                filteringQuery += " AND ";
            }
            filteringQuery += soupFilter;
            count += 1;
        }
        filteringQuery += " )";
        if (count == 0) { //하나의 특징도 검색이 안들어갔으면 exception.
            filteringQuery = "SELECT m FROM Menu m ";
            return filteringQuery;
        }
        else {
            return filteringQuery;
        }
    }
}
