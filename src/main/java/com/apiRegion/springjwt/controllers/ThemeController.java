package com.apiRegion.springjwt.controllers;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Theme;
import com.apiRegion.springjwt.models.Typeproduction;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.repository.ThemeRepository;
import com.apiRegion.springjwt.repository.UserRepository;
import com.apiRegion.springjwt.services.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/theme")
public class ThemeController {

    @Autowired
    private ThemeService themeService;
    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private UserRepository userRepository;

    // ========================================= AJOUTER UN THEME
    @PostMapping("/ajouter/{id}")
    public ReponseMessage ajouter(@RequestBody Theme theme, @PathVariable("id") User id){

        return this.themeService.Ajouter(theme,id);
    }
  // ========================================= MODIFIER UN THEMES
  @PutMapping("/modifier/{idtheme}/{id}")
  public ReponseMessage modifier(@RequestBody Theme theme, @PathVariable("idtheme") Long idtheme, @PathVariable("id") Long id){

      if(themeRepository.findById(idtheme) != null){
          if (userRepository.findById(id) == null) {
              ReponseMessage message = new ReponseMessage("Utilisateur n'existe pas !", false);
              return message;
          }
          else {
              return  this.themeService.Modifier(theme,idtheme,id);
          }



      }  else{
          ReponseMessage message = new ReponseMessage("Thème non modifié !", false);
          return message;
      }
  }

 // ========================================= LISTER LES THEMES
    @GetMapping("/liste")
    public List<Theme> lister(){
        return themeService.Lister();
    }
}
