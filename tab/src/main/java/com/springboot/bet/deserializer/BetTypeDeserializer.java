package com.springboot.bet.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.springboot.bet.enumeration.BetType;
import com.springboot.bet.exception.InvalidBetTypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Json deserializer for bet type
 */
public class BetTypeDeserializer extends JsonDeserializer<BetType> {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public BetType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String str = jsonParser.getText().trim().toUpperCase();
        if (!BetType.getIsValidTypes(str)) {
            log.error("Invalid bet type in JSON: " + str);
            // throw exception if it is not a valid bet type
            throw new InvalidBetTypeException();
        }
        return BetType.valueOf(str);
    }
}
