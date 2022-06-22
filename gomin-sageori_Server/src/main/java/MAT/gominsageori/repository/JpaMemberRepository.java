package MAT.gominsageori.repository;

import MAT.gominsageori.domain.Member;
import MAT.gominsageori.domain.Restaurant;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

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
    public Optional<Member> findByUserId(String userId) {
        Member member = em.find(Member.class, userId);
        return Optional.ofNullable(member);
    }

    @Override
    public Member update(Member memberParam) {
        Member member = em.find(Member.class, memberParam.getId());
        member.setName(memberParam.getName());
        member.setPwd(memberParam.getPwd());
        member.SetFavorites(memberParam.getFavoriteRestaurant());
        return member;
    }

    @Override
    public void delete(Member memberParam) {
        Member member = em.find(Member.class, memberParam.getId());
        em.remove(member);
    }

    @Override
    public List<Restaurant> getFavorites(Member memberparam){
        List <Restaurant> result = memberparam.getFavoriteRestaurant();
        return result;
    }
}
