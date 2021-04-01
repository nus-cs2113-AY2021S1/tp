package seedu.hdbuy.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import seedu.hdbuy.common.HdBuyLogger;
import seedu.hdbuy.common.exception.EmptyResponseException;
import seedu.hdbuy.common.exception.GatewayException;

public class ResponseDecoder {

    /**
     * Converts response body into units mapped by a unique ID from hashcode.
     *
     * @param inputStream Response body.
     * @throws EmptyResponseException When no units are retrieved.
     * @throws GatewayException       When there is an error connecting to database.
     */
    public static void decodeResponse(InputStream inputStream) throws EmptyResponseException, GatewayException {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String response = in.readLine();
            in.close();
            UnitsGenerator.getUnits(response);
        } catch (IOException ioException) {
            HdBuyLogger.error("Failed to read response");
            throw new GatewayException();
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            HdBuyLogger.error(arrayIndexOutOfBoundsException.getMessage());
            throw new EmptyResponseException();
        }
    }
}