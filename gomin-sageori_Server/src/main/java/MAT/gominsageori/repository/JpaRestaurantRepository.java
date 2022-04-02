package MAT.gominsageori.repository;

import MAT.gominsageori.domain.Restaurant;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaRestaurantRepository implements RestaurantRepository{
    public final EntityManager em;

    public JpaRestaurantRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        em.persist(restaurant);
        return restaurant;
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        Restaurant restaurant = em.find(Restaurant.class , id);
        return Optional.ofNullable(restaurant);
    }

    @Override
    public Optional<Restaurant> findByName(String name) {
        Restaurant restuarant = em.find(Restaurant.class , name);
        return Optional.ofNullable(restuarant);
    }

    @Override
    public List<Restaurant> findAll() {
        return em.createQuery("select r from Restaurant r" , Restaurant.class)
                .getResultList();
    }
}
