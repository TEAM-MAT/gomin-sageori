package MAT.gominsageori.repository;

import MAT.gominsageori.domain.Member;
import MAT.gominsageori.domain.Restaurant;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByUserId(String userId);
    Member update(Member member);
    void delete(Member member);
    Member setFavorites(Member member, List<Restaurant> restaurants);
    List<Restaurant> getFavorites(Member member);
    Optional<Member> findByEmail(String email);
}
