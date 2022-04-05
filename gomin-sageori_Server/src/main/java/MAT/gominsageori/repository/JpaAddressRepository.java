package MAT.gominsageori.repository;

import MAT.gominsageori.domain.Address;
import MAT.gominsageori.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaAddressRepository implements AddressRepository{
    public final EntityManager em;

    @Autowired
    public JpaAddressRepository(EntityManager em){
        this.em = em;
    }


    @Override
    public Address save(Address address) {
        em.persist(address);
        return address;
    }

    @Override
    public Address update(Address address) {
        Address newadd = em.find(Address.class , address.getId());
        newadd.setCity(address.getCity());
        newadd.setRestaurant(address.getRestaurant());
        newadd.setDistrict(address.getDistrict());
        newadd.setRoad(address.getRoad());
        newadd.setbuildingnum(address.getBuilding_number());
        newadd.setFloor(address.getFloor());
        return newadd;
    }


    @Override
    public Optional<Address> findAddByRestaurant(Restaurant restaurant) {
        List<Address>  add= em.createQuery("select a from Address a where a.restaurant = :restaurant", Address.class)
                .setParameter("restaurant" , restaurant)
                .getResultList();
        return add.stream().findAny();
    }
}
