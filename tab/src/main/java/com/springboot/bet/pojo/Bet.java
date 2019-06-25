package com.springboot.bet.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.springboot.bet.deserializer.BetTypeDeserializer;
import com.springboot.bet.deserializer.DateDeserializer;
import com.springboot.bet.enumeration.BetType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * POJO for bet
 */
@Getter
@Setter
@ToString
@Alias(value = "bet")
public class Bet {
    private long id;

    @JsonDeserialize(using = DateDeserializer.class)
    private Date betDate;

    @JsonDeserialize(using = BetTypeDeserializer.class)
    private BetType betType;

    @Positive(message = "propNumber must exist and be greater than 0")
    private int propNumber;

    @Positive(message = "customerId must exist and be greater than 0")
    private long customerId;

    @Max(20000)
    @Positive(message = "investmentAmount must exist and be greater than 0 and less than 20000")
    private double investmentAmount;
}
