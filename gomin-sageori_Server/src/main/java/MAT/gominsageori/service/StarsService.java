package MAT.gominsageori.service;

import MAT.gominsageori.domain.Restaurant;
import MAT.gominsageori.domain.Stars;
import MAT.gominsageori.repository.RestaurantRepository;
import MAT.gominsageori.repository.StarsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class StarsService {

    private final StarsRepository starsRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public StarsService(StarsRepository starsRepository, RestaurantRepository restaurantRepository) {
        this.starsRepository = starsRepository;
        this.restaurantRepository = restaurantRepository;

    }

    public void save(Stars stars) throws Exception {
        try {
            starsRepository.save(stars);
        } catch (Exception ex) {
            starsRepository.update(stars);
        } finally {
            Float averageStar = starsRepository.getAverageStars(stars.getRestaurantPK());
            Restaurant restaurant = restaurantRepository.findById(stars.getRestaurantPK());
            restaurant.setInternalStar(averageStar);
            restaurantRepository.update(restaurant);
        }
    }

    public void update(Stars stars) throws Exception {
        starsRepository.update(stars);
        Float averageStar = starsRepository.getAverageStars(stars.getRestaurantPK());
        Restaurant restaurant = restaurantRepository.findById(stars.getRestaurantPK());
        restaurant.setInternalStar(averageStar);
        restaurantRepository.update(restaurant);
    }

    public void giveStar(Stars stars) throws Exception {
        Optional<Stars> findResult = starsRepository.findByMemberAndRestaurant(stars.getUserPK(), stars.getRestaurantPK());
        if (findResult.isEmpty()) {
            save(stars);
        } else {
            Stars toUpdate = findResult.get();
            toUpdate.setStars(stars.getStars());
            update(toUpdate);
        }
    }


}
