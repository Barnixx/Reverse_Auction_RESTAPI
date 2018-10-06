package pl.barnixx.reverse_auction.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 150)
    private String country;

    @Column(length = 150)
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(length = 200)
    private String street;

    @ManyToOne
    private User user;
}
