package com.royalreserve.notificationservice.service.impl;

import com.royalreserve.notificationservice.dto.NotificationResponse;
import com.royalreserve.notificationservice.model.Notification;
import com.royalreserve.notificationservice.repository.NotificationRepository;
import com.royalreserve.notificationservice.service.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;

    public NotificationServiceImpl(NotificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<NotificationResponse> getAllNotifications() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationResponse> getNotificationsByAccount(String accountId) {
        return repository.findByAccountId(accountId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private NotificationResponse toResponse(Notification notification) {
        return new NotificationResponse(
                notification.getId(),
                notification.getAccountId(),
                notification.getMessage(),
                notification.getCreatedAt()
        );
    }
}
