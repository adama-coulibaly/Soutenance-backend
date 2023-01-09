package com.apiRegion.springjwt.repository;

import com.apiRegion.springjwt.models.Ferme;
import com.apiRegion.springjwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FermeRepository extends JpaRepository<Ferme, Long> {

    public Ferme findByNomferme(String nomferme);

    public List<Ferme> findByUser(User user_id);
}
