package seedu.command;

import seedu.exception.WhereGotTimeException;
import seedu.task.Event;
import seedu.ui.Ui;
import seedu.user.User;
import seedu.user.UserList;
import seedu.timetable.SortTimetable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AddCommand extends Command {

    public AddCommand(String input) {
        super(input);
    }

    private static Logger logger = Logger.getLogger("LogLogInCommand");

    @Override
    public void execute(UserList users, Ui ui, User nowUser/*, Storage storage*/) throws WhereGotTimeException {
        /*/Lec /day /time /location*/
        setupInputLogger();

        if (nowUser != null) {
            String[] parsedInputs = input.split("/", 5);
            String[] timeInputs = parsedInputs[3].split("-", 2);
            
            String day = parsedInputs[2].toLowerCase().trim();

            assert day.length() == 3 : "Wrong format of day entered";

            logger.log(Level.INFO, "Timetable name successfully added:  " + parsedInputs[1]);
            logger.log(Level.INFO, "Day successfully add:  " + parsedInputs[2]);
            logger.log(Level.INFO, "Time successfully add:  " + parsedInputs[3]);
            logger.log(Level.INFO, "Location successfully add:  " + parsedInputs[4] + "\n");
            try {
                for (int i = 0; i < users.getTotalUserCount(); i++) {
                    if ((users.getUser(i + 1).getName().equals(nowUser.getName()))) {
                        Event newEvent = new Event(parsedInputs[1].trim(), parsedInputs[4].trim(),
                                timeInputs[0].trim(), timeInputs[1].trim());
                        ArrayList<Event> timetable = users.getUser(i + 1).getTimetable().getTimetable(day);
                        timetable.add(newEvent);
                        
                        try {
                            
                            int timeStart = Integer.parseInt(timeInputs[0].trim());
                            int timeEnd = Integer.parseInt(timeInputs[1].trim());
                            int timeStartHour = Integer.parseInt(timeInputs[0].trim().substring(0, 2));
                            int timeStartMin = Integer.parseInt(timeInputs[0].trim().substring(2));
                            int timeEndHour = Integer.parseInt(timeInputs[1].trim().substring(0, 2));
                            int timeEndMin = Integer.parseInt(timeInputs[1].trim().substring(2));
                            
                            if (timeStartHour < 0 || timeStartHour > 23) {
                                timetable.remove(newEvent);
                                throw new WhereGotTimeException("Invalid Hour! It should be within "
                                        + "24-hour format.");
                            } else if (timeStartMin < 0 || timeStartMin > 59) {
                                timetable.remove(newEvent);
                                throw new WhereGotTimeException("Invalid minute! It should be within "
                                        + "24-hour format.");
                            }

                            if (timeEndHour < 0 || timeEndHour > 23) {
                                timetable.remove(newEvent);
                                throw new WhereGotTimeException("Invalid Hour! It should be within "
                                        + "24-hour format.");
                            } else if (timeEndMin < 0 || timeEndMin > 59) {
                                timetable.remove(newEvent);
                                throw new WhereGotTimeException("Invalid minute! It should be within "
                                        + "24-hour format.");
                            }
                            
                            if (timeStart == timeEnd) {
                                timetable.remove(newEvent);
                                throw new WhereGotTimeException("Start time cannot be the same as End time");
                            } else if (timeStart > timeEnd) {
                                timetable.remove(newEvent);
                                throw new WhereGotTimeException("Start time cannot be later than End time");
                            }
                            if (timeStart > 2300 || timeEnd > 2359) {
                                timetable.remove(newEvent);
                                throw new WhereGotTimeException("Start and End time must be "
                                        + "in 24 hour format");
                            }

                            if ((timeStartMin % 60) != 0 || (timeEndMin % 60) != 0) {
                                timetable.remove(newEvent);
                                throw new WhereGotTimeException("The timings should be in 1 hour block! "
                                        + "e.g. 0900, 1200, 1500, etc");
                            }

                            if (parsedInputs[4].contains("/")) {
                                timetable.remove(newEvent);
                                throw new WhereGotTimeException("\nInvalid location Format \n"
                                        + "Correct format is add /name /day /timeStart-timeEnd /location\n"
                                        + "E.g: add /CS2113 Lec /mon /2000-2100 /LT1");
                            }
                        } catch (NumberFormatException n) {
                            timetable.remove(newEvent);
                            throw new WhereGotTimeException("\nInvalid Time Format \n"
                                    + "Correct format is add /name /day /timeStart-timeEnd /location\n"
                                    + "E.g: add /CS2113 Lec /mon /2000-2100 /LT1");
                        } catch (IndexOutOfBoundsException e) {
                            throw new WhereGotTimeException("An add command needs to be in a"
                                    + "add /name /day /time /location' format!");
                        }
                        switch (day) {
                        case "mon":
                            ArrayList<Event> mon = users.getUser(i + 1).getTimetable().monTimetable;
                            for (int j = 0; j < mon.size() - 1; j++) {
                                int timeStartInt = Integer.parseInt(mon.get(j).getTimeStart());
                                int timeStartNextInt = Integer.parseInt(mon.get(j + 1).getTimeStart());
                                int timeEndInt = Integer.parseInt(mon.get(j).getTimeEnd());
                                int timeEndNextInt = Integer.parseInt(mon.get(j + 1).getTimeEnd());
                                if (timeStartInt == timeStartNextInt || timeEndInt == timeEndNextInt
                                        || (timeStartInt + 100 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeStartInt + 200 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeStartInt + 300 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeStartInt + 400 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeStartInt + 500 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 100 && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 200 && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 300 && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 400 && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 500 && timeEndInt > timeStartNextInt)) {
                                    mon.remove(j + 1);
                                    throw new WhereGotTimeException("Duplicate Timetable Detected! Please re-enter");
                                }
                            }
                            break;
                        case "tue":

                            ArrayList<Event> tue = users.getUser(i + 1).getTimetable().tueTimetable;
                            for (int j = 0; j < tue.size() - 1; j++) {
                                int timeStartInt = Integer.parseInt(tue.get(j).getTimeStart());
                                int timeStartNextInt = Integer.parseInt(tue.get(j + 1).getTimeStart());
                                int timeEndInt = Integer.parseInt(tue.get(j).getTimeEnd());
                                int timeEndNextInt = Integer.parseInt(tue.get(j + 1).getTimeEnd());
                                if (timeStartInt == timeStartNextInt || timeEndInt == timeEndNextInt
                                        || (timeStartInt + 100 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeStartInt + 200 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeStartInt + 300 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeStartInt + 400 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeStartInt + 500 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 100 && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 200 && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 300 && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 400 && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 500 && timeEndInt > timeStartNextInt)) {
                                    tue.remove(j + 1);
                                    throw new WhereGotTimeException("Duplicate Timetable Detected! Please re-enter");
                                }
                            }
                            break;
                        case "wed":
                            ArrayList<Event> wed = users.getUser(i + 1).getTimetable().wedTimetable;
                            for (int j = 0; j < wed.size() - 1; j++) {
                                int timeStartInt = Integer.parseInt(wed.get(j).getTimeStart());
                                int timeStartNextInt = Integer.parseInt(wed.get(j + 1).getTimeStart());
                                int timeEndInt = Integer.parseInt(wed.get(j).getTimeEnd());
                                int timeEndNextInt = Integer.parseInt(wed.get(j + 1).getTimeEnd());
                                if (timeStartInt == timeStartNextInt || timeEndInt == timeEndNextInt
                                        || (timeStartInt + 100 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeStartInt + 200 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeStartInt + 300 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeStartInt + 400 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeStartInt + 500 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 100 && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 200 && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 300 && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 400 && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 500 && timeEndInt > timeStartNextInt)) {
                                    wed.remove(j + 1);
                                    throw new WhereGotTimeException("Duplicate Timetable Detected! Please re-enter");
                                }
                            }
                            break;
                        case "thu":
                            ArrayList<Event> thu = users.getUser(i + 1).getTimetable().thuTimetable;
                            for (int j = 0; j < thu.size() - 1; j++) {
                                int timeStartInt = Integer.parseInt(thu.get(j).getTimeStart());
                                int timeStartNextInt = Integer.parseInt(thu.get(j + 1).getTimeStart());
                                int timeEndInt = Integer.parseInt(thu.get(j).getTimeEnd());
                                int timeEndNextInt = Integer.parseInt(thu.get(j + 1).getTimeEnd());
                                if (timeStartInt == timeStartNextInt || timeEndInt == timeEndNextInt
                                        || (timeStartInt + 100 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeStartInt + 200 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeStartInt + 300 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeStartInt + 400 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeStartInt + 500 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 100 && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 200 && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 300 && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 400 && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 500 && timeEndInt > timeStartNextInt)) {
                                    thu.remove(j + 1);
                                    throw new WhereGotTimeException("Duplicate Timetable Detected! Please re-enter");
                                }
                            }
                            break;
                        case "fri":
                            ArrayList<Event> fri = users.getUser(i + 1).getTimetable().friTimetable;
                            for (int j = 0; j < fri.size() - 1; j++) {
                                int timeStartInt = Integer.parseInt(fri.get(j).getTimeStart());
                                int timeStartNextInt = Integer.parseInt(fri.get(j + 1).getTimeStart());
                                int timeEndInt = Integer.parseInt(fri.get(j).getTimeEnd());
                                int timeEndNextInt = Integer.parseInt(fri.get(j + 1).getTimeEnd());
                                if (timeStartInt == timeStartNextInt || timeEndInt == timeEndNextInt
                                        || (timeStartInt + 100 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeStartInt + 200 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeStartInt + 300 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeStartInt + 400 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeStartInt + 500 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 100 && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 200 && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 300 && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 400 && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 500 && timeEndInt > timeStartNextInt)) {
                                    fri.remove(j + 1);
                                    throw new WhereGotTimeException("Duplicate Timetable Detected! Please re-enter");
                                }
                            }
                            break;
                        case "sat":
                            ArrayList<Event> sat = users.getUser(i + 1).getTimetable().satTimetable;
                            for (int j = 0; j < sat.size() - 1; j++) {
                                int timeStartInt = Integer.parseInt(sat.get(j).getTimeStart());
                                int timeStartNextInt = Integer.parseInt(sat.get(j + 1).getTimeStart());
                                int timeEndInt = Integer.parseInt(sat.get(j).getTimeEnd());
                                int timeEndNextInt = Integer.parseInt(sat.get(j + 1).getTimeEnd());
                                if (timeStartInt == timeStartNextInt || timeEndInt == timeEndNextInt
                                        || (timeStartInt + 100 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeStartInt + 200 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeStartInt + 300 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeStartInt + 400 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeStartInt + 500 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 100 && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 200 && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 300 && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 400 && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 500 && timeEndInt > timeStartNextInt)) {
                                    sat.remove(j + 1);
                                    throw new WhereGotTimeException("Duplicate Timetable Detected! Please re-enter");
                                }
                            }
                            break;
                        case "sun":
                            ArrayList<Event> sun = users.getUser(i + 1).getTimetable().sunTimetable;
                            for (int j = 0; j < sun.size() - 1; j++) {
                                int timeStartInt = Integer.parseInt(sun.get(j).getTimeStart());
                                int timeStartNextInt = Integer.parseInt(sun.get(j + 1).getTimeStart());
                                int timeEndInt = Integer.parseInt(sun.get(j).getTimeEnd());
                                int timeEndNextInt = Integer.parseInt(sun.get(j + 1).getTimeEnd());
                                if (timeStartInt == timeStartNextInt || timeEndInt == timeEndNextInt
                                        || (timeStartInt + 100 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeStartInt + 200 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeStartInt + 300 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeStartInt + 400 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeStartInt + 500 == timeStartNextInt && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 100 && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 200 && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 300 && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 400 && timeEndInt > timeStartNextInt)
                                        || (timeEndNextInt == timeStartInt + 500 && timeEndInt > timeStartNextInt)) {
                                    sun.remove(j + 1);
                                    throw new WhereGotTimeException("Duplicate Timetable Detected! Please re-enter");
                                }
                            }
                            break;
                        default:
                            break;
                        }
                        ui.printEvent(newEvent, day);
                        SortTimetable.sortTimetable(users, nowUser, day);

                        logger.log(Level.INFO, day + " timetable successfully add:  " + timetable + "\n");
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                throw new WhereGotTimeException("An add command needs to be in "
                        + "a 'add /name /day /time /location' format!");
            }
        } else {
            logger.log(Level.WARNING, "Not logged in" + "\n");
            throw new WhereGotTimeException("Sorry! You are not Logged in to any account :-(");
        }
    }

    private void setupInputLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        try {
            FileHandler fh = new FileHandler("AddCommand.log", true);
            fh.setLevel(Level.INFO);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "File logging not working");
        }
    }
}