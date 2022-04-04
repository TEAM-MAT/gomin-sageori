package MAT.gominsageori.repository;

import MAT.gominsageori.domain.Address;
import MAT.gominsageori.domain.Restaurant;

import java.util.Optional;

public interface AddressRepository {
    Address save(Address address);
    Address update(Address address);
    void delete(Address address);
    Optional<Address> findById(Address address);
    Optional<Address> findByRestaurant(Restaurant restaurant);
}
