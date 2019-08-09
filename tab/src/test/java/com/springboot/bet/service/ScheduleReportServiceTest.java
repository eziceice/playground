package com.springboot.bet.service;

import com.springboot.bet.calculator.BetCalculator;
import com.springboot.bet.dao.BetDao;
import com.springboot.bet.pojo.Bet;
import com.springboot.bet.service.impl.ScheduleReportServiceImpl;
import com.springboot.bet.utils.ApplicationProperties;
import com.springboot.bet.utils.DateUtils;
import com.springboot.bet.utils.ReportGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.springboot.bet.utils.ApplicationConstants.DATE_FORMAT;
import static org.mockito.Mockito.when;

/**
 * Unit Test for Report Service
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleReportServiceTest {

    private static final String testReportPath = "./src/test/java/com/springboot/bet/report/";
    @MockBean
    private BetDao betDao;
    @Autowired
    private BetCalculator betCalculator;
    @Autowired
    private ReportGenerator reportGenerator;
    private List<Bet> betList = new ArrayList<>();
    private ScheduleReportService scheduleReportService;
    @Autowired
    private ApplicationProperties applicationProperties;

    /*
     * Expected test HTML String
     */
    private String expectedResult = "<!DOCTYPE html><html><head>    <style>        body {            background-color: #d0e4fe;        }        " +
            "h1 {            color: orange;            text-align: center;        }        p {            font-family: \"Times New Roman\";            " +
            "font-size: 20px;        }    </style></head><body><h1>Daily Bets Report</h1><p>This is a daily bets report for 2019-04-27</p><h3>Total investment per bet type:</h3>" +
            "<table>    <tr>        <td>Type: WIN</td>        <td>TotalAmount: $0.0</td>    </tr></table><table>    <tr>        <td>Type: PLACE</td>        " +
            "<td>TotalAmount: $0.0</td>    </tr></table>" +
            "<table>    <tr>        <td>Type: TRIFECTA</td>        <td>TotalAmount: $0.0</td>    </tr></table><table>    <tr>        " +
            "<td>Type: DOUBLE</td>        <td>TotalAmount: $0.0</td>    </tr></table><table>    <tr>        <td>Type: QUADDIE</td>        " +
            "<td>TotalAmount: $0.0</td>    </tr></table><h3>Total bets sold per bet type:</h3><table>    <tr>        <td>Type: WIN</td>        " +
            "<td>Sold: 0</td>    </tr></table><table>    <tr>        <td>Type: PLACE</td>        <td>Sold: 0</td>    </tr></table><table>    <tr>        " +
            "<td>Type: TRIFECTA</td>        <td>Sold: 0</td>    </tr></table><table>    <tr>        <td>Type: DOUBLE</td>        <td>Sold: 0</td>    </tr></table>" +
            "<table>    <tr>        <td>Type: QUADDIE</td>        <td>Sold: 0</td>    </tr></table><h3>Total bets sold per hour: </h3><table>    <tr>        " +
            "<td>Sold per hour: 0.0</td>    </tr></table><h3>Total investment per customer id:</h3><table>    <tr>        <td>No purchase history for any customers!</td>   " +
            " </tr></table></body></html>";

    @Before
    public void setUp() {

        ReflectionTestUtils.setField(applicationProperties, "reportPath", testReportPath);
        scheduleReportService = new ScheduleReportServiceImpl();
        ReflectionTestUtils.setField(scheduleReportService, "betDao", betDao);
        ReflectionTestUtils.setField(scheduleReportService, "betCalculator", betCalculator);
        ReflectionTestUtils.setField(reportGenerator, "applicationProperties", applicationProperties);
        ReflectionTestUtils.setField(scheduleReportService, "reportGenerator", reportGenerator);
        when(betDao.findAllBetsByDate(null, null)).thenReturn(betList);
    }

    @Test
    public void testGenerateReport() throws IOException {
        scheduleReportService.generateReport();
        String content = readReportContent();
        Assert.assertEquals(content, expectedResult);
    }

    private String readReportContent() throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        Date date = DateUtils.getCalculatedDateFromHours(new Date(), -24);
        String dateString = new SimpleDateFormat(DATE_FORMAT).format(date);
        BufferedReader reader = new BufferedReader(new FileReader(testReportPath + "DailyBetsReport-" + dateString + ".html"));

        String str;
        while ((str = reader.readLine()) != null) {
            contentBuilder.append(str);
        }
        reader.close();

        String content = contentBuilder.toString();
        return content;
    }
}
