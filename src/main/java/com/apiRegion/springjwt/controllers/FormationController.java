package com.apiRegion.springjwt.controllers;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.img.SaveImage;
import com.apiRegion.springjwt.models.Ferme;
import com.apiRegion.springjwt.models.Formation;
import com.apiRegion.springjwt.models.NotificationSender;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.repository.FermeRepository;
import com.apiRegion.springjwt.repository.FormationRepository;
import com.apiRegion.springjwt.repository.NotificationSenderRepository;
import com.apiRegion.springjwt.services.FermeService;
import com.apiRegion.springjwt.services.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/formation")
@CrossOrigin(origins = "*", maxAge = 3600)

public class FormationController {

    @Autowired
    private FormationService formationService;

    @Autowired
    private FormationRepository formationRepository;
    @Autowired
    private NotificationSenderRepository notificationSenderRepository;


    ////================================================AJOUTER UNE FERME

    @PostMapping("/ajouter")
    public ReponseMessage ajouter(@Param("titreforlation") String titreforlation,
                                  @Param("dureformation") String dureformation,
                                  @Param("description") String description,
                                  @Param("urlformation") String urlformation,
                                  @Param("user_id") User user_id,
                                  @Param("file") MultipartFile file) throws IOException {

        Formation formation = new Formation();
        String nomfile = StringUtils.cleanPath(file.getOriginalFilename());

        formation.setTitreformation(titreforlation);
        formation.setDureformation(dureformation);
        formation.setPhotoformation(nomfile);
        formation.setDescription(description);
        formation.setUrlformation(urlformation);
        formation.setEtat(true);
        formation.getUsers().add(user_id);

        if(formationRepository.findByTitreformation(titreforlation) == null){

            formation.setPhotoformation(SaveImage.save(file,formation.getPhotoformation()));
            NotificationSender notificationSender = new NotificationSender(LocalDate.now(),formation.getTitreformation(),"Une nouvelle formation sur "+formation.getTitreformation());
            notificationSenderRepository.save(notificationSender);
            return formationService.Ajouter(formation,user_id);
            //ReponseMessage message = new ReponseMessage("Ferme ajoutée avec succès",true);
            // message;

        }else {
            ReponseMessage message = new ReponseMessage("Formation existe déja",false);
            return message;
        }

    }
    //// ================================================ MODIFIER UNE FERME
    @PutMapping("/modifier/{idformation}")
    public ReponseMessage Modifier(
            @PathVariable("idformation") Long idformation,
            @Param("titreforlation") String titreforlation,
            @Param("dureformation") String dureformation,
            @Param("description") String description,
            @Param("urlformation") String urlformation,
            @Param("user_id") User user_id,
            @Param("etat") boolean etat,
            @Param("file") MultipartFile file) throws IOException {

        Formation formation = new Formation();
        String nomfile = StringUtils.cleanPath(file.getName());

        formation.setTitreformation(titreforlation);
        formation.setDureformation(dureformation);
        formation.setDescription(description);
        formation.setUrlformation(urlformation);
        formation.setEtat(etat);


        if(formationRepository.findById(idformation) != null){
            formation.setPhotoformation(SaveImage.save(file,nomfile));
            return formationService.Modifier(formation,idformation);

        }else {
            ReponseMessage message = new ReponseMessage("Cette formation n'existe pas",false);
            return message;
        }
    }
    ////================================================= LISTER TOUS LES FERMES
    // @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @GetMapping("/lister")
    public List<Formation> lister(){
        return formationService.Lister();
    }


    @GetMapping("/listerId/{idformation}")
    public Optional<Formation> lister(@PathVariable("idformation") Formation idformation){
        return formationRepository.findById(idformation.getIdformation());
    }

    ////=================================================

    @DeleteMapping("supprimer/{idformation}")
    public ReponseMessage changeEtat(@PathVariable("idformation") Long idformation)
    {

        Optional<Formation> formation = this.formationRepository.findById(idformation);
        if (!formation.isPresent())
        {
            ReponseMessage message = new ReponseMessage("Formation non trouvée !", false);
            return message;
        }
        else {
            this.formationRepository.delete(formation.get());
            ReponseMessage message = new ReponseMessage("Formation supprimée avec succès !", true);
            return message;
        }

    }


    @PatchMapping("/etat/{idformation}")
    public ReponseMessage SetEtat(@RequestBody Formation formation,@PathVariable("idformation") Long idformation){
        if(this.formationRepository.findById(idformation) == null){

            ReponseMessage message = new ReponseMessage("Formation n'existe pas !", false);
            return message;
        }
        else{
            return this.formationService.SetEtat(formation,idformation);
        }
    }







}

