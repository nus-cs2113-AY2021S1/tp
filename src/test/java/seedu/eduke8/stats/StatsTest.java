package seedu.eduke8.stats;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class StatsTest {

    @Test
    void statsConstructor_nullTopicListArgument_expectsAssertionError() {
        assertThrows(AssertionError.class, () -> {
            new Stats(null);
        });
    }
}