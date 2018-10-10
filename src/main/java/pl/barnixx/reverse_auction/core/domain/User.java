package pl.barnixx.reverse_auction.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import pl.barnixx.reverse_auction.infrastructure.validators.ConfirmPassword;
import pl.barnixx.reverse_auction.infrastructure.validators.UniqueUserEmail;
import pl.barnixx.reverse_auction.infrastructure.validators.UniqueUserName;
import pl.barnixx.reverse_auction.infrastructure.validators.UserRegisterValidationGroup;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@ConfirmPassword(groups = UserRegisterValidationGroup.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @UniqueUserName(groups = UserRegisterValidationGroup.class)
    @NotBlank
    @Length(min = 5, max = 20)
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Hasło nie może być puste")
    @Column(length = 200)
    private String password;

    private int enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @UniqueUserEmail(message = "Podany email już istnieje", groups = UserRegisterValidationGroup.class)
    @NotBlank(message = "Email nie możę być pusty")
    @Email(message = "Wprowadź poprawny adres email")
    @Column(name = "user_email", length = 200)
    private String email;

    @Column(name = "first_name", length = 150)
    @Size(min = 2, max = 150, message = "Imie może mieć od {min} do {max} znaków")
    private String firstName;

    @Column(name = "last_name", length = 150)
    @Size(min = 2, max = 150, message = "Nazwisko możę mieć od {min} do {max} znaków")
    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;

    @Length(max = 50)
    @Column(name = "phone_number", length = 50)
    private String phoneNumber;
}
