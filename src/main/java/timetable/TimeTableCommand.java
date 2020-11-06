package timetable;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class TimeTableCommand {
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
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
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
        isInvalid = true;
        LocalDateTime date = null;
        while (isInvalid) {
            System.out.println("Please enter the date of your activity (e.g. 28/10/2020): ");
            try {
                date = getDate(in.nextLine());
                isInvalid = false;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("You have entered an invalid date format. Please try again");
            }
        }
        isInvalid = true;
        String start;
        String end;
        int startTime = 0;
        int endTime = 0;
        while (isInvalid) {
            System.out.println("Please enter the time of your activity (e.g. 6-9pm): ");
            String time = in.nextLine();
            try {
                start = time.split("-")[0];
                end = time.split("-")[1];
                startTime = Integer.parseInt(start.replaceAll("[^0-9]", ""));
                endTime = Integer.parseInt(end.replaceAll("[^0-9]", ""));
                if (start.contains("am") || start.contains("pm")) {
                    if (start.contains("pm") && startTime != 12) {
                        startTime += 12;
                    } else if (start.contains("am") && startTime == 12) {
                        startTime = 0;
                    }
                    if (end.contains("pm") && startTime != 12) {
                        endTime += 12;
                    } else if (end.contains("am") && startTime == 12) {
                        startTime = 0;
                    }
                } else if (end.contains("pm")) {
                    endTime += 12;
                    if (startTime != 12) {
                        startTime += 12;
                    }
                }
                if (startTime < endTime) {
                    isInvalid = false;
                }
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("You have entered an invalid time. Please try again");
            }
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

    public static LocalDateTime getDate(String date) {
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
