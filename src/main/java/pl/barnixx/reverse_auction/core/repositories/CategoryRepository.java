package pl.barnixx.reverse_auction.core.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.barnixx.reverse_auction.core.domain.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryName(String name);

    boolean existsById(Long id);

    Page<Category> findAllByParentCategoryIsNull(Pageable pageable);
}
