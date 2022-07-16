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

    //특징으로부터 해당 특징들은 만족하는 메뉴들을 먼저 뽑은 뒤, 해당 메뉴들 중에 대표메뉴가 존재하는 식당을 필터링.
    @Override
    public List<Restaurant> recommendationQuery(String location, Boolean franchise, String menuFilteringQuery){
        return em.createQuery("SELECT r " +
                "FROM Restaurant r " +
                        "WHERE " +
                        "r.bestMenu IN " +
                        "( " + menuFilteringQuery + ")" +
                        "AND r.address.location = :location " +
                        "AND r.franchise = :franchise", Restaurant.class)
                .setParameter("location",location)
                .setParameter("franchise",franchise)
                .getResultList();
    }
}
