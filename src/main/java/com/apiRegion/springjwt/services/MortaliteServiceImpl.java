package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Mortalite;
import com.apiRegion.springjwt.models.Production;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MortaliteServiceImpl implements MortaliteService {
    @Override
    public ReponseMessage Ajouter(Mortalite mortalite, Production production) {
        return null;
    }

    @Override
    public ReponseMessage Modifier(Mortalite mortalite, Long idmortalite) {
        return null;
    }

    @Override
    public ReponseMessage Supprimer(Long idmortalite) {
        return null;
    }

    @Override
    public List<Mortalite> Lister() {
        return null;
    }
}
