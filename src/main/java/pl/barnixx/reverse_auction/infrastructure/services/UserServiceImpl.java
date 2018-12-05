package pl.barnixx.reverse_auction.infrastructure.services;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.barnixx.reverse_auction.core.domain.User;
import pl.barnixx.reverse_auction.core.repositories.UserRepository;
import pl.barnixx.reverse_auction.infrastructure.DTO.UserDTO;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository UserRepository, ModelMapper modelMapper) {
        this.userRepository = UserRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        Optional<User> optUser = userRepository.findById(id);
        return optUser.map(user -> new ModelMapper().map(user, UserDTO.class));
    }

    @Override
    public UserDTO findByName(String username) {
        Optional<User> optUser = userRepository.findByUsername(username);
        return optUser.map(user -> new ModelMapper().map(user, UserDTO.class)).orElse(null);

    }

    @Override
    public Page<UserDTO> getAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(usr -> modelMapper.map(usr, UserDTO.class));
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
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
