package com.springboot.bet.dao;

import com.springboot.bet.enumeration.BetType;
import com.springboot.bet.pojo.Bet;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Bet Dao Test
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BetDaoTest {

    @Autowired
    private BetDao betDao;

    @Test
    @Transactional
    public void testInsertUser() {
        Bet bet = new Bet();
        bet.setBetDate(new Date());
        bet.setBetType(BetType.DOUBLE);
        bet.setCustomerId(12345l);
        bet.setInvestmentAmount(100.0);
        int i = betDao.insertBet(bet);
        Assert.assertTrue(i != 0);
    }

    @Test
    public void testFindAllUsers() {
        List<Bet> bets = betDao.findAllBetsByDate(null, null);
        Assert.assertTrue(bets != null && bets.size() > 0);
    }
}
