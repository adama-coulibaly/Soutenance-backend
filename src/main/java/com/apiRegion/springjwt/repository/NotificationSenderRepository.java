package com.apiRegion.springjwt.repository;

import com.apiRegion.springjwt.models.NotificationSender;
import com.apiRegion.springjwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationSenderRepository extends JpaRepository<NotificationSender,Long> {

    List<NotificationSender> findByUserAndLire(User user,boolean lire);
}
