package location;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import parser.Parser;

public class LocationListTest {

    @Test
    void execute_cannotFindLocation_NullPointerException() {
        Assertions.assertThrows(NullPointerException.class, ()-> {
           Location l = Parser.parseLocation("location", null);
        });
    }
}
