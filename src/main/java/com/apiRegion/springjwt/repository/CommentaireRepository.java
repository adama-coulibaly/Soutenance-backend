package com.apiRegion.springjwt.repository;

import com.apiRegion.springjwt.models.Commentaire;
import com.apiRegion.springjwt.models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {

    List<Commentaire> findByTheme(Theme idtheme);

    @Transactional
    @Query(value = "SELECT * FROM `commentaire` WHERE theme_idtheme = :idtheme ORDER BY commentaire.datecom DESC;",nativeQuery = true)
    List<Commentaire> mesCommentaires(Theme idtheme);
}
