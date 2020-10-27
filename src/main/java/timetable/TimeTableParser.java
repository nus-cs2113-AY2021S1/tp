package timetable;

import studyit.StudyItLog;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class TimeTableParser {
    public static void commandParser(String command, DateList dateList, TimeTableStorage storage) {
        if (command.equals("show schedule")) {
            System.out.println(Message.printShowSchedule);
            TablePrinter.printTable(dateList.dateList);
            return;
        } else if (command.equals("show link")) {
            System.out.println(Message.printShowLink);
            showLink(dateList);
            return;
        }
        try {
            String[] words = command.split(" ");
            String action = words[0];
            String type = words[1];
            if (action.equals("add")) {
                switch (type) {
                case "activity":
                    break;
                case "class": {
                    Lesson lesson = addClass();
                    dateList.addLesson(lesson);
                    storage.writeFile(lesson);
                    System.out.println(Message.printSuccessfulClassAddition);
                }
                break;
                default:
                    System.out.println((Message.printInvalidEvent));
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(Message.printInvalidEvent);
            StudyItLog.logger.warning("Invalid timetable command: Invalid event input");
        } catch (InvalidDayOfTheWeekException e) {
            System.out.println("Day of the week input is invalid. Please add the class again");
            StudyItLog.logger.warning("Invalid timetable command: Invalid day of the week input");
        }
    }

    public static Lesson addClass() throws InvalidDayOfTheWeekException {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter module name: ");
        final String moduleCode = in.nextLine();
        System.out.println("Is the class online? ");
        boolean isOnline = false;
        boolean isInvalid = true;
        while (isInvalid) {
            String status = in.nextLine();
            if (status.equals("yes") || status.equals("online")) {
                isOnline = true;
                System.out.println("Please enter zoom link");
                isInvalid = false;
            } else if (status.equals("no") || status.equals("offline")) {
                System.out.println("Please enter the venue");
                isInvalid = false;
            } else {
                System.out.println("Invalid command command\n Is the class online? ");
            }
        }
        String linkOrVenue = in.nextLine();
        System.out.println("What are the days and time of the lesson (eg. Monday 5-8pm, Tuesday 6-9pm)");
        String [] periods = in.nextLine().split(", ");
        System.out.println("How many weeks is the lesson?");
        int repeat = Integer.parseInt(in.nextLine());
        System.out.println("Which date does the lesson start? (eg. 26/10/2020)");
        LocalDateTime startDay = getDateTime(in.nextLine());
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
                    System.out.println(event.linkOrVenue);
                }
            }
        }
    }


    public static void fileParser(String command, DateList dateList) {
        String[] words = command.split("\\|");
        EventType eventType = EventType.valueOf(words[0]);
        String name = words[1];
        String linkOrVenue = words[2];
        boolean isOnline = Boolean.parseBoolean(words[3]);
        int numPerWeek = Integer.parseInt(words[4]);
        switch (eventType) {
        case L: {
            int durationNum = Integer.parseInt(words[5]);
            Lesson lesson = new Lesson(name, linkOrVenue, isOnline, numPerWeek);
            for (int i = 0; i < durationNum; i++) {
                assert words[ 5 + 2 * i + 1].contains("-") : "this word should be the datetime format";
                LocalDateTime start = LocalDateTime.parse(words[5 + 2 * i + 1]);
                assert words[5 + 2 * i + 2].contains("-") : "this word should be the datetime format";
                LocalDateTime end = LocalDateTime.parse(words[5 + 2 * i + 2]);
                Duration duration = new Duration(start, end);
                lesson.addPeriod(duration);
            }
            dateList.addLesson(lesson);
        }
            break;
        case A: {
            LocalDateTime start = LocalDateTime.parse(words[5]);
            LocalDateTime end = LocalDateTime.parse(words[6]);
            Duration duration = new Duration(start, end);
            Activity activity = new Activity(name, isOnline, linkOrVenue, duration);
            dateList.addLesson(activity);
        }
            break;
        default:
        }
    }


    public static LocalDateTime getDateTime(String date)throws ArrayIndexOutOfBoundsException {
        String [] dateArray = date.split("/");
        int day = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        int year = Integer.parseInt(dateArray[2]);
        return LocalDateTime.of(year, month, day, 0, 0);
    }
}
