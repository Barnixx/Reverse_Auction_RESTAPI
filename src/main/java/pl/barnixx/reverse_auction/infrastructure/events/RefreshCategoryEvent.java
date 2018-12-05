package pl.barnixx.reverse_auction.infrastructure.events;

import pl.barnixx.reverse_auction.core.domain.Category;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RefreshCategoryEvent {

    private final Iterable<Category> categories;

    public RefreshCategoryEvent(Iterable<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getList() {
        return StreamSupport
                .stream(categories.spliterator(), false)
                .collect(Collectors.toList());
    }
}
