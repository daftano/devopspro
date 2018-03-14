package lt.devopspro;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.fasterxml.jackson.databind.ObjectMapper;
import lt.devopspro.model.LoginForm;
import lt.devopspro.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void postSignUp() throws Exception {
        User user = new User(null, "Tom5", "test123", "Tom5", "Don5", "tom@5don.com");
        mvc.perform(MockMvcRequestBuilders.post("/signup", user)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"status\":\"OK\",\"message\":\"5\"}")));
    }

    @Test
    public void postSignUp2() throws Exception {
        User user = new User(null, "Tom4", "test123", "Tom4", "Don4", "tom@4don.com");
        mvc.perform(MockMvcRequestBuilders.post("/signup")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"status\":\"ERROR\",\"message\":\"Username allready in use\"}")));
    }

    @Test
    public void postLogin() throws Exception {

        LoginForm loginForm = new LoginForm("Tom4", "test123");

        mvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(loginForm))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":3,\"userName\":\"Tom4\",\"password\":null,\"firstName\":\"Tom4\",\"lastName\":\"Don4\",\"email\":\"tom@4don.com\"}")));

    }
    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}