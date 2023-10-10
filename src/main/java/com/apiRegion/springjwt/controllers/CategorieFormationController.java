package com.apiRegion.springjwt.controllers;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.img.SaveImage;
import com.apiRegion.springjwt.models.CategorieFormation;
import com.apiRegion.springjwt.models.Formation;
import com.apiRegion.springjwt.models.NotificationSender;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.repository.CategorieFormationRepository;
import com.apiRegion.springjwt.repository.CategorieRepository;
import com.apiRegion.springjwt.services.CategorieFormationService;
import com.apiRegion.springjwt.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/categorieFormation")
@CrossOrigin(origins = "*", maxAge = 3600)

public class CategorieFormationController {

    @Autowired
    private CategorieFormationService categorieFormationService;

    @Autowired
    private CategorieFormationRepository categorieFormationRepository;


    @PostMapping("/ajouter")
    public ReponseMessage ajouter(
            @Param("titreCategorie") String titreCategorie,
            @Param("description") String description,
            @Param("file") MultipartFile file) throws IOException {

        CategorieFormation formation = new CategorieFormation();
        String nomfile = StringUtils.cleanPath(file.getOriginalFilename());

        formation.setTitreCategorie(titreCategorie);
        formation.setDescription(description);
        formation.setPhotocategorie(nomfile);
        formation.setEtat(true);

        if(categorieFormationRepository.findByTitreCategorie(titreCategorie) == null){
            formation.setPhotocategorie(SaveImage.save(file,formation.getPhotocategorie()));
            return categorieFormationService.Ajouter(formation);

        }else {
            ReponseMessage message = new ReponseMessage("Catégorie existe déja",false);
            return message;
        }

    }

    @GetMapping("/lister")
    public List<CategorieFormation> mesCategories(){
        return categorieFormationService.Lister();
    }

    @GetMapping("/listerId/{idformation}")
    public Optional<CategorieFormation> lister(@PathVariable("idformation") CategorieFormation categorieFormation){
        return categorieFormationRepository.findById(categorieFormation.getId());
    }

    @GetMapping("/deuxFormation")
    public List<CategorieFormation> liste(){
        return categorieFormationRepository.deuxFormations();
    }
}
