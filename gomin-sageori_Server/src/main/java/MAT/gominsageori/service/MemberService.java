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
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findById(member.getId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public Optional<Member> findOne(String memberId) { return memberRepository.findById(memberId); }

    public String update(Member member) {
        if(memberRepository.findById(member.getId()).isPresent()) {
            memberRepository.update(member);
            return member.getId();
        }
        else
            return null;
    }

    public void delete(Member member) {
        if(memberRepository.findById(member.getId()).isPresent()) {
            memberRepository.delete(member);
        }
    }
}
