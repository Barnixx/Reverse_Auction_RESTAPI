package pl.barnixx.reverse_auction.core.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import pl.barnixx.reverse_auction.core.domain.attribute.AttributeGroup;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty(value = "id")
    private Long id;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "category_name", length = 50)
    @JsonProperty(value = "name")
    private String categoryName;

    @NotBlank
    @Length(max = 250)
    @Column(name = "category_description", columnDefinition = "TEXT", length = 200)
    private String categoryDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    @JsonIgnoreProperties(value = "children_categories", allowSetters = true)
    @JsonProperty(value = "parent_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Category parentCategory;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(
            mappedBy = "parentCategory",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JsonProperty(value = "children_categories")
    private Set<Category> subcategories;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "categories_attribute_group",
            joinColumns = {@JoinColumn(name = "category_id")},
            inverseJoinColumns = {@JoinColumn(name = "attribute_group_id")}
    )
    private List<AttributeGroup> attributeGroups;
}
