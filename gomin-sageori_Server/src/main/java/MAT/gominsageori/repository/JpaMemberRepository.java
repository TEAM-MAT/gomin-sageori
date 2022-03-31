package MAT.gominsageori.repository;

import MAT.gominsageori.domain.Member;

import javax.persistence.EntityManager;
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
    public Optional<Member> findById(String id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    /*@Override
    public Member delete(Member member) {

    }*/
}
