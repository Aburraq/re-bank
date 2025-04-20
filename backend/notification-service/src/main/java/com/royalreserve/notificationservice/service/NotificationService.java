package com.royalreserve.notificationservice.service;

import com.royalreserve.notificationservice.dto.NotificationResponse;
import java.util.List;

public interface NotificationService {
    List<NotificationResponse> getAllNotifications();
    List<NotificationResponse> getNotificationsByAccount(String accountId);
}
