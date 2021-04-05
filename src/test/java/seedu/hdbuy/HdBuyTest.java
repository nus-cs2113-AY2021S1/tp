package seedu.hdbuy;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import seedu.hdbuy.common.exception.InvalidIndexException;
import seedu.hdbuy.common.exception.InvalidParameterException;
import seedu.hdbuy.parser.CommandEvaluator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HdBuyTest {

    @Test public void testMain() throws IOException {
        final InputStream inputStream = System.in;
        final FileInputStream testInput = new FileInputStream(new File("./text-ui-test/input.txt"));
        System.setIn(testInput);
        HdBuy.main(null);
        System.setIn(inputStream);
    }
}
