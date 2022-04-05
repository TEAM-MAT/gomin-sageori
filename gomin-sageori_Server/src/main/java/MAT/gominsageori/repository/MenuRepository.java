package MAT.gominsageori.repository;

import MAT.gominsageori.domain.Member;
import MAT.gominsageori.domain.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuRepository {
    Menu save(Menu menu);
    Optional<Menu> findById(Long id);
    Optional<Menu> findByName(String name);
    public List<Menu> findAll();
    Menu update(Menu menu);
    void delete(Menu menu);
}
