package timetable;

import exceptions.InvalidDayOfTheWeekException;
import exceptions.InvalidTimeException;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TimeTableCommand {
    public static Lesson addClass() throws InvalidDayOfTheWeekException, InvalidTimeException {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter module code: ");
        boolean isInvalid = true;
        String moduleCode = null;
        while (isInvalid) {
            moduleCode = in.nextLine();
            if (moduleCode.length() > 8) {
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
        isInvalid=true;
        int repeat = 0;
        while (isInvalid) {
            System.out.println("How many weeks is the lesson?");
            repeat = Integer.parseInt(in.nextLine());
            if (repeat < 54) {
                isInvalid = false;
            } else {
                System.out.println("Your lesson should not last for more than a year "
                        + "Please enter a number less than 53");
            }
        }
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
                                       Lesson lesson) throws InvalidDayOfTheWeekException, InvalidTimeException {
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
                String start;
                String end;
                int startTime;
                int endTime;
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
                    } else if (end.contains("am") && startTime == 12) {
                        startTime = 0;
                    }
                    if (startTime >= endTime) {
                        throw new InvalidTimeException();
                    }
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    throw new InvalidTimeException();
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
        isInvalid = true;
        String linkOrVenue = null;
        while (isInvalid) {
            linkOrVenue = in.nextLine();
            if (isOnline && (!linkOrVenue.contains(".") || linkOrVenue.contains(" "))) {
                System.out.println("The link you have entered is invalid. Please enter again");
            } else {
                isInvalid = false;
            }
        }
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
        int startTime;
        int endTime;
        LocalDateTime startDateTime = null;
        LocalDateTime endDateTime = null;
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
                    if (end.contains("pm") && endTime != 12) {
                        endTime += 12;
                    } else if (end.contains("am") && startTime == 12) {
                        startTime = 0;
                    }
                } else if (end.contains("pm")) {
                    endTime += 12;
                    if (startTime != 12) {
                        startTime += 12;
                    }
                } else if (end.contains("am") && startTime == 12) {
                    startTime = 0;
                }
                if (startTime < endTime) {
                    isInvalid = false;
                    startDateTime = date.plusHours(startTime);
                    endDateTime = date.withHour(endTime);
                } else {
                    System.out.println("You have entered an ending time that is not later than the starting time."
                            + " Please try again ");
                }
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException | DateTimeException e) {
                System.out.println("You have entered an invalid time. Please try again");
                isInvalid = true;
            }
        }

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
        boolean exist = false;
        for (Event event: eventList.events) {
            for (Duration period: event.periods) {
                if ((period.timeSlot.contains(now) || period.timeSlot.contains(now + 100)
                        || period.timeSlot.contains(now + 200))
                        && period.startDateTime.toLocalDate().equals(todayDate)) {
                    System.out.print(event.linkOrVenue + " | " + event.name + "\n");
                    exist = true;
                }
            }
        }
        if (!exist) {
            System.out.println("You do not have anything scheduled in the next two hours");
        }
    }

    public static void showActivities(DateList dateList) {
        int index = 0;
        for (Activity activity: dateList.activities) {
            index++;
            System.out.println(index + ". " + activity.name + " "
                    + activity.periods.get(0).startDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                    + (activity.isOnline?" online link:":" offline venue: ") + activity.linkOrVenue);
        }
        if (index == 0) {
            System.out.println("There is no activity in the list");
        }
    }

    public static void showClass(DateList dateList) {
        int index = 0;
        for (Lesson lesson: dateList.lessons) {
            index++;
            System.out.print(index + ". " + lesson.name + " ");
            DayOfWeek first = lesson.periods.get(0).startDateTime.getDayOfWeek();
            int num = 1;
            System.out.print(first);
            try {
                while (first != lesson.periods.get(num).startDateTime.getDayOfWeek()) {
                    System.out.print(", " + lesson.periods.get(num).startDateTime.getDayOfWeek());
                    num++;
                }
                System.out.print("\n");
            } catch (IndexOutOfBoundsException e) {
                System.out.print("\n");
            }
            System.out.println((lesson.isOnline?" online link:":" offline venue: ")
                    + lesson.linkOrVenue);
        }
        if (index == 0) {
            System.out.println("There is no classes in the list");
        }
    }

    public static LocalDateTime getDate(String date) {
        String [] dateArray = date.split("/");
        int day = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        int year = Integer.parseInt(dateArray[2]);
        return LocalDateTime.of(year, month, day, 0, 0);
    }


}
