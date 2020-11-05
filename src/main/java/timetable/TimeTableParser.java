package timetable;

import studyit.StudyItLog;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class TimeTableParser {
    public static void commandParser(String command, DateList dateList, TimeTableStorage storage) {
        switch (command) {
        case "show schedule":
            System.out.println(Message.printShowSchedule);
            TablePrinter.printTable(dateList.dateList);
            return;
        case "show link":
            System.out.println(Message.printShowLink);
            showLink(dateList);
            return;
        case "show activity":
            showActivities(dateList);
            return;
        default:
            try {
                String[] words = command.split(" ");
                String action = words[0];
                String type = words[1];
                if (action.equals("add")) {
                    switch (type) {
                    case "activity": {
                        Activity activity = addActivity();
                        dateList.addEvent(activity);
                        storage.writeFile(activity);
                        System.out.println(Message.printSuccessfulActivityAddition);
                    }
                    break;
                    case "class": {
                        Lesson lesson = addClass();
                        dateList.addEvent(lesson);
                        storage.writeFile(lesson);
                        System.out.println(Message.printSuccessfulClassAddition);
                    }
                    break;
                    default:
                        System.out.println((Message.printInvalidEvent));
                    }
                } else {
                    System.out.println(Message.printInvalidEvent);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(Message.printInvalidEvent);
                StudyItLog.logger.warning("Invalid timetable command: Invalid event input");
            } catch (InvalidDayOfTheWeekException e) {
                System.out.println("Day of the week input is invalid. Please add the class again.");
                StudyItLog.logger.warning("Invalid timetable command: Invalid day of the week input");
            } catch (ClashScheduleException e) {
                System.out.println("There is a clash in schedule!");
            }
        }
    }

    public static Lesson addClass() throws InvalidDayOfTheWeekException {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter module code: ");
        boolean isInvalid = true;
        String moduleCode = null;
        while (isInvalid) {
            moduleCode = in.nextLine();
            if (moduleCode.length() > 7) {
                System.out.println("The code exceeded the maximum number of characters allowed. Please enter again: ");
            } else {
                isInvalid = false;
            }
        }
        isInvalid = true;
        System.out.println("Is the class online? (yes/no)");
        boolean isOnline = false;
        while (isInvalid) {
            String status = in.nextLine();
            if (status.equals("yes") || status.equals("online")) {
                isOnline = true;
                System.out.println("Please enter zoom link: ");
                isInvalid = false;
            } else if (status.equals("no") || status.equals("offline")) {
                System.out.println("Please enter the venue: ");
                isInvalid = false;
            } else {
                System.out.println("Invalid command command\n Is the class online? (yes/no)");
            }
        }
        final String linkOrVenue = in.nextLine();
        System.out.println("What are the days and time of the lesson?\n(e.g. Monday 5-8pm, Tuesday 6-9pm)");
        final String [] periods = in.nextLine().split(", ");
        System.out.println("How many weeks is the lesson?");
        int repeat = Integer.parseInt(in.nextLine());
        isInvalid = true;
        LocalDateTime startDay = null;
        while (isInvalid) {
            try {
                System.out.println("Which date does the lesson start? (eg. 26/10/2020)");
                startDay = getDate(in.nextLine());
                isInvalid = false;
            } catch (NumberFormatException e) {
                System.out.println("You have entered an invalid date format. Please try again");
            }
        }
        Lesson lesson = new Lesson(moduleCode, linkOrVenue, isOnline, repeat);
        addClassPeriods(periods, repeat, startDay, lesson);
        return lesson;
    }


    public static void addClassPeriods(String[] periods, int repeat, LocalDateTime startDay,
                                Lesson lesson) throws InvalidDayOfTheWeekException {
        int startDayNum = startDay.getDayOfWeek().getValue();
        for (int i = 0; i < repeat; i++) {
            for (String period : periods) {
                String [] dayAndTime = period.split((" "));
                String day = dayAndTime[0].toUpperCase().replace(" ", "");
                String time = dayAndTime[1];
                int dayNum;
                try {
                    dayNum = DayOfWeek.valueOf(day).getValue();
                } catch (IllegalArgumentException e) {
                    throw new InvalidDayOfTheWeekException();
                }
                int startTime = Integer.parseInt(time.split("-")[0]);
                int endTime = Integer.parseInt(time.split("-")[1].replaceAll("[^0-9]", ""));
                if (time.contains("pm")) {
                    startTime += 12;
                    endTime += 12;
                }
                int daysDifference = dayNum - startDayNum;
                if (daysDifference < 0) {
                    daysDifference += 7;
                }
                LocalDateTime startDateTime = startDay.plusDays(daysDifference).plusHours(startTime).plusDays(7 * i);
                LocalDateTime endDateTime = startDateTime.withHour(endTime).plusDays(7 * i);
                Duration duration = new Duration(startDateTime, endDateTime);
                lesson.addPeriod(duration);
            }
        }
    }

    public static Activity addActivity() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the activity: ");
        final String activityName = in.nextLine();
        boolean isInvalid = true;
        System.out.println("Is the activity online? (yes/no)");
        boolean isOnline = false;
        while (isInvalid) {
            String status = in.nextLine();
            if (status.equals("yes") || status.equals("online")) {
                isOnline = true;
                System.out.println("Please enter zoom link: ");
                isInvalid = false;
            } else if (status.equals("no") || status.equals("offline")) {
                System.out.println("Please enter the venue: ");
                isInvalid = false;
            } else {
                System.out.println("Invalid command!\n Is the class online? (yes/no)");
            }
        }
        final String linkOrVenue = in.nextLine();
        System.out.println("Please enter the date of your activity (e.g. 28/10/2020): ");
        LocalDateTime date = getDate(in.nextLine());
        System.out.println("Please enter the time of your activity (e.g. 6-9pm): ");
        String time = in.nextLine();
        int startTime = Integer.parseInt(time.split("-")[0]);
        int endTime = Integer.parseInt(time.split("-")[1].replaceAll("[^0-9]", ""));
        if (time.contains("pm")) {
            startTime += 12;
            endTime += 12;
        }
        LocalDateTime startDateTime = date.plusHours(startTime);
        LocalDateTime endDateTime = date.withHour(endTime);
        Duration duration = new Duration(startDateTime, endDateTime);
        return new Activity(activityName, isOnline, linkOrVenue, duration);
    }

    public static void showLink(DateList dateList) {
        LocalDate todayDate = LocalDateTime.now().toLocalDate();
        int now = LocalDateTime.now().toLocalTime().getHour() * 100;
        for (EventList eventList: dateList.dateList) {
            if (eventList.dateTag.equals(todayDate)) {
                accessEventList(eventList, todayDate, now);
                return;
            }
        }
    }

    public static void accessEventList(EventList eventList, LocalDate todayDate, int now) {
        for (Event event: eventList.events) {
            for (Duration period: event.periods) {
                if ((period.timeSlot.contains(now) || period.timeSlot.contains(now + 100)
                        || period.timeSlot.contains(now + 200))
                        && period.startDateTime.toLocalDate().equals(todayDate)) {
                    System.out.print(event.linkOrVenue + "|" + event.name + "\n");
                }
            }
        }
    }

    public static void showActivities(DateList dateList) {
        for (Event event: dateList.activities) {
            System.out.println(event.name + "|" + event.periods.get(0).startDateTime);
        }
    }

    public static void fileParser(String command, DateList dateList) {
        String[] words = command.split("\\|");
        EventType eventType = EventType.valueOf(words[0]);
        String name = words[1];
        String linkOrVenue = words[2];
        boolean isOnline = Boolean.parseBoolean(words[3]);
        try {
            switch (eventType) {
            case L: {
                int numPerWeek = Integer.parseInt(words[4]);
                int durationNum = Integer.parseInt(words[5]);
                Lesson lesson = new Lesson(name, linkOrVenue, isOnline, numPerWeek);
                for (int i = 0; i < durationNum; i++) {
                    assert words[5 + 2 * i + 1].contains("-") : "this word should be the datetime format";
                    LocalDateTime start = LocalDateTime.parse(words[5 + 2 * i + 1]);
                    assert words[5 + 2 * i + 2].contains("-") : "this word should be the datetime format";
                    LocalDateTime end = LocalDateTime.parse(words[5 + 2 * i + 2]);
                    Duration duration = new Duration(start, end);
                    lesson.addPeriod(duration);
                }
                dateList.addEvent(lesson);
            }
            break;
            case A: {
                LocalDateTime start = LocalDateTime.parse(words[4]);
                LocalDateTime end = LocalDateTime.parse(words[5]);
                Duration duration = new Duration(start, end);
                Activity activity = new Activity(name, isOnline, linkOrVenue, duration);
                dateList.addEvent(activity);
            }
            break;
            default:
            }
        } catch (ClashScheduleException e) {
            System.out.println("There is a clash in schedule");
        }
    }


    public static LocalDateTime getDate(String date)throws ArrayIndexOutOfBoundsException {
        String [] dateArray = date.split("/");
        int day = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        int year = Integer.parseInt(dateArray[2]);
        return LocalDateTime.of(year, month, day, 0, 0);
    }

    public static LocalDateTime getDateTime(String command)throws ArrayIndexOutOfBoundsException {
        String[] dateTime;
        String[] date;
        int fromIndex = command.indexOf("from");
        dateTime = command.substring(fromIndex + 5).split(" ");
        date = dateTime[0].split("/");
        return LocalDateTime.of(Integer.parseInt(date[2]),Integer.parseInt(date[1]), Integer.parseInt(date[0]),
                0,0);
    }

}
