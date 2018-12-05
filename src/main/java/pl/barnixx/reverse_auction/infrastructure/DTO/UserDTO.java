package pl.barnixx.reverse_auction.infrastructure.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DTO
public class UserDTO {

    @JsonIgnore
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birth;
    private String phoneNumber;

}
