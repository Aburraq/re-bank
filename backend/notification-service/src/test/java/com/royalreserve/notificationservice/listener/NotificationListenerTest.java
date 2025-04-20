package com.royalreserve.notificationservice.listener;

import com.royalreserve.notificationservice.dto.TransactionResponse;
import com.royalreserve.notificationservice.listener.NotificationListener;
import com.royalreserve.notificationservice.model.Notification;
import com.royalreserve.notificationservice.repository.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationListenerTest {

    @Mock
    private NotificationRepository repository;

    private NotificationListener listener;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        listener = new NotificationListener(repository);
    }

    @Test
    void onTransactionReceived_savesNotification() {
        LocalDateTime txTime = LocalDateTime.of(2025, 4, 20, 12, 0);
        TransactionResponse tx = new TransactionResponse(1L, "acc1", "DEPOSIT", new BigDecimal("100.00"), txTime);

        listener.onTransactionReceived(tx);

        ArgumentCaptor<Notification> captor = ArgumentCaptor.forClass(Notification.class);
        verify(repository).save(captor.capture());
        Notification saved = captor.getValue();
        assertEquals("acc1", saved.getAccountId());
        assertTrue(saved.getMessage().contains("DEPOSIT"));
        assertTrue(saved.getMessage().contains("100.00"));
        assertTrue(saved.getMessage().contains("acc1"));
        assertNotNull(saved.getCreatedAt());
    }
}
