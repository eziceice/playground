package com.springboot.bet.dao;

import com.springboot.bet.pojo.Bet;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Dao For Bet
 */
@Repository
public interface BetDao {

    int insertBet(Bet bet);

    /**
     * Find All bets based on date.
     * If dateFrom is null, then it will fetch all the records to dateTo.
     * IF dateTo is null, then it will fetch all the records from dateFrom.
     * IF both are null, it will fetch all the records in database.
     *
     * @param dateFrom
     * @param dateTo
     * @return
     */
    List<Bet> findAllBetsByDate(@Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo);
}
