package com.apiRegion.springjwt.repository;

import com.apiRegion.springjwt.models.NotificationSender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationSenderRepository extends JpaRepository<NotificationSender,Long> {
}
