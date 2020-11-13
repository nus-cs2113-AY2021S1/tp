package seedu.duke.storage;

import seedu.duke.EventLogger;
import seedu.duke.event.Event;
import seedu.duke.event.Personal;
import seedu.duke.event.Timetable;
import seedu.duke.event.Zoom;
import seedu.duke.exception.InvalidStatusException;
import seedu.duke.exception.InvalidTimeUnitException;
import seedu.duke.parser.DateTimeParser;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Logger;

public class StorageParser {

    private static Logger logger = EventLogger.getEventLogger();

    /**
     * Function helps to convert any event to a recordable string format to be written to txt file.
     *
     * @param activity Event object to be convert to a string representation
     * @param type What kind of event the activity is
     * @return String representation to be recorded into the txt files
     */
    public static String eventToString(Event activity, String type) {

        ArrayList<String> words = new ArrayList<>();

        switch (type) {

        case "Personal":
            personalToArguments((Personal) activity, words);
            break;
        case "Zoom":
            zoomToArguments((Zoom) activity, words);
            break;
        case "Timetable":
            timetableToArguments((Timetable) activity, words);
            break;
        default:
            System.out.println("Error, wrong data type provided");
            break;
        }

        return String.join(" | ", words);

    }

    /**
     * Function used for recording down statuses of repeated events.
     *
     * @param statuses Location of where the list of statuses will be recorded
     * @param activities The list of repeated activities
     * @param repeatCount number of repetitions present
     */
    public static void handleRepeatSave(ArrayList<String> statuses, ArrayList<Event> activities, int repeatCount) {

        for (int i = 0; i < repeatCount; i++) {
            Boolean repeatedActivityStatus = activities.get(i).getStatus().equals("O");
            statuses.add(Boolean.toString(repeatedActivityStatus));
        }
    }

    /**
     * Converts a personal event properties into strings.
     *
     * @param activity Personal event to be converted
     * @param words Array List of strings that the arguments will be recorded to
     */
    public static void personalToArguments(Personal activity, ArrayList<String> words) {

        ArrayList<String> statuses = new ArrayList<>();


        //obtain the dates and time of the event
        String date = "0";
        String time = "0";
        if (activity.getDate() != null) {
            date = activity.getDate().toString();
        }
        if (activity.getTime() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
            time = activity.getTime().format(formatter);
        }

        Boolean activityStatus = activity.getStatus().equals("O");
        statuses.add(Boolean.toString(activityStatus));
        String repeatUnit = "0";
        String repeatNumber = "0";
        if (activity.getRepeatCount() != 0) { //it is a repeated activity
            repeatUnit = activity.getRepeatType().toUpperCase();
            repeatNumber = Integer.toString(activity.getRepeatCount());
            ArrayList<Event> activitiesRepeated = activity.getRepeatEventList();

            handleRepeatSave(statuses, activitiesRepeated, activity.getRepeatCount());

        }

        //obtain the name of the event
        String name = activity.getDescription();
        words.add(name);
        words.add(date);
        words.add(time);
        words.add(repeatUnit);
        words.add(repeatNumber);
        String noteString = notesListToString(activity.getNotes());
        words.add(noteString);
        for (int i = 0; i < statuses.size(); i++) {
            boolean isDone = Boolean.parseBoolean(statuses.get(i));
            if (isDone) {
                words.add("T");
            } else {
                words.add("F");
            }
        }

    }

    /**
     * Converts a Zoom event properties into strings.
     *
     * @param activity Personal event to be converted
     * @param words Array List of strings that the arguments will be recorded to
     */
    public static void zoomToArguments(Zoom activity, ArrayList<String> words) {

        ArrayList<String> statuses = new ArrayList<>();


        //obtain the dates and time of the event
        String date = "0";
        String time = "0";
        if (activity.getDate() != null) {
            date = activity.getDate().toString();
        }
        if (activity.getTime() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
            time = activity.getTime().format(formatter);
        }

        Boolean activityStatus = activity.getStatus().equals("O");
        statuses.add(Boolean.toString(activityStatus));
        String repeatUnit = "0";
        String repeatNumber = "0";
        if (activity.getRepeatCount() != 0) { //it is a repeated activity
            repeatUnit = activity.getRepeatType().toUpperCase();
            repeatNumber = Integer.toString(activity.getRepeatCount());
            ArrayList<Event> activitiesRepeated = activity.getRepeatEventList();

            handleRepeatSave(statuses, activitiesRepeated, activity.getRepeatCount());

        }

        String url = activity.getZoomLink();
        //obtain the name of the event
        String name = activity.getDescription();
        words.add(name);
        words.add(date);
        words.add(time);
        words.add(repeatUnit);
        words.add(repeatNumber);
        words.add(url);
        String noteString = notesListToString(activity.getNotes());
        words.add(noteString);
        for (int i = 0; i < statuses.size(); i++) {
            boolean isDone = Boolean.parseBoolean(statuses.get(i));
            if (isDone) {
                words.add("T");
            } else {
                words.add("F");
            }
        }

    }

    /**
     * Converts a timetable event properties into strings.
     *
     * @param activity timetable event to be converted
     * @param words Array List of strings that the arguments will be recorded to
     */
    public static void timetableToArguments(Timetable activity, ArrayList<String> words) {

        ArrayList<String> statuses = new ArrayList<>();


        //obtain the dates and time of the event
        String date = "0";
        String time = "0";
        if (activity.getDate() != null) {
            date = activity.getDate().toString();
        }
        if (activity.getTime() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
            time = activity.getTime().format(formatter);
        }

        Boolean activityStatus = activity.getStatus().equals("O");
        statuses.add(Boolean.toString(activityStatus));
        String repeatUnit = "0";
        String repeatNumber = "0";
        if (activity.getRepeatCount() != 0) { //it is a repeated activity
            repeatUnit = activity.getRepeatType().toUpperCase();
            repeatNumber = Integer.toString(activity.getRepeatCount());
            ArrayList<Event> activitiesRepeated = activity.getRepeatEventList();

            handleRepeatSave(statuses, activitiesRepeated, activity.getRepeatCount());

        }

        String location = activity.getLocation();
        if (location == null) {
            location = "";
        }
        //obtain the name of the event
        String name = activity.getDescription();
        words.add(name);
        words.add(date);
        words.add(time);
        words.add(repeatUnit);
        words.add(repeatNumber);
        words.add(location);
        String noteString = notesListToString(activity.getNotes());
        words.add(noteString);

        for (int i = 0; i < statuses.size(); i++) {
            boolean isDone = Boolean.parseBoolean(statuses.get(i));
            if (isDone) {
                words.add("T");
            } else {
                words.add("F");
            }
        }

    }

    /**
     * Converts a string into the proper event.
     *
     * @param line text from the txt file
     * @param type what kind of event we are reconstructing
     * @param ui UserInterface for printing things such as error messages
     * @return Event object constructed by the txt file
     */
    public static Event stringToEvent(String line, String type, Ui ui) {
        String[] words = line.split("\\|");
        String[] statuses;
        String[] info;
        String[] notes;

        try {
            for (int i = 0; i < words.length; i++) {
                words[i] = words[i].trim();
            }
            switch (type) {
            case "Personal":
                info = Arrays.copyOfRange(words, 0, 5);
                notes = Arrays.copyOfRange(words, 5, 6);
                statuses = Arrays.copyOfRange(words, 6, words.length);
                return makePersonal(info, statuses, notes);
            case "Zoom":
                info = Arrays.copyOfRange(words, 0, 6);
                notes = Arrays.copyOfRange(words, 6, 7);
                statuses = Arrays.copyOfRange(words, 7, words.length);
                return makeZoom(info, statuses, notes);
            case "Timetable":
                info = Arrays.copyOfRange(words, 0, 6);
                notes = Arrays.copyOfRange(words, 6, 7);
                statuses = Arrays.copyOfRange(words, 7, words.length);
                return makeTimetable(info, statuses, notes);
            default:
                return null;
            }
        } catch (Exception e) {
            logger.warning("Event not loaded due to data corruption.");
        }
        return null;
    }

    /**
     * Creates a personal event from a string of arguments.
     *
     * @param info Array of strings containing all the event information in text form
     * @param statuses Array of strings containing statuses of repeated event if any
     * @param notes Array of strings containing the notes associated with the event
     * @return Personal event constructed from the arguments provided
     * @throws Exception if there is data corruption in the files
     */
    private static Personal makePersonal(String[] info, String[] statuses, String[] notes) throws Exception {
        //0 is name, 1 is date, 2 is time, 3 is repeat unit, 4 is repeat number
        Personal p = new Personal(info[0]);
        if (info[1].equals("0")) {
            //no date, event can be returned as is
            setDone(p, statuses[0]);
            notesSetter(p, notes[0]);
            return p;
        } else if (info[2].equals("0")) {
            //no time, but got date
            LocalDate date = DateTimeParser.dateParser(info[1]);
            LocalTime time = null;
            p.setDate(date);
        } else {
            //has both date and time
            LocalDate date = DateTimeParser.dateParser(info[1]);
            LocalTime time = DateTimeParser.timeParser(info[2]);
            p.setDate(date);
            p.setTime(time);
        }
        setDone(p, statuses[0]);
        repeatSetter(p, statuses, info[3], info[4]);
        notesSetter(p, notes[0]);
        return p;


    }

    /**
     * Creates a zoom event from a string of arguments.
     *
     * @param info Array of strings containing all the event information in text form
     * @param statuses Array of strings containing statuses of repeated event if any
     * @param notes Array of strings containing the notes associated with the event
     * @return zoom event constructed from the arguments provided
     * @throws Exception if there is data corruption in the files
     */
    private static Zoom makeZoom(String[] info, String[] statuses, String[] notes) throws Exception {
        //0 is name, 1 is date, 2 is time, 3 is repeat unit, 4 is repeat number, 5 is zoom link
        Zoom z = new Zoom(info[0], info[5]);
        if (info[1].equals("0")) {
            //no date, event can be returned as is
            setDone(z, statuses[0]);
            notesSetter(z, notes[0]);
            return z;
        } else if (info[2].equals("0")) {
            //no time, but got date
            LocalDate date = DateTimeParser.dateParser(info[1]);
            LocalTime time = null;
            z.setDate(date);
        } else {
            //has both date and time
            LocalDate date = DateTimeParser.dateParser(info[1]);
            LocalTime time = DateTimeParser.timeParser(info[2]);
            z.setDate(date);
            z.setTime(time);
        }
        setDone(z, statuses[0]);
        repeatSetter(z, statuses, info[3], info[4]);
        notesSetter(z, notes[0]);
        return z;
    }

    /**
     * Creates a personal event from a string of arguments.
     *
     * @param info Array of strings containing all the event information in text form
     * @param statuses Array of strings containing statuses of repeated event if any
     * @param notes Array of strings containing the notes associated with the event
     * @return Personal event constructed from the arguments provided
     * @throws Exception if there is data corruption in the files
     */
    private static Timetable makeTimetable(String[] info, String[] statuses, String[] notes) throws Exception {
        //0 is name, 1 is date, 2 is time, 3 is repeat unit, 4 is repeat number, 5 is location
        LocalDate date = DateTimeParser.dateParser(info[1]);
        LocalTime time = DateTimeParser.timeParser(info[2]);
        Timetable t = new Timetable(info[0], date, time);
        if (!info[5].equals("")) { //location provided
            t.setLocation(info[5]);
        }
        setDone(t, statuses[0]);
        repeatSetter(t, statuses, info[3], info[4]);
        notesSetter(t, notes[0]);
        return t;
    }

    /**
     * Set the done status of the event from the string argument.
     *
     * @param e The event for the status to be set
     * @param doneStatus String containing the status. "T" for true, "F" for false
     * @throws InvalidStatusException if the status is not a "T" or "F"
     */
    private static void setDone(Event e, String doneStatus) throws InvalidStatusException {
        boolean isDone = doneStatus.equals("T");
        boolean isNotDone = doneStatus.equals("F");
        if (isDone) {
            e.markAsDone();
        } else if (isNotDone) {
            e.markAsUndone();
        } else {
            throw new InvalidStatusException("Status needs to be true or false");
        }

    }

    /**
     * Setup of of repeated events.
     *
     * @param activity the event object where the repeated event is to be set
     * @param statuses status information of all repeated events to be recorded
     * @param timeUnit The time increment used to make the repeated event
     * @param repeatNumber How many times the event repeated
     * @throws Exception if there are file corruption errors
     */
    private static void repeatSetter(Event activity, String[] statuses, String timeUnit, String repeatNumber)
            throws Exception {

        LocalDate startDate = activity.getDate();
        LocalTime startTime = activity.getTime();
        int count = Integer.parseInt(repeatNumber);
        if (count == 0) {
            return;
        }
        ArrayList<Event> repeatEventList = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            LocalDate repeatDate;
            switch (timeUnit) {
            case "MONTHLY":
                repeatDate = startDate.plusMonths(i);
                break;
            case "WEEKLY":
                repeatDate = startDate.plusWeeks(i);
                break;
            case "DAILY":
                repeatDate = startDate.plusDays(i);
                break;
            default:
                throw new InvalidTimeUnitException(timeUnit);
            }
            activity.setRepeatType(timeUnit);
            Event repeatEvent;
            repeatEvent = activity.clone();
            repeatEvent.setDate(repeatDate);
            if (statuses[i].equals("T")) {
                repeatEvent.markAsDone();
            } else {
                repeatEvent.markAsUndone();
            }
            repeatEventList.add(repeatEvent);
        }
        activity.setRepeatEventList(repeatEventList);




    }

    /**
     * Set the notes for event.
     * @param e event.
     * @param notes notes for event.
     */
    private static void notesSetter(Event e, String notes) {
        String noteString = notes;
        noteString = noteString.trim();
        ArrayList<String> noteList = new ArrayList<>();
        if (noteString.equals("")) {
            e.setNotes(noteList);
            assert noteList.size() == 0 : "There were notes provided";
        } else {
            //do not discard trailing empty strings
            String[] noteArr = noteString.split("`", -1);
            Collections.addAll(noteList, noteArr);
        }


        e.setNotes(noteList);
    }

    /**
     * Convert ArrayList to String for notes.
     * @param notes list of notes.
     * @return notes in string format.
     */
    private static String notesListToString(ArrayList<String> notes) {
        StringBuffer notesBuffer = new StringBuffer();
        String noteString = "";
        if (!notes.isEmpty() && !(notes.size() == 1 && notes.get(0).equals(""))) {
            for (String s : notes) {
                if (s != "") {
                    notesBuffer.append(s);
                    notesBuffer.append("`");
                }

            }
            noteString = notesBuffer.toString();
        }

        return noteString;
    }
}