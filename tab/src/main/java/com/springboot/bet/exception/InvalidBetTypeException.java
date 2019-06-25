package com.springboot.bet.exception;

/**
 * Invalid Bet Type Exception
 */
public class InvalidBetTypeException extends RuntimeException {
    private static final String INVALID_BET_TYPE = "Invalid Bet Type. Please use following types:" +
            "1.WIN 2.PLACE 3.TRIFECTA 4.DOUBLE 5.QUADDIE";

    public InvalidBetTypeException() {
        super(INVALID_BET_TYPE);
    }
}
