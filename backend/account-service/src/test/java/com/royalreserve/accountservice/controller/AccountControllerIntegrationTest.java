package com.royalreserve.accountservice.controller;

import com.royalreserve.accountservice.dto.AccountRequest;
import com.royalreserve.accountservice.model.Account;
import com.royalreserve.accountservice.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AccountRepository repository;

    @Test
    void createAndGetAccount() throws Exception {
        repository.deleteAll();

        String body = "{ \"ownerName\": \"Test\", \"email\": \"test@example.com\", \"initialBalance\": 20 }";
        mvc.perform(post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", containsString("/api/accounts/")))
            .andExpect(jsonPath("$.id", notNullValue()));

        mvc.perform(get("/api/accounts"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].ownerName", is("Test")));
    }
}
