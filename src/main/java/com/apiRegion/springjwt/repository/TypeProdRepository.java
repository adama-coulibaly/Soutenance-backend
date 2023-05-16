package com.apiRegion.springjwt.repository;

import com.apiRegion.springjwt.models.Typeproduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeProdRepository extends JpaRepository<Typeproduction, Long> {

    Typeproduction findByNomtype(String nomtype);
}
