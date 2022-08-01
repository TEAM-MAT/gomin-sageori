package MAT.gominsageori.repository;

import MAT.gominsageori.domain.Stars;

import java.util.List;
import java.util.Optional;


public interface StarsRepository {
    void save(Stars stars) throws Exception;

    void update(Stars stars) throws Exception;

    Optional<Stars> findByMemberAndRestaurant(Long memberPK, Long restaurantPK);

    List<Stars> findByMember(Long memberPK);

    List<Stars> findByRestaurant(Long restaurantPK);

    Float getAverageStars(Long restaurantPK);
}
