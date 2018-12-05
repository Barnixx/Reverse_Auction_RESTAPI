package pl.barnixx.reverse_auction.api.controllers;

import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import pl.barnixx.reverse_auction.infrastructure.DTO.CategoryDTO;
import pl.barnixx.reverse_auction.infrastructure.commands.ICommandDispatcher;
import pl.barnixx.reverse_auction.infrastructure.commands.categories.CreateCategory;
import pl.barnixx.reverse_auction.infrastructure.commands.categories.UpdateCategory;
import pl.barnixx.reverse_auction.infrastructure.events.RefreshCategoryEvent;
import pl.barnixx.reverse_auction.infrastructure.services.CategoryService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@CrossOrigin
@RestController
@RequestMapping("/api/category")
public class CategoryController extends BaseController {

    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();
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

    @GetMapping(
            produces = "application/json",
            params = {"root"}
    )
    public ResponseEntity<Page<CategoryDTO>> getAll(
            Pageable pageable,
            @RequestParam(value = "root", required = false, defaultValue = "false") String root) {

        Page<CategoryDTO> categoriesDTO = categoryService.getAll(pageable, root.equals("true"));
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

    @GetMapping("/stream")
    public SseEmitter streamAllCategories(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-store");
        SseEmitter emitter = new SseEmitter(30000L);
        this.emitters.add(emitter);

        emitter.onCompletion(() -> this.emitters.remove(emitter));
        emitter.onTimeout(() -> this.emitters.remove(emitter));

        return emitter;
    }

    @GetMapping("/stream-sse-mvc")
    public SseEmitter streamSseMvc() {
        SseEmitter emitter = new SseEmitter();
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        sseMvcExecutor.execute(() -> {
            try {
                for (int i = 0; true; i++) {
                    SseEmitter.SseEventBuilder event = SseEmitter.event()
                            .data("SSE MVC - " + LocalTime.now().toString())
                            .id(String.valueOf(i))
                            .name("sse event - mvc");
                    emitter.send(event);
                    Thread.sleep(1000);
                }
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });
        return emitter;
    }

    @EventListener
    public void onRefreshCategories(RefreshCategoryEvent refreshCategoryEvent) {
        List<SseEmitter> deadEmitters = new ArrayList<>();
        this.emitters.forEach(emitter -> {
            try {
                emitter.send(refreshCategoryEvent.getList());
            } catch (Exception e) {
                deadEmitters.add(emitter);
            }
        });

        this.emitters.removeAll(deadEmitters);
    }
}
