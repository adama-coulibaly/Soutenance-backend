package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Commentaire;
import com.apiRegion.springjwt.models.Theme;
import com.apiRegion.springjwt.models.User;
import com.apiRegion.springjwt.repository.CommentaireRepository;
import com.apiRegion.springjwt.repository.ThemeRepository;
import com.apiRegion.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class CommentaireServiceImpl implements CommentaireService {

    @Autowired
    private CommentaireRepository commentaireRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ThemeRepository themeRepository;



    @Override
    public ReponseMessage Ajouter(Commentaire commentaire,Theme idtheme, User user_id) {

        commentaire.setTheme(idtheme);
        commentaire.setDatecom(new Date());
        commentaire.setUser(user_id);
        Theme t = themeRepository.findById(idtheme.getIdtheme()).get();
        t.setNbreCommentaire(t.getNbreCommentaire()+1);

        commentaireRepository.save(commentaire);
        ReponseMessage message = new ReponseMessage("Commenté evec succès !",true);
        return message;
    }

    @Override
    public ReponseMessage Modifier(Commentaire commentaire, Long idcommentaire, Long id) {

        Optional<User> user = userRepository.findById(id);
        Optional<Commentaire> commentaire1 = commentaireRepository.findById(idcommentaire);

        if(commentaire1.get().getUser().getId() == user.get().getId()) {

            Commentaire com = commentaireRepository.findById(idcommentaire).get();
            com.setDescriptioncom(commentaire.getDescriptioncom());
            com.setDatecom(new Date());

            commentaireRepository.save(com);
            ReponseMessage message = new ReponseMessage("Commentaire modifier avec succès",true);
            return message;
        }
        else {
            ReponseMessage message = new ReponseMessage("Vous ne pouvez pas modifier ce commentaire !",false);
            return message;
        }

    }

    @Override
    public ReponseMessage Supprimer(Long idcommentaire, Long id) {
        Optional<Commentaire> commentaire = this.commentaireRepository.findById(idcommentaire);
        Optional<User> user = userRepository.findById(id);
        if (user.get().getId() != commentaire.get().getUser().getId())
        {
            ReponseMessage message = new ReponseMessage("Impossible de supprimer ce commentaire !", false);
            return message;
        }
        else {
            this.commentaireRepository.delete(commentaire.get());
            ReponseMessage message = new ReponseMessage("Commentaire supprimé avec succès !", true);
            return message;
        }
    }

    @Override
    public List<Commentaire> Lister() {
        return commentaireRepository.findAll();
    }
}
