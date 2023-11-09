package com.example.friendfinder.service;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.friendfinder.dto.LoginInputDto;
import com.example.friendfinder.dto.LoginOutputDto;
import com.example.friendfinder.dto.UserInputDto;
import com.example.friendfinder.dto.UserOutputDto;
import com.example.friendfinder.entity.Address;
import com.example.friendfinder.entity.User;
import com.example.friendfinder.repository.IAuthenticationRepository;

@ExtendWith(SpringExtension.class)
class AuthenticationServiceMockitoTest {
	
    // @InjectMock - Creates instance of a class and injects mocks that are created
	// with @Mock

	@InjectMocks
	AuthenticationServiceImpl authServ;

	// @MockBean - Creates Mock of a class or interface
	@MockBean
	IAuthenticationRepository authRepo;

	// Initialization of mock objects
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	@Disabled
	@Test
	void registrationTest()
	{
		UserInputDto newUser = new UserInputDto();
		newUser.setFirstName("Vivek");
		newUser.setLastName("S");
		newUser.setDob(LocalDate.parse("1997-10-04"));
		newUser.setEmail("vivek@gmail.com");
		newUser.setCity("Trivandrum");
		newUser.setCollege("NITC");
		newUser.setCountry("India");
		newUser.setMobile("9087658908");
		newUser.setPassword("vivek12");
		newUser.setPincode("890987");
		newUser.setRole("User");
		newUser.setStatus("Active");
		newUser.setState("Kerala");
		newUser.setUsername("vivek6");
		
		Address add = new Address();
		add.setCity(newUser.getCity());
		add.setCountry(newUser.getCountry());
		add.setPincode(newUser.getPincode());
		add.setState(newUser.getState());
		
		User user = new User();
		user.setUserId(1);
		user.setFirstName(newUser.getFirstName());
		user.setLastName(newUser.getLastName());
		user.setDob(newUser.getDob());
		user.setEmail(newUser.getEmail());
		user.setCollege(newUser.getCollege());
		user.setMobile(newUser.getMobile());
		user.setPassword(newUser.getPassword());
		user.setRole(user.getRole());
		user.setUsername(newUser.getUsername());
		user.setAddress(add);
		
		Mockito.when(authRepo.save(user)).thenReturn(user);
		
		UserOutputDto userDto = authServ.register(newUser);
		assertEquals("Vivek",userDto.getFirstName());
		assertEquals("User",userDto.getRole());
	 }
	
	@Test
	void logoutTest()
	{
		String email = "akash@gmail.com";
		String dto = authServ.logout(email);
		
		assertEquals("logout successfull",dto);	
	}

	@Test
	void loginTest()
	{
		LoginInputDto login = new LoginInputDto();
		login.setEmail("vivek@gmail.com");
		login.setPassword("vivek@18");
		login.setRole("User");
		
		LoginOutputDto logDto = authServ.login(login);
		assertEquals("vivek@gmail.com",logDto.getEmail());
		assertEquals(true,logDto.isLogin());
	}

}
