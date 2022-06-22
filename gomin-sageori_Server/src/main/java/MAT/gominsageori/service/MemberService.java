package MAT.gominsageori.service;

import MAT.gominsageori.domain.Member;
import MAT.gominsageori.repository.MemberRepository;
import javax.transaction.Transactional;
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

    public Optional<Member> findOne(String memberId) { return memberRepository.findByUserId(memberId); }

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
}
