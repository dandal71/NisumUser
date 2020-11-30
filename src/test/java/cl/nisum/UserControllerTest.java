package cl.nisum;

import static org.mockito.Mockito.*;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import cl.nisum.model.entity.User;
import cl.nisum.model.service.UserService;

/**
 * Test usuario controller
 * 
 * @author Daniel E. Dalmagro
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	@MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    @DisplayName("GET /api/v1/user/private/list")
    void testGeUsersOk() throws Exception {
    	User user1 = new User();
    	user1.setName("Nombre Test1");
    	user1.setUsername("utest11");
    	user1.setEmail("usertes1@nisum.cl");
    	
    	User user2 = new User();
    	user1.setName("Nombre Test2");
    	user1.setUsername("utest22");
    	user1.setEmail("usertes2@nisum.cl");
    	
    	doReturn(Arrays.asList(user1, user2)).when(userService).findAll();
    
    	mockMvc.perform(get("/api/v1/user/private/list"))
          .andExpect(status().isOk())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON))
          .andExpect(header().string(HttpHeaders.LOCATION, "/api/v1/user/private/list"))
          .andExpect((ResultMatcher) jsonPath("$", hasSize(2)))
          .andExpect(jsonPath("$[0].id",  is(notNullValue())))
          .andExpect(jsonPath("$[0].name", is("Widget Name")))
          .andExpect(jsonPath("$[0].username", is("utest11")))
          .andExpect(jsonPath("$[0].email", is("usertes1@nisum.cl")));
    }
    
    @Test
    @DisplayName("POST /api/v1/user/create")
    void testCreateUserOk() throws Exception {
    	UUID id = UUID.randomUUID();
    	User user1 = new User();
    	user1.setId(id);
    	user1.setName("Nombre Test1");
    	user1.setUsername("utest11");
    	user1.setEmail("usertes1@nisum.cl");
    	
    	when(userService.save(user1)).thenReturn(user1);
    	
    	RequestBuilder request = MockMvcRequestBuilders.post("/api/v1/user/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(user1));
    
    	mockMvc.perform(request)
          .andExpect(status().isCreated())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("user.id",  is(notNullValue())));
    }
    
    @Test
    @DisplayName("GET /api/v1/user/create")
    void testBadMethod() throws Exception {
    	UUID id = UUID.randomUUID();
    	User user1 = new User();
    	user1.setId(id);
    	user1.setName("Nombre Test1");
    	user1.setUsername("utest11");
    	user1.setEmail("usertes1@nisum.cl");
    	
    	doReturn(user1).when(userService).save(user1);
    
    	mockMvc.perform(post("/api/v1/user/create"))
          .andExpect(status().is4xxClientError());
    }
    
    private String toJson(User user) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		return ow.writeValueAsString(user);
	}
}
