package pl.barnixx.reverse_auction.core.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import pl.barnixx.reverse_auction.core.domain.attribute.AttributeValue;
import pl.barnixx.reverse_auction.infrastructure.validators.NewAuctionValidationGroup;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auctions")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "{validators.auction.name.notnull}")
    @Length(min = 10, max = 100)
    @Column(name = "auction_name", length = 100)
    private String name;

    @NotBlank
    @Length(max = 800)
    @Column(name = "auction_description", columnDefinition = "TEXT", length = 800)
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @Setter(AccessLevel.NONE)
    @Column(name = "auction_created", updatable = false)
    private LocalDateTime created;

    @Setter(AccessLevel.NONE)
    @Column(name = "auction_updated")
    private LocalDateTime updated;

    @NotNull(message = "Data nie może być pusta")
    @Column(name = "auction_start_time")
    private LocalDateTime startTime;

    @FutureOrPresent(message = "Podaj przyszłą datę", groups = NewAuctionValidationGroup.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Data nie może być pusta")
    @Column(name = "auction_end_time")
    private LocalDate endTime;

    @Column(name = "auction_is_active")
    private byte isActive;

    @Column(name = "auction_view")
    private Long view;

    @OneToOne(orphanRemoval = true)
    private Offer winOffer;

    @Column(name = "auction_imageUrl")
    private String imageUrl;


    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "auction_attribute_value",
            joinColumns = {@JoinColumn(name = "auction_id")},
            inverseJoinColumns = {@JoinColumn(name = "attribute_value_id")}
    )
    private List<AttributeValue> attributeValues;

    @PrePersist
    void created() {
        this.view = 0L;
        this.created = LocalDateTime.now();
    }

    @PreUpdate
    void updated() {
        this.updated = LocalDateTime.now();
    }
}