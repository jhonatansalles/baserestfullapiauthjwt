package br.com.baserestfullapiauthjwt;

import br.com.baserestfullapiauthjwt.enums.RoleEnum;
import br.com.baserestfullapiauthjwt.model.User;
import br.com.baserestfullapiauthjwt.model.UserRole;
import br.com.baserestfullapiauthjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class BaseRestFullApiAuthJwtApplication {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(BaseRestFullApiAuthJwtApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			User user = new User();
			user.setId(1);
			user.setName("Administrador");
			user.setEmail("admin@admin.com.br");
			user.setUsername("admin");
			user.setPassword("admin");

			UserRole userRole = new UserRole();
			userRole.setId(1);
			userRole.setRole(RoleEnum.ROLE_ADMIN);
			userRole.setUser(user);

			user.setRoles(Arrays.asList(userRole));

			userService.save(user);

		};
	}
}
