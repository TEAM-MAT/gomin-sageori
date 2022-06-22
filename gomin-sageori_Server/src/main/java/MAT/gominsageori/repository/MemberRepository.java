package MAT.gominsageori.repository;

import MAT.gominsageori.domain.Member;
import MAT.gominsageori.domain.Restaurant;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByUserId(String userId);
    Member update(Member member);
    void delete(Member member);
    List<Restaurant> getFavorites(Member member);
}
