package MAT.gominsageori.service;

import MAT.gominsageori.domain.Member;
import MAT.gominsageori.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Optional;


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
