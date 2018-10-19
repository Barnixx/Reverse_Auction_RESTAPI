package pl.barnixx.reverse_auction.core.domain.attribute;

import pl.barnixx.reverse_auction.core.domain.Category;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "attribute_group")
public class AttributeGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "attribute_group_id")
    private Long id;

    @Column(name = "attribute_group_name")
    private String name;

    @ManyToMany(mappedBy = "attributeGroups")
    private List<Category> categories;

    @ManyToMany(mappedBy = "attributeGroups")
    private List<Attribute> attributes;

}