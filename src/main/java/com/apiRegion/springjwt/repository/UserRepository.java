package com.apiRegion.springjwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apiRegion.springjwt.models.User;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO users (adresse,email,etat,nom,password, prenom,  username)" +
			"  VALUES(\"Djelibougou\", \"coulibalyadamabekaye03@gmail.com\",1, \"Coulibaly\",:password ,  \"Adama\", \"70446711\" );",
			nativeQuery = true)
	void creationadmin(String password);
}
