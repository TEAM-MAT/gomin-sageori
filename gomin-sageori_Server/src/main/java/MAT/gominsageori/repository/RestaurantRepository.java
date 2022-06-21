package MAT.gominsageori.repository;

import MAT.gominsageori.domain.Address;
import MAT.gominsageori.domain.Member;
import MAT.gominsageori.domain.Menu;
import MAT.gominsageori.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);
    Restaurant findById (Long id) throws Exception;
    Optional<Restaurant> findByName(String name);
    List<Restaurant> findAll();
    Optional<Restaurant> findRestaurantByAdd(Address address);
    List<Restaurant> findRestaurantByLocation(String location);
    List<Restaurant> recommendationQuery(String location, Boolean franchise, Menu menu);
}
