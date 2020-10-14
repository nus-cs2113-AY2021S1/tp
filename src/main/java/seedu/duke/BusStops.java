package seedu.duke;

import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;

import java.util.EnumSet;

public enum BusStops {
    PGP("PGP", "prince george residence"),
    KENTRIDGEMRTSTATION("Kent Ridge MRT Station", "mrt"),
    OPPUNIVERSITYHEALTHCENTRE("Opp University Health Centre", "opposite UHC, where students can get MC"),
    YUSOFISHAKHOUSE("Yusof Ishak House", "Location with student service center and restaurants"),
    CENTRALLIBRARY("Central Library", "also known as CLB"),
    KENTRIDGE("Kent Ridge", "idk"),
    MUSEUM("Museum", "Opposite Faculty of Engineering"),
    UNIVERSITYTOWN("University Town", "Place full of fun"),
    UNIVERSITYHEALTHCENTRE("University Health Centre", "UHC, where students can get MC"),
    OPPKENTRIDGEMRTSTATION("Opp Kent Ridge MRT station", "MRT"),
    RAFFLESHALL("Raffles Hall", "Hall located nearby Faculty of Engineering"),
    KENTVALE("Kent Vale", "Accommodation for visiting faculty guests"),
    EA("EA", "located in Faculty of Engineering"),
    NUSIT("NUS IT", "Opposite Central library"),
    PGPR("PGPR", "prince george residence");

    private final String description;
    private final String name;

    BusStops(String name, String description) {
        this.description = description;
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public static String formatName(String input) throws CustomException {
        for (BusStops info: EnumSet.allOf(BusStops.class)){
            if(info.getName().equalsIgnoreCase(input)) {
                return info.getName();
            }
        }
        throw new CustomException(ExceptionType.INVALID_BUS_STOP);
    }

    public static void listStops() {
        for (BusStops info : EnumSet.allOf(BusStops.class)) {
            System.out.println(info.name + " : " + info.description);
        }
    }

}
