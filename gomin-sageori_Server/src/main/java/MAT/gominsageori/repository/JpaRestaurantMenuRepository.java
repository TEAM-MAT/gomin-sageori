package MAT.gominsageori.repository;

import MAT.gominsageori.domain.Menu;
import MAT.gominsageori.domain.Restaurant;
import MAT.gominsageori.domain.RestaurantMenu;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaRestaurantMenuRepository implements RestaurantMenuRepository{

    public final EntityManager em;

    public JpaRestaurantMenuRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public RestaurantMenu save(RestaurantMenu restaurantMenu) {
        /*Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantMenu.getRestaurant().getName());
        Menu menu = new Menu();
        menu.setId(restaurantMenu.getMenu().getId());
        menu.setName(restaurantMenu.getMenu().getName());*/
        em.persist(restaurantMenu);
        return restaurantMenu;
    }

    @Override
    public List<RestaurantMenu> findAllByRestaurantId(Long restaurantId) {
        return em.createQuery("select r from RestaurantMenu r where r.restaurant = :restaurantId", RestaurantMenu.class)
                .setParameter("restaurantId", restaurantId)
                .getResultList();
    }

    @Override
    public List<RestaurantMenu> findAllByMenuId(Long menuId) {
        return em.createQuery("select r from RestaurantMenu r where r.menu = :menuId", RestaurantMenu.class)
                .setParameter("menuId", menuId)
                .getResultList();
    }
}
