package MAT.gominsageori.service;

import MAT.gominsageori.domain.Menu;
import MAT.gominsageori.repository.MenuRepository;

import java.util.Optional;

public class MenuService {
    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu addMenu(Menu menu) {
        validateDuplicateMenu(menu);
        menuRepository.save(menu);
        return menu;
    }

    private void validateDuplicateMenu(Menu menu) {
        menuRepository.findById(menu.getId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 메뉴입니다.");
                });
    }

    public Optional<Menu> findMenuById(Long id) {
        return menuRepository.findById(id);
    }

    public Optional<Menu> findMenuByName(String name) {
        return menuRepository.findByName(name);
    }

    public Long update(Menu menu) {
        if(menuRepository.findById(menu.getId()).isPresent()) {
            menuRepository.update(menu);
            return menu.getId();
        }
        else
            return null;
    }

    public void delete(Menu menu) {
        if(menuRepository.findById(menu.getId()).isPresent()) {
            menuRepository.delete(menu);
        }
    }
}
