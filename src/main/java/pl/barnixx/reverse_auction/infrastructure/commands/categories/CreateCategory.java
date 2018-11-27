package pl.barnixx.reverse_auction.infrastructure.commands.categories;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import pl.barnixx.reverse_auction.infrastructure.commands.ICommand;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateCategory implements ICommand {

    @JsonIgnore
    private Long id;
    @NotBlank
    @Length(min = 2, max = 50)
    private String categoryName;

    @NotBlank
    @Length(max = 250)
    private String categoryDescription;

    private Long parentCategory;

    public void setParentCategory(Long parentCategory) {
        if (parentCategory == null) {
            this.parentCategory = 0L;
            return;
        }
        this.parentCategory = parentCategory;
    }
}
