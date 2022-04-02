package MAT.gominsageori.repository;

import MAT.gominsageori.domain.Member;
import MAT.gominsageori.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);
    Optional<Restaurant> findById(Long id);
    Optional<Restaurant> findByName(String name);
    List<Restaurant> findAll();
}
