package MAT.gominsageori.service;

import MAT.gominsageori.domain.Restaurant;
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

    /**
     *
     * 전체 회원 조회
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

}
