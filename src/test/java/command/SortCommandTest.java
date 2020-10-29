package command;

import exception.InvalidSortCriteriaException;
import exception.NoSortCriteriaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import parser.Parser;


class SortCommandTest {

    @Test
    void execute_NoSortCriteria_NoSortCriteriaException() {
        Assertions.assertThrows(NoSortCriteriaException.class, () -> {
            Command d = Parser.parse("sort",  null);
        });
    }

    @Test
    void execute_InvalidSortCriteria_InvalidSortCriteriaException() {
        Assertions.assertThrows(InvalidSortCriteriaException.class, () -> {
            Command d = Parser.parse("sort something",  null);
        });
    }
}