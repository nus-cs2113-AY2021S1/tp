package seedu.hdbuy;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

class HdBuyTest {

    @Test public void testMain() {
        String testInput = "exit";
        ByteArrayInputStream bytes = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(bytes);
        HdBuy.main(null);
    }
}
