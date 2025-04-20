package com.royalreserve.discoveryserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DiscoveryServerApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void actuatorHealthUp() throws Exception {
        mockMvc.perform(get("/actuator/health"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("UP"));
    }

    @Test
    void actuatorInfoAvailable() throws Exception {
        mockMvc.perform(get("/actuator/info"))
            .andExpect(status().isOk());
    }

    @Test
    void eurekaAppsAccessible() throws Exception {
        mockMvc.perform(get("/eureka/apps"))
            .andExpect(status().isOk());
    }
}
