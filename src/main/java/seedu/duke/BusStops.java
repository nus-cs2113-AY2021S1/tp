package seedu.duke;


import java.util.EnumSet;

public enum BusStops {
    PGP("PGP","prince george residence"),
    KENTRIDGEMRTSTATION("Kent Ridge MRT Station","mrt"),
    OPPUNIVERSITYHEALTHCENTRE("Opp University Health Centre","opposite UHC, where students can get MC"),
    YUSOFISHAKHOUSE("Yusof Ishak House", "Location with student service center and restaurants"),
    CENTRALLIBRARY("Central Library","also known as CLB"),
    KENTRIDGE("Kent Ridge","idk"),
    MUSEUM("Museum", "Opposite Faculty of Engineering"),
    UNIVERSITYTOWN("University Town","place full of fun"),
    UNIVERSITYHEALTHCENTRE("University Health Centre","UHC, where students can get MC"),
    OPPKENTRIDGEMRTSTATION("OppKentRidgeMRTstation","MRT"),
    RAFFLESHALL("Raffles Hall","Hall located nearby Faculty of Engineering"),
    KENTVALE("Kent Vale","somewhere"),
    EA("EA","located in Faculty of Engineering"),
    NUSIT("NUS IT","Opposite Central library"),
    PGPR("PGPR","prince george residence");

    private final String description;
    private final String name;

    BusStops(String name,String description) {
        this.description = description;
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public static void listStops() {
        for (BusStops info : EnumSet.allOf(BusStops.class)) {
            System.out.println(info.name + " : " + info.description);
        }
    }
}