package pl.barnixx.reverse_auction.infrastructure.events;

import pl.barnixx.reverse_auction.infrastructure.DTO.CategoryDTO;

import java.util.List;

public class RefreshCategoryEvent {

    private final List<CategoryDTO> categories;

    public RefreshCategoryEvent(List<CategoryDTO> categories) {
        this.categories = categories;
    }

    public List<CategoryDTO> getList() {
//        System.out.println("CategoryEvent");
        return categories;
    }

}
