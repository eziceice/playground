package com.mebank.codechallenge.utils;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServiceUtil {

    /**
     * Convert String to Local Date Time
     *
     * @param dateSting
     * @return
     */
    public static LocalDateTime convertStringToLocalDateTime(String dateSting) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateSting, formatter);
        return localDateTime;
    }
}
