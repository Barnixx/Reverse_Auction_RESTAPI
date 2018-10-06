package pl.barnixx.reverse_auction.infrastructure.services;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.barnixx.reverse_auction.core.domain.User;
import pl.barnixx.reverse_auction.core.repositories.IUserRepository;

import java.util.List;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository UserRepository) {
        this.userRepository = UserRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll(Sort.by("id"));
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean isUsernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public void Register(String email, String username, String password, String repeatPassword) {
        User user = User.builder()
                .email(email)
                .username(username)
                .password(password)
                .repeatPassword(repeatPassword)
                .build();
        userRepository.save(user);
    }

//    private User create(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRepeatPassword(user.getPassword());
//        user.setEnabled(1);
//        Role userRole = roleRepository.findByName("USER");
//        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
//        System.out.println(user);
//        userRepository.save(user);
//    }
}
