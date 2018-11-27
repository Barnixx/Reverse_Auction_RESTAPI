package pl.barnixx.reverse_auction.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.barnixx.reverse_auction.core.domain.User;
import pl.barnixx.reverse_auction.core.repositories.UserRepository;
import pl.barnixx.reverse_auction.infrastructure.DTO.UserDTO;
import pl.barnixx.reverse_auction.infrastructure.services.UserService;
import pl.barnixx.reverse_auction.infrastructure.services.UserServiceImpl;

import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void when_given_exists_id_then_return_user() {
        //given
        User user1 = User.builder()
                .id(1L)
                .username("User1")
                .password("secret")
                .email("user1@email.com")
                .build();
        when(userRepository.findById(anyLong())).thenReturn(ofNullable(user1));
        //when
        UserDTO resultUser = userService.findById(1L);
        log.info(resultUser.toString());
        //then
        verify(userRepository, times(1)).findById(anyLong());
        assert user1 != null;
        assertEquals(new ModelMapper().map(user1, UserDTO.class), resultUser);
    }

    @Test
    public void when_given_not_exists_id_then_return_null() {
        //given
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        //when
        UserDTO resultUser = userService.findById(2L);
        //then
        verify(userRepository, times(1)).findById(anyLong());
        assertNull(resultUser);
    }

    @Test
    public void when_given_exists_username_then_return_true() {
        //given
        when(userRepository.existsByUsername(anyString())).thenReturn(true);
        //when
        boolean result = userService.isUsernameExists("user");
        //then
        verify(userRepository, times(1)).existsByUsername(anyString());
        assertTrue(result);
    }

    @Test
    public void when_given_not_exists_username_then_return_false() {
        //given
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        //when
        boolean result = userService.isUsernameExists("user");
        //then
        verify(userRepository, times(1)).existsByUsername(anyString());

        assertFalse(result);
    }

    @Test
    public void when_given_exists_email_then_return_true() {
        //given
        when(userRepository.existsByEmail(anyString())).thenReturn(true);
        //when
        boolean result = userService.isEmailExists("email");
        //then
        verify(userRepository, times(1)).existsByEmail(anyString());
        assertTrue(result);
    }

    @Test
    public void when_given_not_exists_email_then_return_false() {
        //given
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        //when
        boolean result = userService.isEmailExists("email");
        //then
        verify(userRepository, times(1)).existsByEmail(anyString());
        assertFalse(result);
    }


    @Test
    public void when_given_user_then_register() {
        //given
        User user = User.builder()
                .username("user1")
                .password("secret")
                .email("user1@email.com")
                .build();
        when(userRepository.save(any(User.class))).thenReturn(user);
        //when
        userService.Register(user.getEmail(), user.getUsername(), user.getPassword());
        UserDTO userDTO = userService.findByName(user.getUsername());
        //then
        verify(userRepository, times(1)).save(user);
    }
}
