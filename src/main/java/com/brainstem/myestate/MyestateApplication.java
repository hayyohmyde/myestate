package com.brainstem.myestate;

import com.brainstem.myestate.model.Role;
import com.brainstem.myestate.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyestateApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(MyestateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role user = new Role();
		user.setName("ROLE_USER");
		roleRepository.save(user);

		Role admin = new Role();
		admin.setName("ROLE_ADMIN");
		roleRepository.save(admin);
	}
}
