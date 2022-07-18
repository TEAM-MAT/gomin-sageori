package MAT.gominsageori.service;

import MAT.gominsageori.domain.Member;
import MAT.gominsageori.domain.Restaurant;
import MAT.gominsageori.repository.MemberRepository;
import MAT.gominsageori.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Autowired RestaurantService restaurantService;
    @Autowired RestaurantRepository restaurantRepository;

    @Test
    void 회원가입() {
        //given
        Member member1 = new Member();
        member1.setUserId("userId1");
        member1.setName("userName1");
        member1.setPwd("userPwd1");
        //when
        String saveId = memberService.join(member1);
        //then
    }

    @Test
    void 중복_회원_예외() {
        Member member1 = new Member();
        member1.setUserId("userId");;
        member1.setName("userName");
        member1.setPwd("userPwd");
        Member member2 = new Member();
        member2.setUserId("userId");
        member2.setName("userName");
        member2.setPwd("userPwd");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void 회원_정보_수정() {
        Member member1 = new Member();
        //member1.setPid("userId2");
        member1.setName("userName3");
        member1.setPwd("userPwd3");

        String saveId = memberService.update(member1);

        //if(saveId != null) {
            //Member findMember = memberService.findOne(saveId).get();
            //assertThat(member1.getId()).isEqualTo(findMember.getId());
        //}
    }

    @Test
    void 회원_삭제() {
        Member member = new Member();
        //member.setPid("userId2");
        member.setName("userName2");
        member.setPwd("userPwd2");

        memberService.delete(member);
    }

    @Test
    //@Commit
    void 즐겨찾기_추가() {
        Optional<Member> member = null;
        try {
            member = memberService.findOneByUserId("sam");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            List<Long> selectedIds = new ArrayList<>();
            Set<Restaurant> restaurants = restaurantService.findRestaurantInfoFromListById(selectedIds);
            memberService.saveFavorites(member.get(),restaurants);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void 즐겨찾기_조회() {
        Optional<Member> member = null;
        try {
            member = memberService.findOneById(2L);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            Set<Restaurant> restaurants = memberService.getFavoritesList(member.get());
            for(Restaurant iter : restaurants) {
                System.out.println(iter.getName());
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    //@Commit
    void 즐겨찾기_삭제() {
        Optional<Member> member = null;
        try {
            member = memberService.findOneById(5L);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            List<Long> selectedIds = new ArrayList<>();
            Set<Restaurant> restaurants = restaurantService.findRestaurantInfoFromListById(selectedIds);
            memberService.deleteFavorites(member.get(),restaurants);
            System.out.println("삭제되었음");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
