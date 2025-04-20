package com.royalreserve.notificationservice.repository;

import com.royalreserve.notificationservice.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // Find all notifications for a given account
    List<Notification> findByAccountId(String accountId);
}
