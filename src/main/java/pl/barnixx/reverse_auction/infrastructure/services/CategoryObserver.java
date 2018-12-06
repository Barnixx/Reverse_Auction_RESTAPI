package pl.barnixx.reverse_auction.infrastructure.services;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.barnixx.reverse_auction.infrastructure.DTO.CategoryDTO;
import pl.barnixx.reverse_auction.infrastructure.events.RefreshCategoryEvent;

@Service
public class CategoryObserver {

    public final ApplicationEventPublisher eventPublisher;
    public final CategoryService categoryService;

    public CategoryObserver(ApplicationEventPublisher eventPublisher, CategoryService categoryService) {
        this.eventPublisher = eventPublisher;
        this.categoryService = categoryService;
    }

    @Scheduled(fixedRate = 5000)
    public void refresh() {
        System.out.println("CategoryObserver");
        Iterable<CategoryDTO> categories = categoryService.getAll();
        this.eventPublisher.publishEvent(new RefreshCategoryEvent(categories));
    }
}
