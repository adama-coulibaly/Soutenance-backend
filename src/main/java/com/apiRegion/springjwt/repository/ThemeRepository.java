package com.apiRegion.springjwt.repository;

import com.apiRegion.springjwt.models.Ferme;
import com.apiRegion.springjwt.models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<Theme,Long> {

    public Theme findByTitretheme(String titretheme);

}
