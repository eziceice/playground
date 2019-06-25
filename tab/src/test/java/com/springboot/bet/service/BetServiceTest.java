package com.springboot.bet.service;

import com.springboot.bet.dao.BetDao;
import com.springboot.bet.enumeration.BetType;
import com.springboot.bet.pojo.Bet;
import com.springboot.bet.service.impl.BetServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;

import static org.mockito.Mockito.when;

/**
 * Bet Service Test
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BetServiceTest {

    private BetService betService;

    @MockBean
    private BetDao betDao;

    private Bet bet;

    @Before
    public void setUp() {
        betService = new BetServiceImpl();
        ReflectionTestUtils.setField(betService, "betDao", betDao);
        bet = new Bet();
        bet.setBetDate(new Date());
        bet.setBetType(BetType.DOUBLE);
        bet.setCustomerId(12345l);
        bet.setInvestmentAmount(100.0);
        when(betDao.insertBet(bet)).thenReturn(1);
    }

    @Test
    public void testInsertBet() {
        Bet returnBet = betService.insertBet(bet);
        Assert.assertEquals(returnBet, bet);
    }
}
