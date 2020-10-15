package seedu.duke;

import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;

import java.util.EnumSet;

import static seedu.duke.ui.Ui.printLine;

public enum BusStops {
    PGP("PGP", "Prince George's Park"),
    KENTRIDGEMRTSTATION("Kent Ridge MRT Station", "Kent Ridge MRT entrance"),
    OPPUNIVERSITYHEALTHCENTRE("Opp University Health Centre", "Near University Health Centre"),
    YUSOFISHAKHOUSE("Yusof Ishak House", "Location with the Student Service center"),
    CENTRALLIBRARY("Central Library", "also known as CLB"),
    KENTRIDGE("Kent Ridge", "Kent Ridge"),
    MUSEUM("Museum", "Opposite Faculty of Engineering"),
    UNIVERSITYTOWN("University Town", "Place filled with dining and recreational options"),
    UNIVERSITYHEALTHCENTRE("University Health Centre", "UHC, where students can get MC"),
    OPPKENTRIDGEMRTSTATION("Opp Kent Ridge MRT station", "Opposite Kent Ridge MRT"),
    RAFFLESHALL("Raffles Hall", "Hall located near Faculty of Engineering"),
    KENTVALE("Kent Vale", "Accommodation for visiting faculty guests"),
    EA("EA", "Located in Faculty of Engineering"),
    NUSIT("NUS IT", "Opposite Central library"),
    PGPR("PGPR", "Prince George's Park Residence");

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
        for (BusStops info: EnumSet.allOf(BusStops.class)) {
            if (info.name.equalsIgnoreCase(input)) {
                return info.name;
            }
        }
        throw new CustomException(ExceptionType.INVALID_BUS_STOP);
    }

    public static void listStops() {
        printLine();
        for (BusStops info : EnumSet.allOf(BusStops.class)) {
            System.out.println(info.name + " : " + info.description);
        }
        printLine();
    }

}
