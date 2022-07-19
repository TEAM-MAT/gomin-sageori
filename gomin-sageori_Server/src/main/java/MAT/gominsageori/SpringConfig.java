package MAT.gominsageori;

import MAT.gominsageori.repository.*;
import MAT.gominsageori.service.CustomUserDetailService;
import MAT.gominsageori.service.MemberService;
import MAT.gominsageori.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

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
        return new JpaMemberRepository(em);
    }

    @Bean
    public MenuService menuService() { return new MenuService(menuRepository()); }

    @Bean
    public MenuRepository menuRepository() {
        return new JpaMenuRepository(em);
    }

    @Bean
    public UserDetailsService userDetailsService() {return new CustomUserDetailService(memberRepository());}
}
