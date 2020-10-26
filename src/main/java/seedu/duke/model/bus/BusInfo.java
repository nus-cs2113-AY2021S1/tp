package seedu.duke.model.bus;

import java.util.ArrayList;

public class BusInfo {
    private static ArrayList<Bus> busesInNUS = new ArrayList<>();
    private static final String[] NUS = {"AA1", "AA2", "BB", "CC", "CCX", "DD1", "DD2"};

    public BusInfo() {
        busesInNUS.add(new Bus(NUS[0], AA1));
        busesInNUS.add(new Bus(NUS[1], AA2));
        busesInNUS.add(new Bus(NUS[2], BB));
        busesInNUS.add(new Bus(NUS[3], CC));
        busesInNUS.add(new Bus(NUS[4], CCX));
        busesInNUS.add(new Bus(NUS[5], DD1));
        busesInNUS.add(new Bus(NUS[6], DD2));
        BusData.createBusList(busesInNUS);
    }

    private static final BusStops[] AA1 = {
        BusStops.PGP,
        BusStops.KENTRIDGEMRTSTATION,
        BusStops.OPPUNIVERSITYHEALTHCENTRE,
        BusStops.YUSOFISHAKHOUSE,
        BusStops.CENTRALLIBRARY,
        BusStops.KENTRIDGE,
        BusStops.MUSEUM,
        BusStops.UNIVERSITYTOWN,
        BusStops.UNIVERSITYHEALTHCENTRE,
        BusStops.OPPKENTRIDGEMRTSTATION,
        BusStops.PGPR
    };

    private static final BusStops[] AA2 = {
        BusStops.PGP,
        BusStops.KENTRIDGEMRTSTATION,
        BusStops.OPPUNIVERSITYHEALTHCENTRE,
        BusStops.UNIVERSITYTOWN,
        BusStops.RAFFLESHALL,
        BusStops.KENTVALE,
        BusStops.EA,
        BusStops.NUSIT,
        BusStops.UNIVERSITYHEALTHCENTRE,
        BusStops.OPPKENTRIDGEMRTSTATION,
        BusStops.PGPR
    };

    private static final BusStops[] BB = {
        BusStops.PGP,
        BusStops.KENTRIDGEMRTSTATION,
        BusStops.LT27,
        BusStops.UNIVERSITYHALL,
        BusStops.OPPUNIVERSITYHEALTHCENTRE,
        BusStops.UNIVERSITYTOWN,
        BusStops.UNIVERSITYHEALTHCENTRE,
        BusStops.OPPUNIVERSITYHALL,
        BusStops.S17,
        BusStops.OPPKENTRIDGEMRTSTATION,
        BusStops.PGPR
    };

    private static final BusStops[] CC = {
        BusStops.PGPR,
        BusStops.TCOMS,
        BusStops.OPPHSSML,
        BusStops.OPPNUSS,
        BusStops.COM2,
        BusStops.VENTUS,
        BusStops.NUSIT,
        BusStops.OPPYUSOFISHAKHOUSE,
        BusStops.UNIVERSITYTOWN,
        BusStops.YUSOFISHAKHOUSE,
        BusStops.CENTRALLIBRARY,
        BusStops.LT13,
        BusStops.AS5,
        BusStops.COM2,
        BusStops.BIZ2,
        BusStops.OPPTCOMS,
        BusStops.PGP,
        BusStops.OPPKENTRIDGEMRTSTATION
    };

    private static final BusStops[] CCX = {
        BusStops.OPPKENTRIDGEMRTSTATION,
        BusStops.UNIVERSITYTOWN,
        BusStops.OPPKENTRIDGEMRTSTATION
    };

    private static final BusStops[] DD1 = {
        BusStops.OEITIONGHAM,
        BusStops.BOTANICGARDENSMRT,
        BusStops.UNIVERSITYTOWN,
        BusStops.KENTVALE
    };
    private static final BusStops[] DD2 = {
        BusStops.KENTVALE,
        BusStops.UNIVERSITYTOWN,
        BusStops.COLLEGEGREEN,
        BusStops.OEITIONGHAM
    };

}
