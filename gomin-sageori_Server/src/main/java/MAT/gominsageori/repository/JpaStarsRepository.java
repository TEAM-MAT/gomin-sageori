package MAT.gominsageori.repository;

import MAT.gominsageori.domain.Stars;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaStarsRepository implements StarsRepository{

    private final EntityManager em;

    @Autowired
    public JpaStarsRepository(EntityManager em) {this.em = em;}

    @Override
    public void save(Stars stars) throws Exception {
        try {
            em.persist(stars);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void update(Stars stars) throws Exception {
        try {
            em.merge(stars);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public Optional<Stars> findByMemberAndRestaurant(Long memberPK, Long restaurantPK) {
        Optional<Stars> result = em.createQuery(
                "SELECT s " +
                        "FROM Stars s " +
                        "WHERE s.userPK = :memberPK " +
                        "AND s.restaurantPK = :restaurantPK",
                Stars.class
        )
                .setParameter("memberPK",memberPK)
                .setParameter("restaurantPK",restaurantPK)
                .getResultStream()
                .findFirst();
        return result;
    }

    @Override
    public List<Stars> findByMember(Long memberPK) {
        List<Stars> result = em.createQuery(
                        "SELECT s " +
                                "FROM Stars s " +
                                "WHERE s.userPK = :memberPK ",
                        Stars.class
                )
                .setParameter("memberPK",memberPK)
                .getResultList();
        return result;
    }

    @Override
    public List<Stars> findByRestaurant(Long restaurantPK) {
        List<Stars> result = em.createQuery(
                        "SELECT s " +
                                "FROM Stars s " +
                                "WHERE s.restaurantPK = :restaurantPK ",
                        Stars.class
                )
                .setParameter("restaurantPK",restaurantPK)
                .getResultList();
        return result;
    }

    @Override
    public Float getAverageStars(Long restaurantPK) {
        Double result = em.createQuery(
                "SELECT AVG(s.stars) " +
                        "FROM Stars s " +
                        "WHERE s.restaurantPK = :restaurantPK ",
                Double.class
        )
                .setParameter("restaurantPK", restaurantPK)
                .getSingleResult();
        return result.floatValue();
    }
}
