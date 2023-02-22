package com.apiRegion.springjwt.controllers;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Commentaire;
import com.apiRegion.springjwt.models.Theme;
import com.apiRegion.springjwt.models.Typeproduction;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.repository.CommentaireRepository;
import com.apiRegion.springjwt.services.CommentaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/commentaire")
@CrossOrigin(origins = "*", maxAge = 3600)

public class CommentaireController {

    @Autowired
    private CommentaireService commentaireService;

    @Autowired
    private CommentaireRepository commentaireRepository;


    // ========================================= AJOUTER UN Commentaire
    @PostMapping("/ajouter/{idtheme}/{id}")
    public ReponseMessage ajouter(@RequestBody Commentaire commentaire, @PathVariable("idtheme") Theme idtheme, @PathVariable("id") User id){

        return this.commentaireService.Ajouter(commentaire,idtheme,id);
    }

    // ======================================== ICI ON LISTE TOUS LES COMMENTAIRES

    @GetMapping("/liste")
    public List<Commentaire> commentaires(){
        return this.commentaireService.Lister();
    }


//============================================== ICI ON LISTE LES COMMENTAIRES PAR THEMES
  /*  @GetMapping("/listepatheme/{idtheme}")
    public List<Commentaire> commentairesparthemes(Theme idtheme){
        return this.commentaireRepository.findByTheme(idtheme);
    }*/

    @GetMapping("/listepatheme/{idtheme}")
    public List<Commentaire> commentairesparthemes(Theme idtheme){
        return this.commentaireRepository.mesCommentaires(idtheme);
    }

// ============================================== MODIFIER UN COMMENTAIRE PAR SON PROPRE USER
    @PutMapping("/modifier/{idcommentaire}/{id}")
    public ReponseMessage Modifier(@RequestBody Commentaire commentaire, @PathVariable("idcommentaire") Long idtheme,  @PathVariable("id")Long id) {
        return this.commentaireService.Modifier(commentaire,idtheme,id);
    }


    // ============================================== SUPPRIMER UN COMMENTAIRE PAR SON PROPRE USER
    @DeleteMapping(path = "/supprimer/{idcommentaire}/{id}", name = "supprimer")
    //  @ResponseStatus(HttpStatus.NO_CONTENT)
    public ReponseMessage supprimer(@PathVariable Long idcommentaire, @PathVariable("id") Long id) {
        return this.commentaireService.Supprimer(idcommentaire,id);
    }


}
