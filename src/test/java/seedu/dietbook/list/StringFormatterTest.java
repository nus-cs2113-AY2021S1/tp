package seedu.dietbook.list;

import java.util.Map;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringFormatterTest {
    @Test
    @DisplayName("Pattern Replacement Test (Positive)")
    void replaceStringTest() throws NoReplacementFoundException {
        String original = "This test belongs to ${name} and is used in ${class}";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Me");
        map.put("class", "StringFormatterTest");
        String test = StringFormatter.formatStringWithMap(original, map);
        
        assertEquals("This test belongs to Me and is used in StringFormatterTest", test);
    }

    @Test
    @DisplayName("Pattern Mismatch Test (Negative)")
    void invalidMapping_mapWithMissingKey_exceptionThrown() {
        String original = "This test belongs to ${name} and is used in ${class}";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Me");
        assertThrows(NoReplacementFoundException.class, () -> StringFormatter.formatStringWithMap(original, map));

        try {
            StringFormatter.formatStringWithMap(original, map);
        } catch (NoReplacementFoundException e) {
            assertEquals("For key of: class", e.getMessage());
        }
    }
}
