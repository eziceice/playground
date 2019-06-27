package com.springboot.bet.service.impl;

import com.springboot.bet.dao.BetDao;
import com.springboot.bet.pojo.Bet;
import com.springboot.bet.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Bet Service
 */
@Service
public class BetServiceImpl implements BetService {

    @Autowired
    private BetDao betDao;

    @Override
    @Transactional
    public Bet insertBet(Bet bet) {
        betDao.insertBet(bet);
        return bet;
    }
}
