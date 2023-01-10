package com.apiRegion.springjwt.controllers;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.img.ConfigImage;
import com.apiRegion.springjwt.models.Ferme;
import com.apiRegion.springjwt.models.Produit;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.repository.ProduitRepository;
import com.apiRegion.springjwt.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/produit")
public class ProduitController {

    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private ProduitService produitService;


    @PostMapping("/ajouter")
    public ReponseMessage ajouter(@Param("nomproduit") String nomproduit,
                                  @Param("reference") String reference,
                                  @Param("idferme") Ferme ferme,
                                  @Param("descriptionproduit") String descriptionproduit,
                                  @Param("file") MultipartFile file) throws IOException {

        Produit produit1 = new Produit();
        String nomfile = StringUtils.cleanPath(file.getOriginalFilename());




        produit1.setNomproduit(nomproduit);
        produit1.setDescriptionproduit(descriptionproduit);
        produit1.setReference(reference);
        produit1.setPhtoproduit(nomfile);
       // produit1.setFermes(ferme.);
        produit1.setEtat(true);




        System.out.println("========== "+nomfile);
        System.out.println("========== "+nomproduit);
        System.out.println("========== "+descriptionproduit);
        System.out.println("========== "+reference);


        if(produitRepository.findByReference(reference) == null){

            String uploaDir = "C:/Users/adcoulibaly/Desktop/Adama/CLONES_API/SoutenanceODC/Backend/Api-Regions-Originale/src/main/resources/images";
            ConfigImage.saveimg(uploaDir, nomfile, file);

            return produitService.Ajouter(produit1,ferme);
            //ReponseMessage message = new ReponseMessage("Ferme ajoutée avec succès",true);
            // message;

        }else {
            ReponseMessage message = new ReponseMessage("Cette référence de produit existe déja",false);
            return message;
        }

    }
}
