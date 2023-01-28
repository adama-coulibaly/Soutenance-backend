package com.apiRegion.springjwt.controllers;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.img.SaveImage;
import com.apiRegion.springjwt.models.Ferme;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.repository.FermeRepository;
import com.apiRegion.springjwt.services.FermeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/ferme")
@CrossOrigin(origins = "*", maxAge = 3600)

public class FermeControler {

    @Autowired
    private FermeService fermeService;

    private FermeRepository fermeRepository;

    public FermeControler(FermeService fermeService, FermeRepository fermeRepository) {
        this.fermeService = fermeService;
        this.fermeRepository = fermeRepository;
    }
    ////================================================AJOUTER UNE FERME

    @PostMapping("/ajouter")
    public ReponseMessage ajouter(@Param("nomferme") String nomferme,
                                  @Param("activiteferme") String activiteferme,
                                  @Param("adresseferme") String adresseferme,
                                  @Param("user_id") User user_id,
                                  @Param("taille") String taille,
                                  @Param("file") MultipartFile file) throws IOException {

        Ferme ferme1 = new Ferme();
        String nomfile = StringUtils.cleanPath(file.getOriginalFilename());

        ferme1.setNomferme(nomferme);

        ferme1.setActiviteferme(activiteferme);
        ferme1.setAdresseferme(adresseferme);
        ferme1.setImageferme(nomfile);
        ferme1.setEtat(true);
        ferme1.setUser(user_id);
        ferme1.setTaille(taille);

        System.out.println("========== "+nomfile);
        System.out.println("========== "+nomferme);
        System.out.println("========== "+activiteferme);
        System.out.println("========== "+adresseferme);


        if(fermeRepository.findByNomferme(nomferme) == null){

           /* String uploaDir = "C:/Users/adcoulibaly/Desktop/Adama/CLONES_API/SoutenanceODC/Backend/Api-Regions-Originale/src/main/resources/images";
            ConfigImage.saveimg(uploaDir, nomfile, file);

            */
        ferme1.setImageferme(SaveImage.save(file,ferme1.getImageferme()));
            return fermeService.Ajouter(ferme1,user_id);
            //ReponseMessage message = new ReponseMessage("Ferme ajoutée avec succès",true);
            // message;

        }else {
            ReponseMessage message = new ReponseMessage("Ce nom de ferme existe déja",false);
            return message;
        }

    }
    //// ================================================ MODIFIER UNE FERME
    @PutMapping("/modifier/{idferme}")
    public ReponseMessage Modifier(
                                  @PathVariable("idferme") Long idferme,
                                  @Param("nomferme") String nomferme,
                                  @Param("activiteferme") String activiteferme,
                                  @Param("adresseferme") String adresseferme,
                                  @Param("user_id") User user_id,
                                  @Param("taille") String taille,
                                  @Param("etat") boolean etat,
                                  @Param("file") MultipartFile file) throws IOException {

        Ferme ferme1 = new Ferme();
        String nomfile = StringUtils.cleanPath(file.getName());

        ferme1.setNomferme(nomferme);

        ferme1.setActiviteferme(activiteferme);
        ferme1.setAdresseferme(adresseferme);
        ferme1.setImageferme(nomfile);
        ferme1.setEtat(true);
        ferme1.setUser(user_id);
        ferme1.setTaille(taille);
        ferme1.setEtat(etat);

        System.out.println("Modifier ========== "+nomfile);
        System.out.println("Modifier ========== "+nomferme);
        System.out.println("Modifier ========== "+activiteferme);
        System.out.println(" Modifier ========== "+adresseferme);


        if(fermeRepository.findById(idferme) != null){
            ferme1.setImageferme(SaveImage.save(file,nomfile));
            return fermeService.Modifier(ferme1,idferme);

        }else {
            ReponseMessage message = new ReponseMessage("Cette ferme n'existe pas",false);
            return message;
        }

    }


    ////================================================= LISTER TOUS LES FERMES
   // @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @GetMapping("/lister")
    public List<Ferme> lister(){
        return fermeService.Lister();
    }

    ////=================================================

    @DeleteMapping("supprimer/{idferme}")
    public ReponseMessage changeEtat(@PathVariable("idferme") Long idferme)
    {

        Optional<Ferme> ferme = this.fermeRepository.findById(idferme);
        if (!ferme.isPresent())
        {
            ReponseMessage message = new ReponseMessage("Ferme non trouvée !", false);
            return message;
        }
        else {
            this.fermeRepository.delete(ferme.get());
            ReponseMessage message = new ReponseMessage("Ferme supprimée avec succès !", true);
            return message;
        }

    }

    // ICI ON RECUPERE LES FERMES D'UN SEUL UTILISATEUR

    @GetMapping("/UserFermes/{user_id}")
    public List<Ferme> mesFermes(@PathVariable("user_id") User user_id){
        return this.fermeRepository.findByUser(user_id);
    }


    // ICI ON RECUPERE LES INFORMATION D'UNE FERMES D'UN SEUL UTILISATEUR

    @GetMapping("/infoferme/{idferme}")
    public Optional<Ferme> Fermes(@PathVariable("idferme") Ferme ferme){
        return this.fermeRepository.findById(ferme.getIdferme());
    }



    @PatchMapping("/etat/{idferme}")
    public ReponseMessage SetEtat(@RequestBody Ferme ferme,@PathVariable("idferme") Long idferme){
        if(this.fermeRepository.findById(idferme) == null){

            ReponseMessage message = new ReponseMessage("Ferme n'existe pas !", false);
            return message;
        }
        else{


           return this.fermeService.SetEtat(ferme,idferme);
        }
    }







}
