package pl.barnixx.reverse_auction.infrastructure.services;

import pl.barnixx.reverse_auction.core.domain.User;
import pl.barnixx.reverse_auction.infrastructure.DTO.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO findById(Long id);

    UserDTO findByName(String username);

    List<UserDTO> getAll();

    void update(User user);

    void delete(User user);

    void delete(Long id);

    boolean isEmailExists(String email);

    boolean isUsernameExists(String username);

    void Register(String email, String username, String password);
}
