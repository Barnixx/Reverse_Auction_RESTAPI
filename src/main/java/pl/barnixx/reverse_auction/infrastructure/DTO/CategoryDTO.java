package pl.barnixx.reverse_auction.infrastructure.DTO;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DTO
public class CategoryDTO {

    private Long id;
    private String categoryName;
    private String categoryDescription;

    @JsonIgnoreProperties(value = "subcategories", allowSetters = true)
    @JsonProperty(value = "parent_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private CategoryDTO parentCategory;

    private Set<CategoryDTO> subcategories;
    private List<AttributeGroupDTO> attributeGroups;

}
