package pl.barnixx.reverse_auction.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.barnixx.reverse_auction.core.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
