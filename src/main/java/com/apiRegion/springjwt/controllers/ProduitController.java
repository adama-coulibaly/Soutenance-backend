package com.apiRegion.springjwt.controllers;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.img.SaveImage;
import com.apiRegion.springjwt.models.Ferme;
import com.apiRegion.springjwt.models.Produit;
import com.apiRegion.springjwt.repository.FermeRepository;
import com.apiRegion.springjwt.repository.ProduitRepository;
import com.apiRegion.springjwt.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/produit")
@CrossOrigin(origins = "*", maxAge = 3600)

public class ProduitController {

    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private ProduitService produitService;
    @Autowired
    private FermeRepository fermeRepository;


    @PostMapping("/ajouter")
    public ReponseMessage ajouter(@Param("nomproduit") String nomproduit,
                                  @Param("reference") String reference,
                                  @Param("idferme") Ferme ferme,
                                  @Param("descriptionproduit") String descriptionproduit,
                                  @Param("prix") Long prix,
                                  @Param("quantiteVente") Long quantiteVente,
                                  @Param("file") MultipartFile file) throws IOException {

        Produit produit1 = new Produit();
        String nomfile = StringUtils.cleanPath(file.getOriginalFilename());

        produit1.setNomproduit(nomproduit);
        produit1.setDescriptionproduit(descriptionproduit);
        produit1.setReference(reference);
        produit1.setPhtoproduit(nomfile);
        produit1.setPrix(prix);
        produit1.setQuantiteVente(quantiteVente);
        //ferme.setProduits();
        produit1.setEtat(true);

        if(produitRepository.findByReference(reference) == null){
            produit1.setPhtoproduit(SaveImage.save(file,nomfile));
            return produitService.Ajouter(produit1,ferme);
            //ReponseMessage message = new ReponseMessage("Ferme ajoutée avec succès",true);
            // message;

        }else {
            ReponseMessage message = new ReponseMessage("Cette référence de produit existe déja",false);
            return message;
        }

    }


    @PutMapping("/modifier/{idproduit}")
    public ReponseMessage Modifier(
                                  @PathVariable("idproduit") Long idproduit,
                                  @Param("nomproduit") String nomproduit,
                                  @Param("reference") String reference,
                                  @Param("idferme") Ferme ferme,
                                  @Param("descriptionproduit") String descriptionproduit,
                                  @Param("prix") Long prix,
                                  @Param("quantiteVente") Long quantiteVente
            ,
                                  @Param("file") MultipartFile file) throws IOException {

        Produit produit1 = new Produit();
        String nomfile = StringUtils.cleanPath(file.getOriginalFilename());

        produit1.setNomproduit(nomproduit);
        produit1.setDescriptionproduit(descriptionproduit);
        produit1.setReference(reference);
        produit1.setPhtoproduit(nomfile);
        produit1.setPrix(prix);
        produit1.setQuantiteVente(quantiteVente);
        produit1.setEtat(true);

        if(produitRepository.findByReference(reference) == null){
            produit1.setPhtoproduit(SaveImage.save(file,nomfile));
            return produitService.Modifier(produit1,idproduit);
            //ReponseMessage message = new ReponseMessage("Ferme ajoutée avec succès",true);
            // message;

        }else {
            ReponseMessage message = new ReponseMessage("Cette référence de produit existe déja",false);
            return message;
        }

    }



    @GetMapping("/lister")
    public List<Produit> lister(){
        return produitService.Lister();
    }


    @GetMapping("/listerParFerme/{idferme}")
    public List<Produit> listerParFerme(@PathVariable("idferme") Ferme ferme){
        return produitRepository.findByFermes(ferme);
    }


    @GetMapping("/listerParid/{idproduit}")
    public Optional<Produit> listerParId(@PathVariable("idproduit") Long produit){
        return produitRepository.findById(produit);
    }





    @PatchMapping("/etat/{idferme}")
    public ReponseMessage SetEtat(@RequestBody Produit produit, @PathVariable("idferme") Long idproduit){
        if(this.produitRepository.findById(idproduit) == null){

            ReponseMessage message = new ReponseMessage("Ferme n'existe pas !", false);
            return message;
        }
        else{
            return this.produitService.SetEtat(produit,idproduit);
        }
    }



}
