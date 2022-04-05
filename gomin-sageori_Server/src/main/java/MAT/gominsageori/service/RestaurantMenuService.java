package MAT.gominsageori.service;

import MAT.gominsageori.domain.RestaurantMenu;
import MAT.gominsageori.repository.RestaurantMenuRepository;

import java.util.List;

public class RestaurantMenuService {
    private final RestaurantMenuRepository restaurantMenuRepository;

    public RestaurantMenuService(RestaurantMenuRepository restaurantMenuRepository) {
        this.restaurantMenuRepository = restaurantMenuRepository;
    }

    public RestaurantMenu addRestaurantMenu(RestaurantMenu restaurantMenu) {
        restaurantMenuRepository.save(restaurantMenu);
        return restaurantMenu;
    }

    public List<RestaurantMenu> findAllRestaurantMenuByRestaurantId(Long id) {
        return restaurantMenuRepository.findAllByRestaurantId(id);
    }

    public List<RestaurantMenu> findAllRestaurantMenuByMenuId(Long id) {
        return restaurantMenuRepository.findAllByMenuId(id);
    }
}
