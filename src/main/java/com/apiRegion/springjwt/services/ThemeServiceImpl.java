package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Theme;
import com.apiRegion.springjwt.models.Typeproduction;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.repository.ThemeRepository;
import com.apiRegion.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ReponseMessage Ajouter(Theme theme, User user_id) {

            if(themeRepository.findByTitretheme(theme.getTitretheme()) == null) {
                theme.setUser(user_id);
                theme.setDateposte(new Date());
                themeRepository.save(theme);
                ReponseMessage message = new ReponseMessage("Theme ou probleme ajouté avec succès", true);
                return message;
            }
            else{
                ReponseMessage message = new ReponseMessage("Ce theme a deja été discuter", false);
                return message;
            }


    }

    @Override
    public ReponseMessage Modifier(Theme theme, Long idtheme, Long id) {

        Optional<User> user = userRepository.findById(id);

        Optional<Theme> theme1 = themeRepository.findById(idtheme);

        if(theme1.get().getUser().getId() != user.get().getId()){

            ReponseMessage message = new ReponseMessage("Vous ne pouvez pas modifié ce theme !", false);
            return message;
        } else {

            Theme theme2 = themeRepository.findById(idtheme).get();
            theme2.setTitretheme(theme.getTitretheme());

            this.themeRepository.save(theme2);

            ReponseMessage message = new ReponseMessage("Theme modifiée avec succès  !", true);
            return message;


        }
    }

    @Override
    public ReponseMessage Supprimer(Long idtheme,Long id) {

        Optional<Theme> theme = this.themeRepository.findById(idtheme);
        Optional<User> user = userRepository.findById(id);
        if (!theme.isPresent() && user.get().getId() == theme.get().getUser().getId())
        {
            ReponseMessage message = new ReponseMessage("Impossible de modifier le theme !", false);
            return message;
        }
        else {
            this.themeRepository.delete(theme.get());
            ReponseMessage message = new ReponseMessage("Thème supprimé avec succès !", true);
            return message;
        }
    }

    @Override
    public List<Theme> Lister() {
        return themeRepository.findAll();
    }
}
