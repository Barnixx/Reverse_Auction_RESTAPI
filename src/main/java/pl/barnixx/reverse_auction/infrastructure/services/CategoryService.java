package pl.barnixx.reverse_auction.infrastructure.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.barnixx.reverse_auction.core.domain.Category;
import pl.barnixx.reverse_auction.infrastructure.DTO.CategoryDTO;

public interface CategoryService {
    CategoryDTO getDTObyId(Long id);

    Category getById(Long id);

    CategoryDTO findByName(String name);

    Page<CategoryDTO> getAll(Pageable pageable);

    void createCategory(Category category);

    void update(Category category);

    void delete(Category category);

    void delete(Long id);

    boolean isExists(Long id);
}
