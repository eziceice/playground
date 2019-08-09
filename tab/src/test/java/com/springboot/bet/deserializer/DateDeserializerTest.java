package com.springboot.bet.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.when;

/**
 * Date Deserializer Test
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DateDeserializerTest {

    private DateDeserializer dateDeserializer;

    @MockBean
    private JsonParser jsonParser;

    @MockBean
    private DeserializationContext deserializationContext;

    @Before
    public void setUp() throws IOException {
        dateDeserializer = new DateDeserializer();
        when(jsonParser.getText()).thenReturn("2025-04-26 12:20:20");
    }

    @Test
    public void testDeserialize() throws IOException, ParseException {
        Date date = dateDeserializer.deserialize(jsonParser, deserializationContext);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date expectDate = sdf.parse("2025-04-26 12:20:20");
        Assert.assertEquals(date, expectDate);
    }
}
