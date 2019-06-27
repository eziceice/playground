package com.springboot.bet.exception;

/**
 * Invalid Date Exception
 */
public class InvalidDateException extends RuntimeException {

    public static final String INVALID_DATE_FORMAT = "Date Format is invalid, please check your date with following format: " +
            "yyyy-MM-dd HH:mm:ss";

    public static final String EXPIRED_DATE = "Date must not be in the past";

    public InvalidDateException(String exception) {
        super(exception);
    }
}
