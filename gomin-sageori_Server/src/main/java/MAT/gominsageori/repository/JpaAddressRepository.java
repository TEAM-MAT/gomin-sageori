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
        Address newAdd = em.find(Address.class , address.getId());
        newAdd.setCity(address.getCity());
        newAdd.setDistrict(address.getDistrict());
        newAdd.setRoad(address.getRoad());
        newAdd.setBuildingNum(address.getBuildingNumber());
        newAdd.setFloor(address.getFloor());
        newAdd.setLocation(address.getLocation());
        return newAdd;
    }


}
