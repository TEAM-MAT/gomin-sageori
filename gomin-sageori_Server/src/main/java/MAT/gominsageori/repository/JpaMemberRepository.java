package MAT.gominsageori.repository;

import MAT.gominsageori.domain.Member;
import MAT.gominsageori.domain.Restaurant;

import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.Set;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByUserId(String userId) {
        return em.createQuery("SELECT m " +
                "FROM Member m " +
                "WHERE m.userId = :userId")
                .setParameter("userId", userId)
                .getResultStream()
                .findAny();
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return em.createQuery("SELECT m " +
                "FROM Member m " +
                "WHERE m.email = :email",Member.class)
                .setParameter("email", email)
                .getResultStream()
                .findAny();
    }


    @Override
    public Member update(Member memberParam) {
        Member member = em.find(Member.class, memberParam.getId());
        member.setName(memberParam.getName());
        member.setPwd(memberParam.getPwd());
        member.setFavorites(memberParam.getFavoriteRestaurant());
        return member;
    }

    @Override
    public void delete(Member memberParam) {
        Member member = em.find(Member.class, memberParam.getId());
        em.remove(member);
    }

    @Override
    public Member setFavorites(Member memberParam, Set<Restaurant> newFavorites) {
        Member member = em.find(Member.class, memberParam.getId());
        Set<Restaurant> oldFavorites = member.getFavoriteRestaurant();
        oldFavorites.addAll(newFavorites);
        member.setFavorites(oldFavorites);
        return member;
    }

    @Override
    public Set<Restaurant> getFavorites(Member memberParam) {
        Member member = em.find(Member.class, memberParam.getId());
        Set<Restaurant> restaurants = member.getFavoriteRestaurant();
        return restaurants;
    }
}
