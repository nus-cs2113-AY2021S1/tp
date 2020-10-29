package seedu.duke.backend;

import seedu.duke.DukeFileFormatException;
import seedu.duke.DukeFileHeaderException;
import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.finance.FinanceList;
import seedu.duke.finance.FinanceLog;
import seedu.duke.hr.Member;
import seedu.duke.hr.MemberList;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileManager {
    // Todo support RFC 4180 CSV standard and handle rogue characters
    private static final String HEADERS_MEMBERS = "Name,Phone,Email,Role";
    private static final String HEADERS_FINANCE = "Name,Value";
    private static final String HEADERS_EVENT = "Name,Date,Time,Done,Participant";
    private static Logger logger = Logger.getLogger("FileLog");
    private String path; // if not the working directory, path should end with a slash

    public FileManager(String path) {
        logger.setLevel(Level.OFF);
        this.path = path;
        try {
            Files.createDirectories(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Saves data from all the different lists to disk.
     * @throws IOException Any one of the files encounters a write error
     */
    public void saveAll() throws IOException {
        logger.log(Level.INFO, "Begin Saving All");
        saveEvent(path + "events.csv");
        saveFinance(path + "finance.csv");
        saveMembers(path + "members.csv");
        logger.log(Level.INFO, "Finished Saving All");
    }

    public int readAll() throws DukeFileFormatException, DukeFileHeaderException, IOException {
        logger.log(Level.INFO, "Begin Loading All");
        int ret = 0;
        try {
            readFinance(path + "finance.csv");
        } catch (FileNotFoundException fe) {
            ret++;
            logger.log(Level.INFO, "Failed to load finance");
        }
        try {
            readMembers(path + "members.csv");
        } catch (FileNotFoundException fe) {
            ret++;
            logger.log(Level.INFO, "Failed to load members");
        }
        try {
            readEvents(path + "events.csv");
        } catch (FileNotFoundException fe) {
            ret++;
            logger.log(Level.INFO, "Failed to load events");
        }
        logger.log(Level.INFO, "Finished loading all");
        return ret;
    }

    /**
     * Saves all the events currently in memory to a csv file.
     * @param fileName The name of the file, including the path if necessary
     * @throws IOException The file cannot be written to
     */
    public void saveEvent(String fileName) throws IOException {
        String writeOutput = HEADERS_EVENT + "\n";
        for (Event e : EventList.events) {
            writeOutput += e.getEventName() + ",";
            writeOutput += e.getEventDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ",";
            writeOutput += e.getEventTime() + ",";
            writeOutput += e.getDone() ? "1," : "0,";
            writeOutput += e.getStringParticipants() + "\n";
        }
        saveFile(fileName, writeOutput);
    }

    /**
     * Saves all the finance logs currently in memory to a csv file.
     * @param fileName The name of the file, including the path if necessary
     * @throws IOException The file cannot be written to
     */
    public void saveFinance(String fileName) throws IOException {
        String writeOutput = HEADERS_FINANCE + "\n";
        for (FinanceLog f : FinanceList.financeLogs) {
            writeOutput += f.getLog() + ",";
            writeOutput += f.getLogVal() + "\n";
        }
        saveFile(fileName, writeOutput);
    }

    /**
     * Saves all the members currently in memory to a csv file.
     * @param fileName The name of the file, including the path if necessary
     * @throws IOException The file cannot be written to
     */
    public void saveMembers(String fileName) throws IOException {

        String writeOutput = HEADERS_MEMBERS + "\n";
        for (Member m : MemberList.members) {
            writeOutput += m.getMemberName() + ",";
            writeOutput += m.getMemberPhone() + ",";
            writeOutput += m.getMemberEmail() + ",";
            writeOutput += m.getMemberRole() + "\n";
        }
        saveFile(fileName, writeOutput);
    }

    /**
     * Generic method for saving string to a specified file.
     * @param fileName The name of the file, including the path if necessary
     * @param data String to write to the file
     * @throws IOException The file cannot be written to
     */
    public static void saveFile(String fileName, String data) throws IOException {

        FileWriter fw = new FileWriter(fileName);
        fw.write(data);
        fw.flush();
        fw.close();
    }

    /**
     * Opens a file and interprets the content as if it's a csv file.
     * @param filename The name of the file, including the path if necessary
     * @param headers if not null, verifies a matching header to the parameter, otherwise aborts
     * @return A HashMap containing the header and all column entries under the header as an ArrayList
     * @throws IOException If the file cannot be found or a read error is encountered
     */
    public static HashMap<String, ArrayList<String>> readFile(String filename, String headers)
            throws IOException, DukeFileHeaderException, DukeFileFormatException {
        BufferedReader csvReader = new BufferedReader(new FileReader(filename));
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        String row;
        boolean header = true;
        //int rowCount = 0;
        String[] headerOrder = null;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            if (header) {
                // Process file header
                if (headers != null && !row.equalsIgnoreCase(headers)) {
                    throw new DukeFileHeaderException();
                }
                for (String s : data) {
                    map.put(s, new ArrayList<String>());
                }
                headerOrder = data;
                header = false;
                continue;
            }
            int i = 0;
            for (String s : data) {
                map.get(headerOrder[i]).add(s);
                i++;
            }
            if (i != headerOrder.length) {
                // Column mismatch!
                throw new DukeFileFormatException();
            }
        }
        csvReader.close();
        return map;
    }

    public static HashMap<String, ArrayList<String>> readFile(String filename)
            throws IOException, DukeFileHeaderException, DukeFileFormatException {
        return readFile(filename, null);
    }

    public static void readFinance(String filename)
            throws IOException, DukeFileHeaderException, DukeFileFormatException {
        HashMap<String, ArrayList<String>> data = readFile(filename);
        // Validate size of any column
        int rows = data.get("Name").size();
        for (int i = 0; i < rows; i++) {
            FinanceLog tmp = new FinanceLog(data.get("Name").get(i), Double.parseDouble(data.get("Value").get(i)));
            FinanceList.financeLogs.add(tmp);
        }
    }

    public static void readEvents(String filename)
            throws IOException, DukeFileHeaderException, DukeFileFormatException {
        HashMap<String, ArrayList<String>> data = readFile(filename);
        // Validate size of any column
        int rows = data.get("Name").size();

        for (int i = 0; i < rows; i++) {
            Event tmp = new Event(data.get("Name").get(i), data.get("Date").get(i), data.get("Time").get(i));
            try {
                tmp.setDone(data.get("Done").get(i).equals("1"));
            } catch (Exception e) {
                logger.log(Level.INFO, "Failed to load done status");
            }
            EventList.events.add(tmp);

            try {
                String participantList = data.get("Participant").get(i);
                String trimmedParticipants = participantList.substring(1, (participantList.length() - 1));
                String[] participants = trimmedParticipants.split(" & ");
                for (int j = 0; j < participants.length; j++) {
                    tmp.setEventParticipants(MemberList.findMemberByName(participants[j]));
                }
            } catch (Exception e) {
                logger.log(Level.INFO, "Failed to load participant data");
            }
        }
    }

    public static void readMembers(String filename)
            throws IOException, DukeFileHeaderException, DukeFileFormatException {
        HashMap<String, ArrayList<String>> data = readFile(filename);
        // Validate size of any column
        int rows = data.get("Name").size();
        for (int i = 0; i < rows; i++) {
            Member tmp = new Member(data.get("Name").get(i),
                    Long.parseLong(data.get("Phone").get(i)),
                    data.get("Email").get(i),
                    data.get("Role").get(i));
            MemberList.members.add(tmp);
        }
    }
}
