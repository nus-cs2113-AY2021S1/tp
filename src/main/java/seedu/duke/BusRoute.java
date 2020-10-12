package seedu.duke;

import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;

public class BusRoute {

    /*Command Prefixes*/
    private static final String ROUTE_AA1 = "aa1";
    private static final String ROUTE_AA2 = "aa2";

    String[] zoneA = {"AA1", "AA2"};

    static String[] aa1 = {
        "PGP",
        "Kent Ridge MRT Station",
        "Opp University Health Center",
        "Yusof Ishak House",
        "Central Library",
        "Kent Ridge",
        "Museum",
        "University Town",
        "University Health Centre",
        "Opp Kent Ridge MRT station",
        "PGPR"
    };

    static String[] aa2 = {
        "PGP",
        "Kent Ridge MRT Station",
        "Opp University Health Centre",
        "University Town",
        "Raffles Hall",
        "Kent Vale",
        "EA",
        "NUS IT",
        "University Health Centre",
        "Opp Kent Ridge MRT Station",
        "PGPR"
    };

    public static String selectBusRoute(String busNumber) throws CustomException {
        String fullBusRoute;
        switch (busNumber) {
        case ROUTE_AA1:
            fullBusRoute = getBusRoute(aa1);
            return fullBusRoute;
        case ROUTE_AA2:
            fullBusRoute = getBusRoute(aa2);
            return fullBusRoute;
        default:
            throw new CustomException(ExceptionType.INVALID_COMMAND);
        }
    }

    private static String getBusRoute(String[] routeName) {
        String busRoute = "";
        for (int i = 0; i < routeName.length; i++) {
            busRoute += routeName[i];
            if (i != (routeName.length - 1)) {
                busRoute += (" > ");
            }
        }
        return busRoute;
    }
}
