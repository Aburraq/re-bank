package com.royalreserve.notificationservice.config;

import com.royalreserve.notificationservice.model.Notification;
import com.royalreserve.notificationservice.repository.NotificationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataSeeder implements CommandLineRunner {
    private final NotificationRepository repository;

    public DataSeeder(NotificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() == 0) {
            repository.save(new Notification("user1", "Welcome to Royal Reserve!", LocalDateTime.now()));
            repository.save(new Notification("user2", "Your recent transaction was successful.", LocalDateTime.now()));
            repository.save(new Notification("user1", "Your asset portfolio has been updated.", LocalDateTime.now()));
        }
    }
}
