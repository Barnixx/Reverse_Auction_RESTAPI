package pl.barnixx.reverse_auction.infrastructure.services;

import pl.barnixx.reverse_auction.core.domain.User;

import java.util.List;

public interface IUserService {

    User findById(Long id);

    List<User> getAll();

    void update(User user);

    void delete(User user);

    void delete(Long id);

    boolean isEmailExists(String email);

    boolean isUsernameExists(String username);

    void Register(String email, String username, String password, String repeatPassword);
}
