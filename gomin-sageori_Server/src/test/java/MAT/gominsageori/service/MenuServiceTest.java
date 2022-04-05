package MAT.gominsageori.service;

import MAT.gominsageori.domain.Member;
import MAT.gominsageori.domain.Menu;
import MAT.gominsageori.repository.MenuRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MenuServiceTest {
    @Autowired MenuService menuService;
    @Autowired MenuRepository menuRepository;

    @Test
    void 메뉴추가() {
        Menu menu = new Menu();
        menu.setId(1L);
        menu.setName("닭가슴살");

        menuService.addMenu(menu);

        Menu findMenu = menuService.findMenuById(menu.getId()).get();
        assertThat(findMenu).isEqualTo(menu);
    }

    @Test
    void 중복_메뉴_예외() {
        Menu menu = new Menu();
        menu.setId(1L);
        menu.setName("닭가슴살");
        Menu menu2 = new Menu();
        menu2.setId(1L);
        menu2.setName("닭가슴살");

        menuService.addMenu(menu);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> menuService.addMenu(menu2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 메뉴입니다.");
    }

    @Test
    void 메뉴수정() {
        Menu menu = new Menu();
        menu.setId(1L);
        menu.setName("닭가슴살");

        Long saveId = menuService.update(menu);

        if(saveId != null) {
            Menu findMenu = menuService.findMenuById(saveId).get();
            assertThat(menu.getId()).isEqualTo(findMenu.getId());
        }
    }

    @Test
    void 메뉴삭제() {
        Menu menu = new Menu();
        menu.setId(1L);
        menu.setName("닭가슴살");

        menuService.delete(menu);
    }
}
