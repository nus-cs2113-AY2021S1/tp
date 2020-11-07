import exception.ArriveTimeException;
import exception.DayOfWeekException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parseCustomer() throws DayOfWeekException, ArriveTimeException {
        assertEquals("z", Parser.parseCustomer("z/2/1200").name);
        assertEquals(1, Parser.parseCustomer("z/2/1200").dayOfWeek);
        assertEquals(1200, Parser.parseCustomer("z/2/1200").arriveTime);
    }
}