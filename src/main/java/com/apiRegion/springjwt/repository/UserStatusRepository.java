package com.apiRegion.springjwt.repository;

import com.apiRegion.springjwt.models.StatusUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserStatusRepository extends JpaRepository<StatusUser,Long> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO status_user (nomstatus) VALUES (\"Fermier\"),(\"Non fermier\");",nativeQuery = true)
    void ajouterStatusUser();
}
