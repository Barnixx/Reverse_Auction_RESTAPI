package pl.barnixx.reverse_auction.infrastructure.handlers.categories;

import org.springframework.stereotype.Component;
import pl.barnixx.reverse_auction.core.domain.Category;
import pl.barnixx.reverse_auction.infrastructure.commands.ICommandHandler;
import pl.barnixx.reverse_auction.infrastructure.commands.categories.CreateCategory;
import pl.barnixx.reverse_auction.infrastructure.services.CategoryObserver;
import pl.barnixx.reverse_auction.infrastructure.services.CategoryService;

@Component("CreateCategoryHandler")
public class CreateCategoryHandler implements ICommandHandler<CreateCategory> {

    private final CategoryService categoryService;
    private final CategoryObserver categoryObserver;

    public CreateCategoryHandler(CategoryService categoryService, CategoryObserver categoryObserver) {
        this.categoryService = categoryService;
        this.categoryObserver = categoryObserver;
    }

    @Override
    public void handle(CreateCategory command) {
        Category category = Category.builder()
                .categoryName(command.getCategoryName())
                .categoryDescription(command.getCategoryDescription())
                .parentCategory(command.getParentCategory() == 0L ? null : categoryService.getById(command.getParentCategory()))
                .build();

        categoryService.createCategory(category);
        categoryObserver.refresh();

    }
}
