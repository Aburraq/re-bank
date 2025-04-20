package com.royalreserve.notificationservice.controller;

import com.royalreserve.notificationservice.dto.NotificationResponse;
import com.royalreserve.notificationservice.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NotificationController.class)
class NotificationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private NotificationService service;

    @Test
    void getAll_shouldReturnList() throws Exception {
        NotificationResponse n1 = new NotificationResponse(1L, "acc1", "msg1", LocalDateTime.now());
        NotificationResponse n2 = new NotificationResponse(2L, "acc2", "msg2", LocalDateTime.now());
        when(service.getAllNotifications()).thenReturn(Arrays.asList(n1, n2));

        mvc.perform(get("/api/notifications"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[1].accountId").value("acc2"));
    }

    @Test
    void getByAccount_shouldReturnList() throws Exception {
        NotificationResponse n = new NotificationResponse(3L, "acc3", "msg3", LocalDateTime.now());
        when(service.getNotificationsByAccount("acc3")).thenReturn(Arrays.asList(n));

        mvc.perform(get("/api/notifications/account/acc3"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].message").value("msg3"));
    }
}
