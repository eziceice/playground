package com.springboot.bet.utils;

import com.springboot.bet.enumeration.BetType;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static com.springboot.bet.utils.ApplicationConstants.*;

/**
 * Report Generator Util
 */
@Component
public class ReportGenerator {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ApplicationProperties applicationProperties;

    public void generate(Map<String, Double> totalInvestmentByType, Map<Long, Double> totalInvestmentByCustomerId,
                         Map<String, Integer> totalBetsByType, double totalBetsSoldByHour, Date date) {
        File htmlTemplate;
        String dateString = new SimpleDateFormat(DATE_FORMAT).format(date);

        try {
            htmlTemplate = new ClassPathResource(REPORT_TEMPLATE_FILE).getFile();
            String htmlString = FileUtils.readFileToString(htmlTemplate);
            File newHtmlFile = new File(applicationProperties.getReportPath() + "DailyBetsReport-" + dateString + ".html");
            FileUtils.writeStringToFile(newHtmlFile, render(htmlString, dateString, totalInvestmentByType, totalInvestmentByCustomerId, totalBetsByType, totalBetsSoldByHour));
        } catch (Exception e) {
            log.error("Exception happened while generating report: " + e.getMessage() + ", for daily bets report at date: " + dateString, e);
        }
    }

    /**
     * Render the HTML string based on input value
     *
     * @param htmlString
     * @param dateString
     * @param totalInvestmentByType
     * @param totalInvestmentByCustomerId
     * @param totalBetsByType
     * @param totalBetsSoldByHour
     * @return
     */
    private String render(String htmlString, String dateString, Map<String, Double> totalInvestmentByType, Map<Long, Double> totalInvestmentByCustomerId,
                          Map<String, Integer> totalBetsByType, double totalBetsSoldByHour) {
        htmlString = htmlString.replace(DATE_TAG, dateString);
        htmlString = htmlString.replace(WIN_AMOUNT_TAG, totalInvestmentByType.get(BetType.WIN.getCode()).toString());
        htmlString = htmlString.replace(PLACE_AMOUNT_TAG, totalInvestmentByType.get(BetType.PLACE.getCode()).toString());
        htmlString = htmlString.replace(TRIFECTA_AMOUNT_TAG, totalInvestmentByType.get(BetType.TRIFECTA.getCode()).toString());
        htmlString = htmlString.replace(DOUBLE_AMOUNT_TAG, totalInvestmentByType.get(BetType.DOUBLE.getCode()).toString());
        htmlString = htmlString.replace(QUADDIE_AMOUNT_TAG, totalInvestmentByType.get(BetType.QUADDIE.getCode()).toString());

        htmlString = htmlString.replace(WIN_SOLD_TAG, totalBetsByType.get(BetType.WIN.getCode()).toString());
        htmlString = htmlString.replace(PLACE_SOLD_TAG, totalBetsByType.get(BetType.PLACE.getCode()).toString());
        htmlString = htmlString.replace(TRIFECTA_SOLD_TAG, totalBetsByType.get(BetType.TRIFECTA.getCode()).toString());
        htmlString = htmlString.replace(DOUBLE_SOLD_TAG, totalBetsByType.get(BetType.DOUBLE.getCode()).toString());
        htmlString = htmlString.replace(QUADDIE_SOLD_TAG, totalBetsByType.get(BetType.QUADDIE.getCode()).toString());
        htmlString = htmlString.replace(SOLD_PER_HOUR_TAG, Double.toString(totalBetsSoldByHour));

        int size = totalInvestmentByCustomerId.size();
        int position = htmlString.lastIndexOf("</h3>");
        String before = htmlString.substring(0, position + 5);
        String after = htmlString.substring(position + 5);
        StringBuilder sb = new StringBuilder(before);

        if (size == 0) {
            sb.append("<table>\n" +
                    "    <tr>\n" +
                    "        <td>No purchase history for any customers!</td>\n" +
                    "    </tr>\n" +
                    "</table>");
        } else {
            for (int i = 0; i < size; i++) {
                sb.append("<table>\n" +
                        "    <tr>\n" +
                        "        <td>Customer ID: " + CUSOMTER_ID_TAG + i + "</td>\n" +
                        "        <td>TotalAmount: " + CUSTOMER_ID_AMOUNT_TAG + i + "</td>\n" +
                        "    </tr>\n" +
                        "</table>");
            }
        }

        String newHtmlString = sb.toString() + after;

        int i = 0;
        for (Long id : totalInvestmentByCustomerId.keySet()) {
            newHtmlString = newHtmlString.replace(CUSOMTER_ID_TAG + i, id.toString()).replace(CUSTOMER_ID_AMOUNT_TAG + i, totalInvestmentByCustomerId.get(id).toString());
            i++;
        }
        return newHtmlString;
    }
}
