package pl.barnixx.reverse_auction.infrastructure.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.barnixx.reverse_auction.core.domain.User;
import pl.barnixx.reverse_auction.infrastructure.DTO.UserDTO;

import java.util.Optional;

public interface UserService {

    Optional<UserDTO> findById(Long id);

    UserDTO findByName(String username);

    Page<UserDTO> getAll(Pageable pageable);

    void update(User user);

    void delete(User user);

    void delete(Long id);

    boolean isEmailExists(String email);

    boolean isUsernameExists(String username);

    void Register(String email, String username, String password);
}
