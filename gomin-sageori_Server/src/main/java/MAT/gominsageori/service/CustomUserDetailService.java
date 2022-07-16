package MAT.gominsageori.service;

import MAT.gominsageori.domain.Member;
import MAT.gominsageori.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findByUserId(userId);
        if(member.isEmpty()) {
            throw new UsernameNotFoundException("no user found");
        } else {
            return member.get();
        }
    }
}
