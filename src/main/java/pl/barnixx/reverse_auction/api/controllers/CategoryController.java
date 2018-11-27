package pl.barnixx.reverse_auction.api.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.barnixx.reverse_auction.infrastructure.DTO.CategoryDTO;
import pl.barnixx.reverse_auction.infrastructure.commands.ICommandDispatcher;
import pl.barnixx.reverse_auction.infrastructure.commands.categories.CreateCategory;
import pl.barnixx.reverse_auction.infrastructure.commands.categories.UpdateCategory;
import pl.barnixx.reverse_auction.infrastructure.services.CategoryService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController extends BaseController {

    private final CategoryService categoryService;

    public CategoryController(ICommandDispatcher commandDispatcher, CategoryService categoryService) {
        super(commandDispatcher);
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable long id) {
        CategoryDTO categoryDTO = categoryService.getDTObyId(id);
        if (categoryDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> getAll(Pageable pageable) {
        Page<CategoryDTO> categoriesDTO = categoryService.getAll(pageable);
        if (categoriesDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoriesDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody CreateCategory createCategory, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        commandDispatcher.Dispatch(createCategory);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@Valid @RequestBody UpdateCategory updateCategory, @PathVariable long id) {

        if (categoryService.isExists(id)) {
            updateCategory.setId(id);
            commandDispatcher.Dispatch(updateCategory);
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id) {
        categoryService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
