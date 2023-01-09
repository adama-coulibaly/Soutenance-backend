package com.apiRegion.springjwt.payload.response;

import java.util.List;


public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String nom;
	private String prenom;
	private String email;
	String adresse;
	private List<String> roles;

	public JwtResponse(String accessToken, Long id, String username, String email, String nom, String prenom, String adresse, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.nom =  nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public String getPrenom() {
		return  prenom;
	}

	public String getNom() {
		return nom;
	}
	public String getAdresse() {
		return adresse;
	}
}
