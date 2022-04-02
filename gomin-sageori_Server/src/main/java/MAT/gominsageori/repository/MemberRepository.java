package MAT.gominsageori.repository;

import MAT.gominsageori.domain.Member;

import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(String id);
    Member update(Member member);
    void delete(Member member);
}
