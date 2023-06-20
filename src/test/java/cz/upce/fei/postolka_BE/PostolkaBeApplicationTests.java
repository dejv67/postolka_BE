package cz.upce.fei.postolka_BE;

import cz.upce.fei.postolka_BE.domain.Role;
import cz.upce.fei.postolka_BE.domain.User;
import cz.upce.fei.postolka_BE.repository.UserRepository;
import cz.upce.fei.postolka_BE.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
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


	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;
	@Test
	public void integrationTestCreateUser() throws Exception {

		User user = new User();
		user.setName("Lada");
		user.setSurname("Tester");
		user.setEmail("lada@tester.com");
		user.setPassword("test");
		user.setRole(Role.USER);
		user.setModifDate(LocalDateTime.now());

		userService.create(user);

		User savedUser = userRepository.findById(user.getId()).orElse(null);

		// Assert that the user was saved correctly
		assertNotNull(savedUser);
		assertEquals("Lada", savedUser.getName());
		assertEquals("lada@tester.com", savedUser.getEmail());
	}








}
