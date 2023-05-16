package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Commande;
import com.apiRegion.springjwt.models.User;

public interface CommandeService {

    public ReponseMessage ajouter(Commande commande, User user);

}
