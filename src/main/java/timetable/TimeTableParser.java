package timetable;

import studyit.StudyItLog;

import java.time.LocalDateTime;

public class TimeTableParser {
    public static void commandParser(String command, DateList dateList, TimeTableStorage storage) {
        switch (command) {
        case "show schedule":
            System.out.println(Message.printShowSchedule);
            TablePrinter.printTable(dateList.dateList);
            return;
        case "show link":
            System.out.println(Message.printShowLink);
            TimeTableCommand.showLink(dateList);
            return;
        case "show activity":
            TimeTableCommand.showActivities(dateList);
            return;
        default:
            String[] words = command.split(" ");
            if (words.length == 2) {
                try {
                    String action = words[0];
                    String type = words[1];
                    if (action.equals("add")) {
                        switch (type) {
                        case "activity": {
                            Activity activity = TimeTableCommand.addActivity();
                            dateList.addEvent(activity);
                            storage.writeFile(activity);
                            System.out.println(Message.printSuccessfulActivityAddition);
                        }
                        break;
                        case "class": {
                            Lesson lesson = TimeTableCommand.addClass();
                            dateList.addEvent(lesson);
                            storage.writeFile(lesson);
                            System.out.println(Message.printSuccessfulClassAddition);
                        }
                        break;
                        default:
                            System.out.print((Message.printInvalidEvent));
                        }
                    } else {
                        System.out.print(Message.printInvalidEvent);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Input for days and time of the lesson is invalid Please add class again");
                    StudyItLog.logger.warning("Invalid timetable command: Invalid event input");
                } catch (InvalidDayOfTheWeekException e) {
                    System.out.println("Day of the week input is invalid. Please add the class again.");
                    StudyItLog.logger.warning("Invalid timetable command: Invalid day of the week input");
                } catch (ClashScheduleException e) {
                    System.out.println("There is a clash in schedule!");
                }
            } else {
                System.out.print(Message.printInvalidEvent);
            }
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


}
