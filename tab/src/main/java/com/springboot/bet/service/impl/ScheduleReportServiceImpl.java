package com.springboot.bet.service.impl;

import com.springboot.bet.calculator.BetCalculator;
import com.springboot.bet.dao.BetDao;
import com.springboot.bet.pojo.Bet;
import com.springboot.bet.service.ScheduleReportService;
import com.springboot.bet.utils.DateUtils;
import com.springboot.bet.utils.ReportGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Scheduled Report Service
 */
@Service
public class ScheduleReportServiceImpl implements ScheduleReportService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BetDao betDao;

    @Autowired
    private BetCalculator betCalculator;

    @Autowired
    private ReportGenerator reportGenerator;

    /**
     * Generate report based on cron expression, and it is a async method controlled by a thread
     */
    @Scheduled(cron = "${bet.report.execution.time}")
    @Override
    @Async
    @Transactional
    public void generateReport() {
        Date now = new Date();
        Date yesterday = DateUtils.getCalculatedDateFromHours(new Date(), -24);
        log.info("Start generating daily bets report for " + yesterday);
        List<Bet> betList = betDao.findAllBetsByDate(yesterday, now);
        Map<String, Double> totalInvestmentByType = betCalculator.calculateTotalInvestmentByType(betList);
        Map<Long, Double> totalInvestmentByCustomerId = betCalculator.calculateTotalInvestmentByCustomerId(betList);
        Map<String, Integer> totalBetsByType = betCalculator.calculateTotalBetsByType(betList);
        double totalBetsSoldByHour = betCalculator.calculateTotalBetsSoldByHour(betList);
        log.debug("Results for " + yesterday + " is. TotalInvestmentByType: " + totalInvestmentByType + ", TotalInvestmentByCustomerId: " + totalInvestmentByCustomerId
                + ", TotalBetsByType: " + totalBetsByType + ", TotalBetsSoldByHour: " + totalBetsSoldByHour);
        reportGenerator.generate(totalInvestmentByType, totalInvestmentByCustomerId, totalBetsByType, totalBetsSoldByHour, yesterday);
        log.info("Successfully generated daily bets report for " + yesterday + ", stop generating service");
    }
}
