package com.apiRegion.springjwt;

import com.apiRegion.springjwt.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootSecurityJwtApplication implements CommandLineRunner {
	private final RoleRepository roleRepository;

	public SpringBootSecurityJwtApplication(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		if(roleRepository.findAll().size()==0){
			roleRepository.creationrole();
		}

	}
}
