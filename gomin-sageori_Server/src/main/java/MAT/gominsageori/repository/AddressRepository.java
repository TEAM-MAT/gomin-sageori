package MAT.gominsageori.repository;

import MAT.gominsageori.domain.Address;
import MAT.gominsageori.domain.Restaurant;

import java.util.Optional;

public interface AddressRepository {
    Address save(Address address);
    Address update(Address address);
}

//주소 삭제의 경우 식당이 삭제되면 cascading으로 삭제되게