package locationlist;

import location.Building;
import location.Hostel;
import location.LectureTheatre;
import location.Location;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LocationList {
    private static final String filePath = "data/locations.txt";
    public ArrayList<Location> locationList = new ArrayList<>();

    public void loadLocationData() {
        File f = new File(filePath);
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println(f.getName() + "not found: " + e.getMessage());
        }

        while(s.hasNext()) {
            String input = s.nextLine();
            // info[0] = type, info[1] = name, info[2] = nearest buildings/bus stops
            String[] info = input.split("/");
            String[] additionalInfo = info[2].split(",");
            Location location = null;
            switch(info[0]) {
                case "BLK":
                    location = new Building(info[1], additionalInfo);
                    break;
                case "H":
                    location = new Hostel(info[1], additionalInfo);
                    break;
                case "L":
                    location = new LectureTheatre(info[1], info[2]);
                    break;
            }
            if (location!=null) {
                locationList.add(location);
            } else {
                System.out.println("Invalid Location Type");
            }
        }
    }

    public void printLocationList() {
        int i = 1;
        System.out.println("Lists of locations: ");
        for (Location location : locationList) {
            System.out.println(i + ". " + location);
            i++;
        }
    }
}
