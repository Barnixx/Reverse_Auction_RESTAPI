package pl.barnixx.reverse_auction.infrastructure.services;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.barnixx.reverse_auction.core.domain.Category;
import pl.barnixx.reverse_auction.core.repositories.CategoryRepository;
import pl.barnixx.reverse_auction.infrastructure.DTO.CategoryDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDTO getDTObyId(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(this::mapToDTO).orElse(null);
    }

    @Override
    public Category getById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }

    @Override
    public CategoryDTO findByName(String name) {
        Optional<Category> category = categoryRepository.findByCategoryName(name);
        return category.map(this::mapToDTO).orElse(null);
    }

    @Override
    public Page<CategoryDTO> getAll(Pageable pageable, boolean root) {
        Page<Category> categoryList;
        if (root) {
            categoryList = categoryRepository.findAllByParentCategoryIsNull(pageable);
        } else {
            categoryList = categoryRepository.findAll(pageable);
        }
        return categoryList.map(cat -> modelMapper.map(cat, CategoryDTO.class));
    }

    @Override
    @Cacheable(value = "categories")
    public List<CategoryDTO> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(cat -> modelMapper.map(cat, CategoryDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    @CacheEvict(value = "categories", allEntries = true)
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void update(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return categoryRepository.existsById(id);
    }

    private CategoryDTO mapToDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }
}
