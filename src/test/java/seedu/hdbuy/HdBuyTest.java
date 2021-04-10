package seedu.hdbuy;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.fail;

class HdBuyTest {

    @Test public void testMain() {
        final FileInputStream testInput;
        try {
            testInput = new FileInputStream(new File("./text-ui-test/input.txt"));
            System.setIn(testInput);
            HdBuy.main(null);
        } catch (FileNotFoundException e) {
            fail();
        }
    }
}
