package com.apiRegion.springjwt.payload.request;

import com.apiRegion.springjwt.models.StatusUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;




@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupRequest {


    private String nom;


    private String prenom;

    private String username;
    private String email;
    private Set<String> roles;

     private StatusUser idStatus;

    private String password;

    private String adresse;

    private String avatar;


    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Set<String> getRole() {
      return this.roles;
    }
    
    public void setRole(Set<String> role) {
      this.roles = role;
    }
}
