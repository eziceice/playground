package com.springboot.bet.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Error Details POJO in failed http response
 */
@Getter
@Setter
public class ErrorDetails {
    private Date timestamp;
    private List<String> message;
    private String details;

    public ErrorDetails(Date timestamp, List<String> message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
