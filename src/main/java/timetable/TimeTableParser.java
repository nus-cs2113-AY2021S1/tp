package timetable;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class TimeTableParser {
    public static void commandParser(String command, DateList dateList, TimeTableStorage storage) {
        if (command.equals("show schedule")) {
            System.out.println(Message.printShowSchedule);
            TablePrinter.printTable(dateList.dateList);
            return;
        }
        try {
            String[] words = command.split(" ", 3);
            String action = words[0];
            String type = words[1];
            if (action.equals("add")) {
                switch (type) {
                case "activity":
                    break;
                case "class": {
                    Lesson lesson = addClass(words[2]);
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
        }
    }

    private static Lesson addClass(String classInfo) throws ArrayIndexOutOfBoundsException {
        String [] phrase = classInfo.substring(1).split(" /");
        String moduleCode = phrase[0];
        boolean isOnline;
        isOnline = phrase[1].equals("online");
        String linkOrVenue = phrase[2];
        String [] periods = phrase[3].split(", ");
        LocalDateTime start = getDateTime(classInfo);
        int startDayNum = start.getDayOfWeek().getValue();
        int repeat = Integer.parseInt(phrase[4].split(" ")[0]);
        Lesson lesson = new Lesson(moduleCode, linkOrVenue, isOnline, repeat);
        for (int i = 0; i < repeat; i++) {
            for (String period : periods) {
                String day = period.split(" ")[0].toUpperCase().replace(" ", "");
                String time = period.split(" ")[1];
                int dayNum = DayOfWeek.valueOf(day).getValue();
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
                LocalDateTime startDateTime = start.plusDays(daysDifference).plusHours(startTime).plusDays(7 * i);
                LocalDateTime endDateTime = startDateTime.withHour(endTime).plusDays(7 * i);
                Duration duration = new Duration(startDateTime, endDateTime);
                lesson.addPeriod(duration);
            }
        }
        return lesson;
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
                LocalDateTime start = LocalDateTime.parse(words[5 + 2 * i + 1]);
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
