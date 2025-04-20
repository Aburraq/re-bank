package com.royalreserve.notificationservice.listener;

import com.royalreserve.notificationservice.dto.TransactionResponse;
import com.royalreserve.notificationservice.model.Notification;
import com.royalreserve.notificationservice.repository.NotificationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotificationListener {
    private final NotificationRepository repository;

    public NotificationListener(NotificationRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "transactions", groupId = "${spring.kafka.consumer.group-id}")
    public void onTransactionReceived(TransactionResponse tx) {
        String message = String.format(
            "Transaction %s of amount %s for account %s occurred at %s",
            tx.getType(), tx.getAmount(), tx.getAccountId(), tx.getCreatedAt()
        );
        Notification notification = new Notification(
            tx.getAccountId(), message, LocalDateTime.now()
        );
        repository.save(notification);
    }
}
