package seedu.hdbuy.common.exception;

import org.junit.jupiter.api.Test;

import seedu.hdbuy.ui.TextUi;

class ExceptionTest {

    @Test void exceptionTest() {
        try {
            throw new GatewayException();
        } catch (GatewayException e) {
            TextUi.showExceptionMessage(e);
        }

        try {
            throw new MissingFileException("test");
        } catch (MissingFileException e) {
            TextUi.showExceptionMessage(e);
        }
    }
}