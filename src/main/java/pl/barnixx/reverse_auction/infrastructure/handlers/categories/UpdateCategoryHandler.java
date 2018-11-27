package pl.barnixx.reverse_auction.infrastructure.handlers.categories;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;
import pl.barnixx.reverse_auction.core.domain.Category;
import pl.barnixx.reverse_auction.infrastructure.commands.ICommandHandler;
import pl.barnixx.reverse_auction.infrastructure.commands.categories.UpdateCategory;
import pl.barnixx.reverse_auction.infrastructure.services.CategoryService;

import java.lang.reflect.Type;
import java.util.List;

@Component("UpdateCategoryHandler")
public class UpdateCategoryHandler implements ICommandHandler<UpdateCategory> {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public UpdateCategoryHandler(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void handle(UpdateCategory command) {
        Type attrList = new TypeToken<List<Category>>() {
        }.getType();
        Category category = Category.builder()
                .id(command.getId())
                .categoryName(command.getCategoryName())
                .categoryDescription(command.getCategoryDescription())
                .build();
        categoryService.update(category);
    }
}
