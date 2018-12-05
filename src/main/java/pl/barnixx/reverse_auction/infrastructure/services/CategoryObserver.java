package pl.barnixx.reverse_auction.infrastructure.services;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.barnixx.reverse_auction.core.domain.Category;
import pl.barnixx.reverse_auction.core.repositories.CategoryRepository;
import pl.barnixx.reverse_auction.infrastructure.events.RefreshCategoryEvent;

@Service
public class CategoryObserver {

    public final ApplicationEventPublisher eventPublisher;
    public final CategoryRepository categoryRepository;

    public CategoryObserver(ApplicationEventPublisher eventPublisher, CategoryRepository categoryRepository) {
        this.eventPublisher = eventPublisher;
        this.categoryRepository = categoryRepository;
    }

    @Scheduled(fixedRate = 5000)
    public void refresh() {
        Iterable<Category> categories = categoryRepository.findAll();
        this.eventPublisher.publishEvent(new RefreshCategoryEvent(categories));
    }
}
