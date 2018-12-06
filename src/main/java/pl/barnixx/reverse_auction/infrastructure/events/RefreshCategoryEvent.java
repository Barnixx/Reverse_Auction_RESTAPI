package pl.barnixx.reverse_auction.infrastructure.events;

import pl.barnixx.reverse_auction.infrastructure.DTO.CategoryDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RefreshCategoryEvent {

    private final Iterable<CategoryDTO> categories;

    public RefreshCategoryEvent(Iterable<CategoryDTO> categories) {
        this.categories = categories;
    }

    public List<CategoryDTO> getList() {
        System.out.println("CategoryEvent");
        return StreamSupport
                .stream(categories.spliterator(), false)
                .collect(Collectors.toList());
    }
}
