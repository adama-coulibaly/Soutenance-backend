package com.apiRegion.springjwt;

import com.apiRegion.springjwt.repository.CategorieRepository;
import com.apiRegion.springjwt.repository.RoleRepository;
import com.apiRegion.springjwt.repository.TypeProdRepository;
import com.apiRegion.springjwt.repository.UserRepository;
import com.apiRegion.springjwt.repository.UserStatusRepository;
import com.apiRegion.springjwt.servicesImpl.EmailSenderService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
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
	private final CategorieRepository categorieRepository;
	private  final TypeProdRepository typeProdRepository;

	@Autowired
	PasswordEncoder encoder;
	private String password = "adama12345";
	@Autowired
	private EmailSenderService senderService;
	private final UserStatusRepository userStatusRepository;


	public SpringBootSecurityJwtApplication(RoleRepository roleRepository, UserRepository userRepository,
											CategorieRepository categorieRepository, TypeProdRepository typeProdRepository, UserStatusRepository userStatusRepository) {
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
		this.categorieRepository = categorieRepository;
		this.typeProdRepository = typeProdRepository;
		this.userStatusRepository = userStatusRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
	}

@Bean
FirebaseMessaging firebaseMessaging() throws IOException {
			GoogleCredentials googleCredentials = GoogleCredentials
					.fromStream(new
							ClassPathResource("google-services.json").getInputStream());
			FirebaseOptions firebaseOptions = FirebaseOptions.builder()
					.setCredentials(googleCredentials).build();
			FirebaseApp app =
					FirebaseApp.initializeApp(firebaseOptions,"My farmed");
			return FirebaseMessaging.getInstance(app);
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
		if(userStatusRepository.findAll().size() == 0){
			userStatusRepository.ajouterStatusUser();
		}
		if(categorieRepository.findAll().size() == 0){
			categorieRepository.ajouterCategorieProd();
		}
		if(typeProdRepository.findAll().size() == 0){
			typeProdRepository.ajouterTypeProduction();
		}

	//	senderService.sendSimpleEmail("coulibalyadamabekaye03@gmail.com","Mot de passe super admin","Bienvenue sur la plateforme my farmed. Nous avons le plaisir de vous annoncez votre mot de passe : "+password);


	}
}
