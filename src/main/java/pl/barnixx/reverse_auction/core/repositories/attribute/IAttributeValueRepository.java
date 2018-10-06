package pl.barnixx.reverse_auction.core.repositories.attribute;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.barnixx.reverse_auction.core.domain.attribute.AttributeValue;

@Repository
public interface IAttributeValueRepository extends JpaRepository<AttributeValue, Long> {
}
