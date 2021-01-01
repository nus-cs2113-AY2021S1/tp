import exception.ArriveTimeException;
import exception.DayOfWeekException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    @Test
    void parseCustomer() throws DayOfWeekException, ArriveTimeException {
        assertEquals("z", Parser.parseCustomer("z/2/1200").name);
        assertEquals(1, Parser.parseCustomer("z/2/1200").dayOfWeek);
        assertEquals(1200, Parser.parseCustomer("z/2/1200").arriveTime);
    }

    @Test
    void parseList() {
        assertEquals(Arrays.asList(1,2,3),Parser.parseList("[1,2,3]"));
    }
}