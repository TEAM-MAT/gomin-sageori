package MAT.gominsageori;

import MAT.gominsageori.repository.JpaMemberRepository;
import MAT.gominsageori.repository.JpaRestaurantRepository;
import MAT.gominsageori.repository.MemberRepository;
import MAT.gominsageori.repository.MemoryMemberRepository;
import MAT.gominsageori.service.MemberService;
import MAT.gominsageori.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class SpringConfig {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        return new JpaMemberRepository(em);
    }

}
