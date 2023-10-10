package com.apiRegion.springjwt.controllers;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Theme;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.repository.ThemeRepository;
import com.apiRegion.springjwt.repository.UserRepository;
import com.apiRegion.springjwt.servicesImpl.SmsService;
import com.apiRegion.springjwt.services.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/theme")
public class ThemeController {

    @Autowired
    private ThemeService themeService;
    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SmsService smsService;

    // ========================================= AJOUTER UN THEME
    @PostMapping("/ajouter/{id}")
    public ReponseMessage ajouter(@RequestBody Theme theme, @PathVariable("id") User id){
        //smsService.sendSms("+22370446711","Test d'envoi SMS");
         //System.out.println("SMS envoyer");
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
// =======================================================ICI ON AFFICHE UN SEUL THEME
    @GetMapping("/Unliste/{idtheme}")
    public Optional<Theme> listerUnTheme(@PathVariable("idtheme") Long idtheme){
        if (themeRepository.findById(idtheme) != null){
            return themeRepository.findById(idtheme);
        }
         else{
             return null;
        }
    }

    // ==================================================== SUPRIMER UN THEME

    @DeleteMapping(path = "/supprimer/{idtheme}/{id}", name = "supprimer")
    //  @ResponseStatus(HttpStatus.NO_CONTENT)
    public ReponseMessage supprimer(@PathVariable Long idtheme, @PathVariable("id") Long id) {
        return this.themeService.Supprimer(idtheme,id);
    }
}
