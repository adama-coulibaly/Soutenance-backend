package com.apiRegion.springjwt.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.img.SaveImage;
import com.apiRegion.springjwt.payload.response.JwtResponse;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.apiRegion.springjwt.models.ERole;
import com.apiRegion.springjwt.models.Role;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.payload.request.LoginRequest;
import com.apiRegion.springjwt.payload.request.SignupRequest;
import com.apiRegion.springjwt.payload.response.MessageResponse;
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

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

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
				roles
		));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Utilisateur existe déjà !"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Erreur: Email existe déjà!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

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

		// senderService.sendSimpleEmail(user.getEmail(), "Création de compte","Nous vous remercions pour votre inscription ! " +user.getNom());

		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("Compte crée avec succès!"));
	}

@PutMapping("/modifier/{id}")
	public ReponseMessage Modifier(@Valid @RequestBody User loginRequest,@PathVariable("id") Long id){

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

}
