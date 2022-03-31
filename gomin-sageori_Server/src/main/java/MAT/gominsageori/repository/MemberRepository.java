package MAT.gominsageori.repository;

import MAT.gominsageori.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(String id);
    //Member delete(Member member);
}
