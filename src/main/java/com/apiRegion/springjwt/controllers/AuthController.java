package com.apiRegion.springjwt.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.img.SaveImage;
import com.apiRegion.springjwt.models.*;
import com.apiRegion.springjwt.payload.response.JwtResponse;
import com.apiRegion.springjwt.repository.NotificationSenderRepository;
import com.apiRegion.springjwt.repository.UserStatusRepository;
import com.apiRegion.springjwt.security.EmailConstructor;
import com.apiRegion.springjwt.security.jwt.JwtUtils;
import com.apiRegion.springjwt.services.EmailSenderService;
import com.apiRegion.springjwt.services.UserModifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.apiRegion.springjwt.payload.request.LoginRequest;
import com.apiRegion.springjwt.payload.request.SignupRequest;
import com.apiRegion.springjwt.repository.RoleRepository;
import com.apiRegion.springjwt.repository.UserRepository;
import com.apiRegion.springjwt.services.UserDetailsImpl;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private EmailSenderService senderService;
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;


	@Autowired
	private UserModifier userModifier;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
    JwtUtils jwtUtils;


	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	EmailConstructor emailConstructor;
	@Autowired
	private UserStatusRepository userStatusRepository;
	@Autowired
	private NotificationSenderRepository notificationSenderRepository;


	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
				userDetails.getId(),
				userDetails.getUsername(),
				userDetails.getEmail(),
				userDetails.getNom(),
				userDetails.getPrenom(),
				userDetails.getAdresse(),
				userDetails.getAvatar(),
				userDetails.getStatusUser(),
				roles
		));
	}

	@PostMapping("/signup/{statusUser}")
	public ReponseMessage registerUser(@Validated @RequestBody SignupRequest signUpRequest, @PathVariable("statusUser")StatusUser statusUser) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			ReponseMessage message = new ReponseMessage("Utilisateur existe déja !",false);
			return message;
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			ReponseMessage message = new ReponseMessage("Email existe déjà !",false);
			return message;
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(),
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword())
				);

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Erreur: Role n'est pas trouvé."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Erreur: Role n'est pas trouvé."));
					roles.add(adminRole);

					break;
				case "superadmin":
					Role modRole = roleRepository.findByName(ERole.ROLE_SUPER_ADMIN)
							.orElseThrow(() -> new RuntimeException("Erreur: Role n'est pas trouvé."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Erreur: Role n'est pas trouvé."));
					roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		user.setAdresse(signUpRequest.getAdresse());
		user.setNom(signUpRequest.getNom());
		user.setPrenom(signUpRequest.getPrenom());
		user.setAvatar("http://127.0.0.1/FermesImages/avatar.jpg");
		user.setEtat(true);
		user.setStatusUser(statusUser);

		senderService.sendSimpleEmail(user.getEmail(), "Création de compte","Bonjour "+user.getNom()+" "+user.getPrenom()+" bienvenue sur la plateforme My Farme. My Farmed est une application de suivie des productions avicoles, de commercialisation des produits avicoles, de communication entre fermier et de formation dans le domaine de l'aviculture.");

		userRepository.save(user);
		ReponseMessage message = new ReponseMessage("Compte crée avec succès!",true);
		return message;
	}

@PutMapping("/modifier/{id}")
	public ReponseMessage Modifier(@Validated @RequestBody User loginRequest,@PathVariable("id") Long id){

		if(userRepository.findById(id)== null){
			ReponseMessage message = new ReponseMessage("Compte non trouvé",false);
			return message;
		}
		else{
			 return userModifier.Modifier(loginRequest,id );
		}
}
@PatchMapping("/modifierAvatar/{id}")
public ReponseMessage ModifierAvatar(@Param("file") MultipartFile file,
									 @PathVariable("id") Long id){
		User user = new User();
		String nomfile = StringUtils.cleanPath(file.getOriginalFilename());

		user.setAvatar(SaveImage.save(file, nomfile));

		return userModifier.ModifierAvatar(user, id);

	}



	//::::::::::::::::::::::::::::::REINITIALISER PASSWORD::::::::::::::::::::::::::::::::::::::::::://

	@PostMapping("/resetPassword/{email}")
	public ReponseMessage resetPassword(@PathVariable("email") String email) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			ReponseMessage message = new ReponseMessage("Mail incorrect !",false);
			return message;
		//	return new ResponseEntity<String>("Email non fourni", HttpStatus.BAD_REQUEST);
		}
		userModifier.resetPassword(user);
		ReponseMessage message = new ReponseMessage("Un mail vous à été envoyer contenant un code de réinitialisation",true);
		return message;

		//return new ResponseEntity<String>("Email envoyé!", HttpStatus.OK);
	}

	//::::::::::::::::::::::::::::::::::::::::Changer mot de passe:::::::::::::::::::::::::::::::::::::::::::::::://

	@PostMapping("/changePassword")
	public ReponseMessage changePassword(@RequestBody HashMap<String, String> request) {
		String emailOrNumero = request.get("emailOrNumero");
		User user = userRepository.findByUsername(emailOrNumero);

		if (user == null) {
			ReponseMessage message = new ReponseMessage("Utilisateur non fourni !",false);
			return message;
			//return new ResponseEntity<>("Utilisateur non fourni!", HttpStatus.BAD_REQUEST);
		}
		String currentPassword = request.get("currentpassword");
		String newPassword = request.get("newpassword");
		String confirmpassword = request.get("confirmpassword");
		if (!newPassword.equals(confirmpassword)) {
			ReponseMessage message = new ReponseMessage("Mot de passe incorrect",false);
			return message;
			//return new ResponseEntity<>("PasswordNotMatched", HttpStatus.BAD_REQUEST);
		}
		String userPassword = user.getPassword();
		try {
			if (newPassword != null && !newPassword.isEmpty() && !StringUtils.isEmpty(newPassword)) {
				if (bCryptPasswordEncoder.matches(currentPassword, userPassword)) {
				//	System.out.println("Je suis vraiment okkkkkkkkkkkkkkkkkki");
					userModifier.updateUserPassword(user, newPassword);
				}
			} else {
				ReponseMessage message = new ReponseMessage("Code incorrect",false);
				return message;
				//return new ResponseEntity<>("IncorrectCurrentPassword", HttpStatus.BAD_REQUEST);
			}
			userModifier.updateUserPassword(user, newPassword);
			ReponseMessage message = new ReponseMessage("Mot de passe changé avec succès !",true);
			return message;
			//return new ResponseEntity<>("Mot de passe changé avec succès!", HttpStatus.OK);
		} catch (Exception e) {
			ReponseMessage message = new ReponseMessage("Erreur",false);
			return message;
			//return new ResponseEntity<>("Error Occured: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}


	@GetMapping("/user/{id}")
	public Object utilisateur(@PathVariable("id") Long id){
		return userRepository.findById(id);

	}

	@GetMapping("/statusUser")
	public List<StatusUser> status(){
		return userStatusRepository.findAll();
	}


	@GetMapping("/tousLesUser")
	public List<User> users(){
		return userRepository.findAll();
	}

// ============================================================ LES NOTIFICATION DES UTILISATEURS CONNECTES
	@GetMapping("/userNotification/{user}/{lire}")
	public List<NotificationSender> userNotif(@PathVariable("user") User user,@PathVariable("lire") boolean lire){
		return notificationSenderRepository.findByUserAndLire(user,lire);
	}
// ============================================================ LUE UNE NOTIFICATION DES UTILISATEURS CONNECTES
@PatchMapping("/lire/{idnotification}")
public ReponseMessage lireNotif(@RequestBody NotificationSender notificationSender,@PathVariable("idnotification") Long idnotification){
	if(this.notificationSenderRepository.findById(idnotification) == null){

		ReponseMessage message = new ReponseMessage("Notif n'existe pas !", false);
		return message;
	}
	else{


		return this.userModifier.SetLire(notificationSender,idnotification);
	}
}




	@PatchMapping("/banirUser/{id}")
	public ReponseMessage banirUser(@RequestBody User user,@PathVariable("id") Long id){
		if(this.userRepository.findById(id) == null){

			ReponseMessage message = new ReponseMessage("Notif n'existe pas !", false);
			return message;
		}
		else{
			return this.userModifier.SetEtat(user,id);
		}
	}

}
