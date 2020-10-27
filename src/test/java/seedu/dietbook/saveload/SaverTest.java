package seedu.dietbook.saveload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

public class SaverTest {
    private Saver saver;

    @BeforeEach
    public void setUp() {
        saver = new Saver(10, 6);
        saver.add("banana", 5, 2);
        saver.add("pineapple", 7, 1);
    }

    @Test
    public void get() {
        assertEquals(Optional.of("banana"), saver.get(5,2));
        assertEquals(Optional.of("pineapple"), saver.get(7,1));
        assertEquals(Optional.empty(), saver.get(1,1));
        assertThrows(IndexOutOfBoundsException.class, () -> saver.get(-1992,3500));
    }

    @Test
    public void delete() {
        saver.add("Deletion lotion", 4,2);
        assertEquals(Optional.of("Deletion lotion"), saver.get(4,2));
        saver.delete(4,2);
        assertEquals(Optional.empty(), saver.get(4,2));
        assertThrows(IndexOutOfBoundsException.class, () -> saver.delete(-3402,9999));
    }
}
