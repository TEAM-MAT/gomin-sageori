package MAT.gominsageori.repository;

import MAT.gominsageori.domain.RestaurantMenu;

import java.util.List;

public interface RestaurantMenuRepository {
    public RestaurantMenu save(RestaurantMenu restaurantMenu);
    public List<RestaurantMenu> findAllByRestaurantId(Long restaurantId);
    public List<RestaurantMenu> findAllByMenuId(Long menuId);
}
