package pl.barnixx.reverse_auction.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.barnixx.reverse_auction.core.domain.User;
import pl.barnixx.reverse_auction.core.repositories.IUserRepository;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IUserRepository userRepository;

    @Test
    public void find_by_id_then_return_user() {
        //given
        User user = User.builder()
                .username("User1")
                .password("secret")
                .email("user1@email.com")
                .build();
        entityManager.persist(user);
        //when
        Optional<User> result = userRepository.findById(1L);
        //then
        assertTrue(result.isPresent());
        assertEquals(result.get().getUsername(), user.getUsername());
    }

    @Test
    public void given_not_exists_id_then_return_empty() {
        //given
        User user = User.builder()
                .username("User1")
                .password("secret")
                .email("user1@email.com")
                .build();
        entityManager.persist(user);
        //when
        Optional<User> result = userRepository.findById(2L);
        //then
        assertEquals(result, Optional.empty());
    }

    @Test
    public void given_exists_email_then_return_true() {
        //given
        String email = "user1@email.com";
        User user = User.builder()
                .username("User1")
                .password("secret")
                .email(email)
                .build();
        entityManager.persist(user);
        //then
        assertTrue(userRepository.existsByEmail(email));
    }

    @Test
    public void given_not_exists_email_then_return_false() {
        //given
        String email = "user1@email.com";
        //then
        assertFalse(userRepository.existsByEmail(email));
    }

    @Test
    public void given_exists_username_then_return_true() {
        //given
        User user = User.builder()
                .username("User1")
                .password("secret")
                .email("user1@email.com")
                .build();
        entityManager.persist(user);
        //then
        assertTrue(userRepository.existsByUsername(user.getUsername()));
    }

    @Test
    public void given_not_exists_username_then_return_false() {
        //given
        User user = User.builder()
                .username("User1")
                .password("secret")
                .email("user1@email.com")
                .build();
        entityManager.persist(user);
        //then
        assertFalse(userRepository.existsByUsername("XXX"));
    }
}
