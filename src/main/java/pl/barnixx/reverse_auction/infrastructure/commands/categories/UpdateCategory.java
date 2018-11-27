package pl.barnixx.reverse_auction.infrastructure.commands.categories;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import pl.barnixx.reverse_auction.infrastructure.DTO.AttributeGroupDTO;
import pl.barnixx.reverse_auction.infrastructure.commands.ICommand;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class UpdateCategory implements ICommand {

    @JsonIgnore
    private Long id;
    @NotBlank
    @Length(min = 2, max = 50)
    private String categoryName;

    @NotBlank
    @Length(max = 250)
    private String categoryDescription;

    private Long parentCategory;

    private List<AttributeGroupDTO> attributeGroups;

    public void setParentCategory(Long parentCategory) {
        if (parentCategory == 0) {
            this.parentCategory = null;
            return;
        }
        this.parentCategory = parentCategory;
    }
}
