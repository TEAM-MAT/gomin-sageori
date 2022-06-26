package MAT.gominsageori.repository;

import MAT.gominsageori.domain.Address;
import MAT.gominsageori.domain.Menu;
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
    public List<Restaurant> recommendationQuery(String location, Boolean franchise, Menu menu){
        return em.createQuery("SELECT r " +
                "FROM Restaurant r " +
                        "WHERE " +
                        "r.bestMenu IN " +
                        "( SELECT m.id " +
                        "FROM Menu m " +
                        "WHERE (m.isBread = true and :isBread = true) OR " +
                        "m.isMeat = :isMeat OR " +
                        "m.isHot = :isHot OR " +
                        "m.isNoodle = :isNoodle OR " +
                        "m.isRice = :isRice OR " +
                        "m.isSpicy = :isSpicy OR " +
                        "m.isSweet = :isSweet) " +
                        "AND r.address.location = :location " +
                        "AND r.Franchise = :franchise", Restaurant.class)
                .setParameter("location",location)
                .setParameter("franchise",franchise)
                .setParameter("isHot", menu.isHot())
                .setParameter("isBread", menu.isBread())
                .setParameter("isMeat", menu.isMeat())
                .setParameter("isNoodle", menu.isNoodle())
                .setParameter("isRice", menu.isRice())
                .setParameter("isSpicy", menu.isSpicy())
                .setParameter("isSweet", menu.isSweet())
                .getResultList();
    }
}
