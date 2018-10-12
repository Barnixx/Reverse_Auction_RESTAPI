package pl.barnixx.reverse_auction.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import pl.barnixx.reverse_auction.api.controllers.UserController;
import pl.barnixx.reverse_auction.infrastructure.DTO.UserDTO;
import pl.barnixx.reverse_auction.infrastructure.commands.ICommandDispatcher;
import pl.barnixx.reverse_auction.infrastructure.commands.users.CreateUser;
import pl.barnixx.reverse_auction.infrastructure.services.IUserService;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTests {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private IUserService userService;

    @MockBean
    private ICommandDispatcher commandDispatcher;


    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void given_valid_id_should_be_exists() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .username("user1")
                .email("user1@email.com")
                .build();
        when(userService.findById(anyLong())).thenReturn(userDTO);

        log.info("Start test with user: " + userDTO.toString());
        mockMvc
                .perform(get("/api/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.username", is(userDTO.getUsername())))
                .andExpect(jsonPath("$.email", is(userDTO.getEmail())))
                .andDo(print());

        verify(userService, times(1)).findById(1L);
        verifyNoMoreInteractions(userService);
    }


    @Test
    public void given_invalid_id_should_be_not_exists() throws Exception {
        when(userService.findById(anyLong())).thenReturn(null);

        log.info("Start test with user: null.");
        mockMvc
                .perform(get("/api/users/{id}", 1L))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).findById(1L);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void given_valid_createUser_command_then_command_dispatch() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CreateUser command = new CreateUser();
        command.setUsername("user1");
        command.setEmail("user1@email.com");
        command.setPassword("secret");
        command.setRepeatPassword("secret");
        String jsonRequest = mapper.writeValueAsString(command);
        mockMvc
                .perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(jsonRequest))
                .andExpect(status().isCreated());
    }

    @Test
    public void given_invalid_createUser_command_then_command_not_dispatch() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CreateUser command = new CreateUser();
        command.setUsername("");
        command.setEmail("");
        command.setPassword("");
        command.setRepeatPassword("");
        String jsonRequest = mapper.writeValueAsString(command);
        mockMvc
                .perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }
}
