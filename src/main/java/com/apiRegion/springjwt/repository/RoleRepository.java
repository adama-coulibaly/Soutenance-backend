package com.apiRegion.springjwt.repository;

import java.util.Optional;

import com.apiRegion.springjwt.models.ERole;
import com.apiRegion.springjwt.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO roles (name) VALUES (\"ROLE_SUPER_ADMIN\"),(\"ROLE_ADMIN\"),(\"ROLE_USER\");",nativeQuery = true)
	void creationrole();

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO  user_roles (user_id,role_id) VALUES (1,1);",nativeQuery = true)
	void addRoleToUser();
}
