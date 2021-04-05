package seedu.hdbuy.common.exception;

import org.junit.jupiter.api.Test;

import seedu.hdbuy.ui.TextUi;

import static org.junit.jupiter.api.Assertions.*;

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