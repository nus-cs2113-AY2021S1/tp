package seedu.hdbuy.common.exception;

import org.junit.jupiter.api.Test;

import seedu.hdbuy.ui.TextUi;

class ExceptionTest {

    @Test void exceptionTest() {
        try {
            throw new GatewayException();
        } catch (GatewayException e) {
            TextUi.showGateWayError(e);
        }

        try {
            throw new MissingFileException();
        } catch (MissingFileException e) {
            TextUi.showMissingFileError(e);
        }
    }
}