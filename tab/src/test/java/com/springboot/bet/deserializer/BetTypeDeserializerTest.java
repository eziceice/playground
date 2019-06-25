package com.springboot.bet.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.springboot.bet.enumeration.BetType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.mockito.Mockito.when;

/**
 * Bet Type Deserializer Test
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BetTypeDeserializerTest {

    private BetTypeDeserializer betTypeDeserializer;

    @MockBean
    private JsonParser jsonParser;

    @MockBean
    private DeserializationContext deserializationContext;

    @Before
    public void setUp() throws IOException {
        betTypeDeserializer = new BetTypeDeserializer();
        when(jsonParser.getText()).thenReturn("Win");
    }

    @Test
    public void testDeserialize() throws IOException {
        BetType betType = betTypeDeserializer.deserialize(jsonParser, deserializationContext);
        Assert.assertEquals(betType, BetType.WIN);
    }
}
