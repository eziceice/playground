package com.springboot.bet.calculator;

import com.springboot.bet.enumeration.BetType;
import com.springboot.bet.pojo.Bet;
import com.springboot.bet.utils.DateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * Bet Calculator Test
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BetCalculatorTest {
    @Autowired
    private BetCalculator betCalculator;

    private List<Bet> betList = new ArrayList<>();

    @Before
    public void initialTestData() {
        for (int i = 0; i < 9; i++) {
            Bet bet = new Bet();
            bet.setBetDate(new Date());
            bet.setBetType(BetType.DOUBLE);
            bet.setCustomerId(12345l);
            bet.setInvestmentAmount(100d);
            betList.add(bet);
        }

        Bet bet = new Bet();
        bet.setBetDate(DateUtils.getCalculatedDateFromHours(new Date(), 1));
        bet.setBetType(BetType.DOUBLE);
        bet.setCustomerId(12345l);
        bet.setInvestmentAmount(100d);
        betList.add(bet);
    }

    @Test
    public void testCalculateTotalInvestmentByCustomerId() {
        Map<Long, Double> results = betCalculator.calculateTotalInvestmentByCustomerId(betList);
        Assert.assertEquals(results.get(12345l), new Double(1000));
        Assert.assertEquals(results.size(), 1);
    }

    @Test
    public void testCalculateTotalInvestmentByType() {
        Map<String, Double> results = betCalculator.calculateTotalInvestmentByType(betList);
        Assert.assertEquals(results.get(BetType.DOUBLE.getCode()), new Double(1000));
        Assert.assertEquals(results.get(BetType.WIN.getCode()), new Double(0));
        Assert.assertEquals(results.get(BetType.PLACE.getCode()), new Double(0));
        Assert.assertEquals(results.get(BetType.QUADDIE.getCode()), new Double(0));
        Assert.assertEquals(results.get(BetType.TRIFECTA.getCode()), new Double(0));
    }

    @Test
    public void testCalculateTotalBetsByType() {
        Map<String, Integer> results = betCalculator.calculateTotalBetsByType(betList);
        Assert.assertEquals(results.get(BetType.DOUBLE.getCode()), new Integer(10));
        Assert.assertEquals(results.get(BetType.WIN.getCode()), new Integer(0));
        Assert.assertEquals(results.get(BetType.PLACE.getCode()), new Integer(0));
        Assert.assertEquals(results.get(BetType.QUADDIE.getCode()), new Integer(0));
        Assert.assertEquals(results.get(BetType.TRIFECTA.getCode()), new Integer(0));
    }

    @Test
    public void testCalculateTotalBetsSoldByHour() {
        double result = betCalculator.calculateTotalBetsSoldByHour(betList);
        Assert.assertEquals(result, 0.42, 1);
    }
}
