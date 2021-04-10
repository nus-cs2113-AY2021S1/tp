package seedu.hdbuy.storage;

import seedu.hdbuy.common.HdBuyLogger;
import seedu.hdbuy.common.Unit;

public class UnitDecoder {

    public static Unit textToUnit(String text) {
        if (text.isEmpty()) {
            return null;
        }
        try {
            String[] parameters = text.split(":");
            int id = Integer.parseInt(parameters[0]);
            String address = parameters[1];
            String type = parameters[2];
            int leaseValue = Integer.parseInt(parameters[3]);
            String lease = parameters[4];
            int price = Integer.parseInt(parameters[5]);
            String location = parameters[6];
            return new Unit(location, type, price, leaseValue, lease, address, id);
        } catch (Exception exception) {
            HdBuyLogger.error("Unable to decode text file");
            return null;
        }
    }
}
