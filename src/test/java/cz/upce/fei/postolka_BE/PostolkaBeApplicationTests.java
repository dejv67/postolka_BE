package cz.upce.fei.postolka_BE;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PostolkaBeApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Test
	public void testGetUserById() throws Exception {
		mockMvc.perform(get("/user/{id}", 2)) // Simulate a GET request to "/users/1"
				.andExpect(status().isOk()) // Expect HTTP 200 OK response
				.andExpect(jsonPath("$.name").value("Nela")); // Expect a specific JSON attribute value
	}

	@Test
	public void testGetRoomById() throws Exception {
		mockMvc.perform(get("/room/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("prizemi 1"));
	}




}
