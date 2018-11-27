package pl.barnixx.reverse_auction.infrastructure.services;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.barnixx.reverse_auction.core.domain.User;
import pl.barnixx.reverse_auction.core.repositories.UserRepository;
import pl.barnixx.reverse_auction.infrastructure.DTO.UserDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository UserRepository) {
        this.userRepository = UserRepository;
    }

    @Override
    public UserDTO findById(Long id) {

        Optional<User> optUser = userRepository.findById(id);

        return optUser.map(user -> new ModelMapper().map(user, UserDTO.class)).orElse(null);

    }

    @Override
    public UserDTO findByName(String username) {
        Optional<User> optUser = userRepository.findByUsername(username);
        return optUser.map(user -> new ModelMapper().map(user, UserDTO.class)).orElse(null);

    }

    @Override
    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll(Sort.by("id"));
        return users.stream()
                .map(user -> new ModelMapper().map(user, UserDTO.class))
                .collect(Collectors.toList());
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
    public void Register(String email, String username, String password) {
        User user = User.builder()
                .email(email)
                .username(username)
                .password(password)
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
