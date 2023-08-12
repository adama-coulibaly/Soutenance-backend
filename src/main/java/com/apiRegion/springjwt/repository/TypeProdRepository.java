package com.apiRegion.springjwt.repository;

import com.apiRegion.springjwt.models.Typeproduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TypeProdRepository extends JpaRepository<Typeproduction, Long> {

    Typeproduction findByNomtype(String nomtype);
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO typeproduction (nomtype) VALUES (\"Bétail\"),(\"Caprin\"),(\"Oiseau\"),(\"Volaille\");",nativeQuery = true)
    void ajouterTypeProduction();
}
