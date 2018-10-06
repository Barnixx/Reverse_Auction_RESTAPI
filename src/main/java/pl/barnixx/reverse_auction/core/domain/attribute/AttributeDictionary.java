package pl.barnixx.reverse_auction.core.domain.attribute;

import javax.persistence.*;

@Entity
@Table(name = "attribute_dictionary")
public class AttributeDictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attribute_dictionary_value")
    private String value;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;
}
