package com.royalreserve.notificationservice.controller;

import com.royalreserve.notificationservice.dto.NotificationResponse;
import com.royalreserve.notificationservice.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getAll() {
        return ResponseEntity.ok(service.getAllNotifications());
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<NotificationResponse>> getByAccount(@PathVariable String accountId) {
        return ResponseEntity.ok(service.getNotificationsByAccount(accountId));
    }
}
