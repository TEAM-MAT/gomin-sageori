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
        Restaurant result = em.find(Restaurant.class,id);
        if (result == null) {
            throw new Exception("no Restaurant result");
        }
        else {
            return result;
        }
    }

    @Override
    public Optional<Restaurant> findByName(String name) {
        List<Restaurant> result = em.createQuery("SELECT r " +
                        "FROM Restaurant r " +
                        "WHERE r.name = :name", Restaurant.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Restaurant> findAll() {
        return em.createQuery("SELECT r " +
                        "FROM Restaurant r" , Restaurant.class)
                .getResultList();
    }

    @Override
    public Optional<Restaurant> findRestaurantByAdd(Address address){
        List <Restaurant> result = em.createQuery("SELECT r " +
                        "FROM Restaurant r " +
                        "WHERE r.address = :address ",Restaurant.class )
                .setParameter("address",address)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Restaurant> findRestaurantByLocation(String location){
        return em.createQuery("SELECT r " +
                        "FROM Restaurant r " +
                        "WHERE r.address.location = :location",Restaurant.class )
                .setParameter("location",location)
                .getResultList();
    }

    @Override
    public List<Restaurant> findByLocationAndFranchise(String location, Boolean franchise){
        return em.createQuery("SELECT r " +
                "FROM Restaurant r " +
                "WHERE r.address.location = :location and r.Franchise = :franchise", Restaurant.class)
                .setParameter("location",location)
                .setParameter("franchise",franchise)
                .getResultList();
    }
}
