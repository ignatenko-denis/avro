package ru.sample;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AvroTest {
    @Test
    void test() throws IOException {
        Order expected = new Order();

        expected.setId(1234567890L);
        expected.setOrderType(OrderType.newBuilder().setType(OrderTypeSuit.CALL).build());
        expected.setComment("Comment");
        expected.setDate(LocalDate.now());
        expected.setTime(LocalTime.of(11, 11, 11));
        expected.setNumber(1234567890);
        expected.setDirection(DirectionSuit.BUY);
        expected.setPrice(new BigDecimal("12345678901234567890.123456789012345678901234567890"));

        Option option = Option.newBuilder()
                .setKey("key")
                .setName("name")
                .build();
        expected.setOptions(new ArrayList<>());
        expected.getOptions().add(option);

        ByteBuffer byteBuffer = expected.toByteBuffer();
        Order actual = Order.fromByteBuffer(byteBuffer);

        assertEquals(actual.getPrice(), expected.getPrice());
        assertEquals(actual, expected);
    }
}
