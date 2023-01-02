package com.apiRegion.springjwt.repository;

import com.apiRegion.springjwt.models.Commentaire;
import com.apiRegion.springjwt.models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {

    List<Commentaire> findByTheme(Theme idtheme);
}
