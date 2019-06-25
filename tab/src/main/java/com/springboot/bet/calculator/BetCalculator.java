package com.springboot.bet.calculator;

import com.springboot.bet.enumeration.BetType;
import com.springboot.bet.pojo.Bet;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Calculator Utility for Bets
 */
@Component
public class BetCalculator {

    /**
     * Calculate total investment per customer Id
     *
     * @param betList
     * @return
     */
    public Map<Long, Double> calculateTotalInvestmentByCustomerId(List<Bet> betList) {
        Map<Long, Double> investments = new HashMap<>();
        for (Bet bet : betList) {
            Long customerId = bet.getCustomerId();
            if (investments.containsKey(customerId)) {
                investments.put(customerId, investments.get(customerId) + bet.getInvestmentAmount());
            } else {
                investments.put(customerId, bet.getInvestmentAmount());
            }
        }
        return investments;
    }

    /**
     * Calculate total investment per bet type
     *
     * @param betList
     * @return
     */
    public Map<String, Double> calculateTotalInvestmentByType(List<Bet> betList) {
        Map<String, Double> investments = new HashMap<>();

        for (BetType type : BetType.values()) {
            investments.put(type.getCode(), 0.0d);
        }

        for (Bet bet : betList) {
            String betType = bet.getBetType().getCode();
            investments.put(betType, investments.get(betType) + bet.getInvestmentAmount());
        }
        return investments;
    }

    /**
     * Calculate total bets per type
     *
     * @param betList
     * @return
     */
    public Map<String, Integer> calculateTotalBetsByType(List<Bet> betList) {
        Map<String, Integer> bets = new HashMap<>();

        for (BetType type : BetType.values()) {
            bets.put(type.getCode(), 0);
        }

        for (Bet bet : betList) {
            String betType = bet.getBetType().getCode();
            bets.put(betType, bets.get(betType) + 1);
        }
        return bets;
    }

    /**
     * Calculate total bets per hour
     *
     * @param betList
     * @return
     */
    public double calculateTotalBetsSoldByHour(List<Bet> betList) {
        return Math.round((betList.size() / 24d) * 100.0) / 100.0;
    }
}
