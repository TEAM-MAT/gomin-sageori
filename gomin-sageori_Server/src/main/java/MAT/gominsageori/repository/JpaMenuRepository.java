package MAT.gominsageori.repository;

import MAT.gominsageori.domain.Member;
import MAT.gominsageori.domain.Menu;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMenuRepository implements MenuRepository{

    private final EntityManager em;

    public JpaMenuRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Menu save(Menu menu) {
        em.persist(menu);
        return menu;
    }

    @Override
    public Optional<Menu> findById(Long id) {
        Menu menu = em.find(Menu.class, id);
        return Optional.ofNullable(menu);
    }

    @Override
    public Optional<Menu> findByName(String name) {
        List<Menu> result = em.createQuery("SELECT m " +
                        "FROM Menu m " +
                        "WHERE m.name = :name", Menu.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Menu> findAll() {
        return em.createQuery("SELECT m " +
                        "FROM Member m", Menu.class)
                .getResultList();
    }

    @Override
    public Menu update(Menu menuParam) {
        Menu menu = em.find(Menu.class, menuParam.getId());
        menu.setName(menuParam.getName());
        menu.setSoup(menuParam.isSoup());
        menu.setHot(menu.isHot());
        menu.setSpicy(menu.isSpicy());
        menu.setSweet(menu.isSweet());
        menu.setMeat(menu.isMeat());
        menu.setRice(menu.isRice());
        menu.setNoodle(menu.isNoodle());
        menu.setBread(menu.isBread());
        return menu;
    }

    @Override
    public void delete(Menu menuParam) {
        Menu menu = em.find(Menu.class, menuParam.getId());
        em.remove(menu);
    }
}
