package com.apiRegion.springjwt.controllers;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Typeproduction;
import com.apiRegion.springjwt.services.TypeProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/typeproduction")
public class TypeProductionContoller {

    @Autowired
    private TypeProductionService typeProductionService;

    @PostMapping("/ajouter")
    public ReponseMessage ajouter(@RequestBody Typeproduction typeproduction){
        return this.typeProductionService.Ajouter(typeproduction);
    }
}
