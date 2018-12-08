package pl.barnixx.reverse_auction.infrastructure.services;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pl.barnixx.reverse_auction.infrastructure.DTO.CategoryDTO;
import pl.barnixx.reverse_auction.infrastructure.events.RefreshCategoryEvent;

import java.util.List;

@Service
public class CategoryObserver {

    private final ApplicationEventPublisher eventPublisher;
    private final CategoryService categoryService;

    public CategoryObserver(ApplicationEventPublisher eventPublisher, CategoryService categoryService) {
        this.eventPublisher = eventPublisher;
        this.categoryService = categoryService;
    }

//    @Scheduled(fixedRate = 5000)
//    public void refresh() {
////        System.out.println("CategoryObserver");
//        List<CategoryDTO> categories = categoryService.getAll();
//        this.eventPublisher.publishEvent(new RefreshCategoryEvent(categories));
//    }

    public void refresh() {
        List<CategoryDTO> categories = categoryService.getAll();
        this.eventPublisher.publishEvent(new RefreshCategoryEvent(categories));
    }
}
