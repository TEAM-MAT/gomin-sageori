package MAT.gominsageori.service;

import MAT.gominsageori.domain.Member;
import MAT.gominsageori.domain.Restaurant;
import MAT.gominsageori.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;


@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public String join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getUserId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByUserId(member.getUserId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public Optional<Member> findOneById(Long id) throws Exception {
        Optional<Member> member = memberRepository.findById(id);
        if (!member.isPresent()) {
            throw new NoSuchElementException("No data for that member");
        }
        return member;
    }

    public Optional<Member> findOneByUserId(String userId) {
        return memberRepository.findByUserId(userId);
    }

    public Optional<Member> findOneByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public void save(Member member) {
        memberRepository.save(member);
    }

    public String update(Member member) {
        if(memberRepository.findByUserId(member.getUserId()).isPresent()) {
            memberRepository.update(member);
            return member.getUserId();
        }
        else
            return null;
    }

    public Member saveFavorites(Member member, Set<Restaurant> restaurants) {
        memberRepository.setFavorites(member, restaurants);
        return member;
    }

    public Set<Restaurant> getFavoritesList(Member member) throws Exception {
        Set<Restaurant> favoritesList = memberRepository.getFavorites(member);
        if(favoritesList.size() == 0) {
            throw new NoSuchElementException("반환할 리스트가 없습니다.");
        }
        return favoritesList;
    }

    public Member deleteFavorites(Member member, Set<Restaurant> restaurants) {
        memberRepository.deleteFavorites(member,restaurants);
        return member;
    }

    public Member deleteAllFavorites(Member member) {
        memberRepository.deleteAllFavorites(member);
        return member;
    }

    public Member modifyFavorites(Member member, Set<Restaurant> addRestaurants, Set<Restaurant> deleteRestaurants) {
        memberRepository.setFavorites(member, addRestaurants);
        memberRepository.deleteFavorites(member, deleteRestaurants);
        return member;
    }

    public void delete(Member member) {
        if(memberRepository.findByUserId(member.getUserId()).isPresent()) {
            memberRepository.delete(member);
        }
    }

    public HashMap<String, String> pwdEncryption(String password) {
        String salt = BCrypt.gensalt();
        String encodedPassword = BCrypt.hashpw(password,salt);
        HashMap <String, String> payload = new HashMap<>();
        payload.put("salt", salt);
        payload.put("encodedPW", encodedPassword);
        return payload;
    }

    public String loginPwdEncryption(String password, String salt) {
        String encodedPassword = BCrypt.hashpw(password,salt);
        return encodedPassword;
    }
}
