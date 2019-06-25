package com.springboot.bet.controller;

import com.springboot.bet.dao.BetDao;
import com.springboot.bet.service.BetService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Bet Controller Test
 */
@RunWith(SpringRunner.class)
@WebMvcTest(BetController.class)
public class BetControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BetService betService;

    @MockBean
    private BetDao betDao;

    private String betJson;

    @Before
    public void setUp() {
        betJson = "{\"id\":0,\"betDate\":\"2025-04-30 09:22:22\",\"betType\":\"DOUBLE\",\"propNumber\":1," +
                "\"customerId\":12345,\"investmentAmount\":100.0}\n";
    }

    @Test
    public void testInsertBetWithSecurity() throws Exception {
        mvc.perform(post("/bet").header("Authorization", "Basic YWRtaW46MTIzNDU=").contentType(MediaType.APPLICATION_JSON).content(betJson)).andExpect(status().isOk());
    }

    @Test
    public void testInsertBetWithoutSecurity() throws Exception {
        mvc.perform(post("/bet").contentType(MediaType.APPLICATION_JSON).content(betJson)).andExpect(status().isUnauthorized());
    }
}
