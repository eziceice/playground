package com.springboot.bet.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.springboot.bet.exception.InvalidDateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Json deserializer for Date
 */
public class DateDeserializer extends JsonDeserializer<Date> {

    private SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public Date deserialize(JsonParser paramJsonParser,
                            DeserializationContext paramDeserializationContext)
            throws IOException {
        String str = paramJsonParser.getText().trim();

        try {
            Date date = new Date();
            Date betDate = dateFormat.parse(str);
            if (betDate.before(date)) {
                log.error("Date in JSON is a past date " + str);
                throw new InvalidDateException(InvalidDateException.EXPIRED_DATE);
            }
            return betDate;
        } catch (ParseException e) {
            log.error("Invalid Date format: " + str, e);
            throw new InvalidDateException(InvalidDateException.INVALID_DATE_FORMAT);
        }
    }
}
