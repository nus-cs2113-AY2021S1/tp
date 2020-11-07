package storage;

import event.Event;
import event.Assignment;
import event.Class;
import event.PersonalEvent;
import event.SelfStudy;
import exception.CreatingFileException;
import exception.DataFileNotFoundException;
import exception.EndBeforeStartEventException;
import exception.LoadingException;
import exception.WritingFileException;
import location.Building;
import location.BusStop;
import location.Hostel;
import location.LectureTheatre;
import location.Location;

import location.OnlineLocation;
import location.OutOfNuS;
import locationlist.LocationList;
import parser.Parser;
import usercommunication.UserInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class creates the folder and file path if it's not already created, and
 * prepare the data in the file to be used.
 */
public class Storage {
    public static final String REGEX_IN_FILE = "//";
    public static final String ONLINE = "online";
    private final String[] filePaths;
    //filePaths[0] will be the events data file. filePath[1] will be the user info file

    /**
     * Set the <code>filepath </code> according to the user input.
     *
     * @param filePaths is the paths of the files
     */
    public Storage(String... filePaths) throws CreatingFileException {
        this.filePaths = filePaths;
        createFolderAndFIle(this.filePaths[0]);
        createFolderAndFIle(this.filePaths[1]);
    }

    /**
     * Creates the folder and file if not already crated.
     *
     * @param filePath the String of the relative path
     */
    private static void createFolderAndFIle(String filePath) throws CreatingFileException {
        File dataFile = new File(filePath);
        File directory = dataFile.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            throw new CreatingFileException(filePath);
        }
    }


    /**
     * Save the data of the Event list to the file.
     *
     * @param events the list of Events provided by a variable from a EventList object
     * @throws WritingFileException represents the file is not correctly written
     */
    public void writeFile(ArrayList<Event> events) throws WritingFileException {
        try {
            FileWriter fw = new FileWriter(filePaths[0]);
            for (Event event : events) {
                fw.write(event.fileString());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new WritingFileException();
        }
    }

    /**
     * Save the data of the user info.
     *
     * @param userInfo name and type of the user
     * @throws WritingFileException Represents the exception when the file is not correctly written.
     */
    public void writeUserInfo(UserInfo userInfo) throws WritingFileException {
        try {
            FileWriter fw = new FileWriter(filePaths[1]);
            fw.write(userInfo.fileString());
            fw.close();
        } catch (IOException e) {
            throw new WritingFileException();
        }
    }

    /**
     * Prepares the data in the file as an UserInfo, which is used to construct the userInfo.
     *
     * @return the name and type of the user
     * @throws LoadingException represents the Events is not correctly created
     */
    public UserInfo loadUserInfo() throws LoadingException {
        UserInfo userInfo = null;
        try {
            Scanner s = new Scanner(new File(filePaths[1]));
            if (s.hasNext()) {
                String[] words = s.nextLine().split(REGEX_IN_FILE);
                userInfo = new UserInfo(words[0], words[1]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IndexOutOfBoundsException e) {
            throw new LoadingException();
        }
        return userInfo;
    }

    /**
     * Prepares the data in the file as an ArrayList, which is used to construct the EventList.
     *
     * @return the Events in an ArrayList
     * @throws LoadingException             represents the Events is not correctly created
     * @throws EndBeforeStartEventException Represents the case when the user want to create an Event that ends
     *                                      before it starts.
     */
    public ArrayList<Event> loadEvents(LocationList locations) throws LoadingException, EndBeforeStartEventException {
        ArrayList<Event> events = new ArrayList<>();
        File dataFile = new File(filePaths[0]);
        try {
            Scanner s = new Scanner(dataFile);
            while (s.hasNext()) {
                String[] words = s.nextLine().split(REGEX_IN_FILE);
                switch (words[0]) {
                case "C":
                    try {
                        if (!words[5].equalsIgnoreCase(ONLINE)) {
                            events.add(new Class(words[2], Parser.parseLocation(words[5], locations),
                                    LocalDateTime.parse(words[3]), LocalDateTime.parse(words[4])));
                        } else {
                            if (words.length >= 8) {
                                events.add(new Class(words[2], new OnlineLocation(words[6], words[7]),
                                        LocalDateTime.parse(words[3]), LocalDateTime.parse(words[4])));
                            } else {
                                events.add(new Class(words[2], new OnlineLocation(words[6]),
                                        LocalDateTime.parse(words[3]), LocalDateTime.parse(words[4])));
                            }
                        }
                    } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
                        throw new LoadingException();
                    }
                    if (Integer.parseInt(words[1]) == 1) {
                        events.get(events.size() - 1).markAsDone();
                    }
                    break;
                case "A":
                    try {
                        if (!words[4].equalsIgnoreCase(ONLINE)) {
                            events.add(new Assignment(words[2], Parser.parseLocation(words[4], locations),
                                    LocalDateTime.parse(words[3])));
                        } else {
                            events.add(new Assignment(words[2], new OnlineLocation(words[5]),
                                    LocalDateTime.parse(words[3])));
                        }
                    } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
                        throw new LoadingException();
                    }
                    if (Integer.parseInt(words[1]) == 1) {
                        events.get(events.size() - 1).markAsDone();
                    }
                    break;
                case "P":
                    try {
                        switch (words.length) {
                        case 5:
                            events.add(new PersonalEvent(words[2], Parser.parseLocation(words[4], locations),
                                    LocalDateTime.parse(words[3])));
                            break;
                        case 6:
                            if (!words[4].equalsIgnoreCase(ONLINE)) {
                                events.add(new PersonalEvent(words[2], Parser.parseLocation(words[5], locations),
                                        LocalDateTime.parse(words[3]), LocalDateTime.parse(words[4])));
                            } else {
                                events.add(new PersonalEvent(words[2], new OnlineLocation(words[5]),
                                        LocalDateTime.parse(words[3])));
                            }
                            break;
                        case 7:
                            if (words[4].equalsIgnoreCase(ONLINE)) {
                                events.add(new PersonalEvent(words[2], new OnlineLocation(words[5], words[6]),
                                        LocalDateTime.parse(words[3])));
                            } else if (words[5].equalsIgnoreCase(ONLINE)) {
                                events.add(new PersonalEvent(words[2], new OnlineLocation(words[6]),
                                        LocalDateTime.parse(words[3]), LocalDateTime.parse(words[4])));
                            } else {
                                throw new LoadingException();
                            }
                            break;
                        case 8:
                            events.add(new PersonalEvent(words[2], new OnlineLocation(words[6], words[7]),
                                    LocalDateTime.parse(words[3]), LocalDateTime.parse(words[4])));
                            break;
                        default:
                            throw new LoadingException();
                        }

                    } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
                        throw new LoadingException();
                    }
                    if (Integer.parseInt(words[1]) == 1) {
                        events.get(events.size() - 1).markAsDone();
                    }
                    break;
                case "E":
                    try {
                        switch (words.length) {
                        case 5:
                            events.add(new SelfStudy(words[2], Parser.parseLocation(words[4], locations),
                                    LocalDateTime.parse(words[3])));
                            break;
                        case 6:
                            if (!words[4].equalsIgnoreCase(ONLINE)) {
                                events.add(new SelfStudy(words[2], Parser.parseLocation(words[5], locations),
                                        LocalDateTime.parse(words[3]), LocalDateTime.parse(words[4])));
                            } else {
                                events.add(new SelfStudy(words[2], new OnlineLocation(words[5]),
                                        LocalDateTime.parse(words[3])));
                            }
                            break;
                        case 7:
                            if (words[4].equalsIgnoreCase(ONLINE)) {
                                events.add(new SelfStudy(words[2], new OnlineLocation(words[5], words[6]),
                                        LocalDateTime.parse(words[3])));
                            } else if (words[5].equalsIgnoreCase(ONLINE)) {
                                events.add(new SelfStudy(words[2], new OnlineLocation(words[6]),
                                        LocalDateTime.parse(words[3]), LocalDateTime.parse(words[4])));
                            } else {
                                throw new LoadingException();
                            }
                            break;
                        case 8:
                            events.add(new SelfStudy(words[2], new OnlineLocation(words[6], words[7]),
                                    LocalDateTime.parse(words[3]), LocalDateTime.parse(words[4])));
                            break;
                        default:
                            throw new LoadingException();
                        }

                    } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
                        throw new LoadingException();
                    }
                    if (Integer.parseInt(words[1]) == 1) {
                        events.get(events.size() - 1).markAsDone();
                    }
                    break;
                default:
                    throw new LoadingException();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new LoadingException();
        }

        return events;
    }

    /**
     * Loads data from bus_stop text file to an ArrayList, which is stored in a BusStopList.
     *
     * @param busStopList ArrayList of BusStops in BusStopList
     */
    public void loadBusStopData(ArrayList<BusStop> busStopList) {
        Scanner s = new Scanner(busStopData);

        while (s.hasNext()) {
            String input = s.nextLine();
            String[] split = input.split(":", 2);
            String name = split[0];
            String[] buses = split[1].split(",");
            BusStop stop = new BusStop(name, buses);
            busStopList.add(stop);
        }
    }

    /**
     * Loads data from location text file into an ArrayList, which is stored in a LocationList.
     *
     * @param locationList ArrayList of Locations in LocationList
     */
    public void loadLocationData(ArrayList<Location> locationList) {
        Scanner s = new Scanner(locationData);

        while (s.hasNext()) {
            String input = s.nextLine();
            // info[0] = type, info[1] = name, info[2] = nearest buildings/bus stops
            String[] info = input.split("/");
            String[] additionalInfo = info[2].split(",");
            Location location = null;
            switch (info[0]) {
            case "BLK":
                location = new Building(info[1], additionalInfo);
                break;
            case "H":
                location = new Hostel(info[1], additionalInfo);
                break;
            case "L":
                location = new LectureTheatre(info[1], info[2]);
                break;
            case "OUT":
                location = new OutOfNuS(info[1]);
                break;
            default:
                break;
            }
            if (location != null) {
                locationList.add(location);
            } else {
                System.out.println("Invalid Location Type");
            }
        }
    }

    private String locationData = "BLK/EA/EA\n" +
            "BLK/EA/Information Technology\n" +
            "BLK/E1A/EA\n" +
            "BLK/EW1/EA,Information Technology\n" +
            "BLK/EW1A/EA\n" +
            "BLK/E2/EA\n" +
            "BLK/E3/EA,Raffles Hall\n" +
            "BLK/E3A/EA\n" +
            "BLK/E4/Information Technology\n" +
            "BLK/E4A/Opp YIH,YIH\n" +
            "BLK/E5/Information Technology\n" +
            "BLK/E5A/Raffles Hall\n" +
            "BLK/E6/Opp YIH,YIH\n" +
            "BLK/IT/Information Technology,CLB\n" +
            "H/Raffles Hall/Raffles Hall,NUS Museum\n" +
            "L/LT1/E2\n" +
            "L/LT2/E2\n" +
            "L/LT6/E4\n" +
            "L/LT7/EA\n" +
            "L/LT7A/EA";

    private String busStopData = "EA:B2,C,BTC2\n" +
            "Raffles Hall:B2,C\n" +
            "Information Technology:A2,B1,B2,D1\n" +
            "Opp YIH:A2,B1,B2,D1\n" +
            "NUS Museum:A2,BTC1,BTC2,C,D1,D2\n" +
            "YIH:A1,B1,BTC1,D1\n" +
            "CLB:A1,B1,BTC1,D1";
}
