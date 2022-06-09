package MAT.gominsageori.repository;

import MAT.gominsageori.domain.Address;
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
    public Restaurant findById(Long id) throws Exception{
        List<Restaurant> resultList = em.createQuery("SELECT r FROM " +
                        "Restaurant r WHERE r.id = :id", Restaurant.class)
                .setParameter("id", id)
                .getResultList();
        if (resultList.isEmpty()) {
            throw new Exception("no Restaurant result");
        }
        return resultList.get(0);
    }

    @Override
    public Optional<Restaurant> findByName(String name) {
        List<Restaurant> result = em.createQuery("SELECT r FROM Restaurant r " +
                        "WHERE r.name = :name", Restaurant.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Restaurant> findAll() {
        return em.createQuery("select r from Restaurant r" , Restaurant.class)
                .getResultList();
    }

    @Override
    public Optional<Restaurant> findRestaurantByAdd(Address address){
        List <Restaurant> result = em.createQuery("select r from Restaurant  r where r.address = :address ",Restaurant.class )
                .setParameter("address",address)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Restaurant> findRestaurantByLocation(String location){
        return em.createQuery("select r from Restaurant  r where r.address.location = :location",Restaurant.class )
                .setParameter("location",location)
                .getResultList();
    }
}
