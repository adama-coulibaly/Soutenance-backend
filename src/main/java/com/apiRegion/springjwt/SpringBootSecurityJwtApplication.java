package com.apiRegion.springjwt;

import com.apiRegion.springjwt.repository.RoleRepository;
import com.apiRegion.springjwt.repository.UserRepository;
import com.apiRegion.springjwt.services.EmailSenderService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.aspectj.apache.bcel.util.ClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;

@Configuration
@EnableScheduling
@SpringBootApplication
public class SpringBootSecurityJwtApplication implements CommandLineRunner {
	private final RoleRepository roleRepository;
	private final UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;
	private String password = "adama12345";
	@Autowired
	private EmailSenderService senderService;


	public SpringBootSecurityJwtApplication(RoleRepository roleRepository, UserRepository userRepository) {
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
	}





	@Bean
	FirebaseMessaging firebaseMessaging() throws IOException {

		GoogleCredentials googleCredentials = GoogleCredentials
				.fromStream(new ClassPathResource("smsnotification-76ab1-firebase-adminsdk-3ocyr-ab4453f32d.json").getInputStream());
		FirebaseOptions firebaseOptions  =  FirebaseOptions.builder()
				.setCredentials(googleCredentials).build();
		FirebaseApp firebaseApp = FirebaseApp.initializeApp(firebaseOptions,"My-Farmed");
		return FirebaseMessaging.getInstance(firebaseApp);
	}




	@Override
	public void run(String... args) throws Exception {

		if(roleRepository.findAll().size()==0){
			roleRepository.creationrole();
		}
		if(userRepository.findAll().size() == 0){
			userRepository.creationadmin(encoder.encode(password));
			roleRepository.addRoleToUser();
		}

	//	senderService.sendSimpleEmail("coulibalyadamabekaye03@gmail.com","Mot de passe super admin","Bienvenue sur la plateforme my farmed. Nous avons le plaisir de vous annoncez votre mot de passe : "+password);


	}
}
