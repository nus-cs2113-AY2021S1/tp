import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class UITest {

    protected static Scanner sc = new Scanner(System.in);
    @Test
    void getCustomer() {
        try {
            assertEquals("z", UI.getCustomer(sc).name);
            assertEquals(1, UI.getCustomer(sc).dayOfWeek);
            assertEquals(1200, UI.getCustomer(sc).arriveTime);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Wrong Message", e.getMessage());
        }
    }
    @Test
    void getOrder(){

    }
}