package locationlist;

import location.BusStop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BusStopList {
    private static final String filePath = "data/BusStops.txt";
    private ArrayList<BusStop> busStopList = new ArrayList<>();

    public void loadBusStopData() {
        File f = new File(filePath);
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println(f.getName() + "not found: " + e.getMessage());
        }

        while(s.hasNext()) {
            String input = s.nextLine();
            String[] split = input.split(":",2);
            String name = split[0];
            String[] buses = split[1].split(",");
            BusStop stop = new BusStop(name, buses);
            this.busStopList.add(stop);
        }
    }

    public boolean checkExistence(String name) {
        for (BusStop busStop : busStopList) {
            if (name.equals(busStop.getName())) {
                return true;
            }
        }
        return false;
    }

    public void printBusStopList() {
        int i = 1;
        System.out.println("List of bus stops: ");
        for(BusStop busStop : busStopList) {
            System.out.println(i + ". " + busStop);
            i++;
        }
    }
}
