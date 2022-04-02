package MAT.gominsageori.service;

import MAT.gominsageori.domain.Member;
import MAT.gominsageori.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given
        Member member1 = new Member();
        member1.setId("userId");
        member1.setName("userName");
        member1.setPwd("userPwd");
        //when
        String saveId = memberService.join(member1);
        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member1.getId()).isEqualTo(findMember.getId());
    }

    @Test
    void 중복_회원_예외() {
        Member member1 = new Member();
        member1.setId("userId");;
        member1.setName("userName");
        member1.setPwd("userPwd");
        Member member2 = new Member();
        member2.setId("userId");
        member2.setName("userName");
        member2.setPwd("userPwd");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 아이디입니다.");
    }

    @Test
    void 회원_정보_수정() {
        Member member1 = new Member();
        member1.setId("userId2");
        member1.setName("userName3");
        member1.setPwd("userPwd3");

        String saveId = memberService.Update(member1);

        Member findMember = memberService.findOne(saveId).get();
        assertThat(member1.getId()).isEqualTo(findMember.getId());
    }

    @Test
    void 회원_삭제() {
        Member member = new Member();
        member.setId("userId2");
        member.setName("userName2");
        member.setPwd("userPwd2");

        memberService.delete(member);
    }

}
