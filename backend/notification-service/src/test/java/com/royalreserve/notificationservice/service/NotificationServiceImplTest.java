package com.royalreserve.notificationservice.service;

import com.royalreserve.notificationservice.dto.NotificationResponse;
import com.royalreserve.notificationservice.model.Notification;
import com.royalreserve.notificationservice.repository.NotificationRepository;
import com.royalreserve.notificationservice.service.impl.NotificationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationServiceImplTest {
    @Mock
    private NotificationRepository repository;

    private NotificationServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new NotificationServiceImpl(repository);
    }

    @Test
    void getAllNotifications_returnsList() {
        Notification n1 = new Notification("acc1", "msg1", LocalDateTime.now());
        n1.setId(1L);
        Notification n2 = new Notification("acc2", "msg2", LocalDateTime.now());
        n2.setId(2L);
        when(repository.findAll()).thenReturn(Arrays.asList(n1, n2));

        List<NotificationResponse> list = service.getAllNotifications();
        assertEquals(2, list.size());
        assertEquals(1L, list.get(0).getId());
        assertEquals("acc1", list.get(0).getAccountId());
    }

    @Test
    void getNotificationsByAccount_returnsList() {
        Notification n = new Notification("acc1", "msg1", LocalDateTime.now());
        n.setId(3L);
        when(repository.findByAccountId("acc1")).thenReturn(Arrays.asList(n));

        List<NotificationResponse> list = service.getNotificationsByAccount("acc1");
        assertEquals(1, list.size());
        assertEquals(3L, list.get(0).getId());
        assertEquals("msg1", list.get(0).getMessage());
    }
}
