package MAT.gominsageori.repository;

import MAT.gominsageori.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaRestaurantRepository implements RestaurantRepository{
    public final EntityManager em;

    @Autowired
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
        List<Restaurant> result = em.createQuery("select r from Restaurant r where r.name = :name", Restaurant.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Restaurant> findAll() {
        return em.createQuery("select r from Restaurant r" , Restaurant.class)
                .getResultList();
    }
}
