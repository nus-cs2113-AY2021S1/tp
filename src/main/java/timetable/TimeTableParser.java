package timetable;

import exceptions.ClashScheduleException;
import exceptions.InvalidDayOfTheWeekException;
import exceptions.InvalidTimeException;
import studyit.StudyItLog;

import java.time.DateTimeException;
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
        case "list activity":
            TimeTableCommand.showActivities(dateList);
            return;
        case "list class":
            TimeTableCommand.showClass(dateList);
            return;
        case "clean up":
            dateList.cleanUpEvent(storage);
            System.out.println("Clean up completed");
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
                            dateList.activities.add(activity);
                            storage.writeFile(activity);
                            System.out.println(Message.printSuccessfulActivityAddition);
                        }
                        break;
                        case "class": {
                            Lesson lesson = TimeTableCommand.addClass();
                            dateList.addEvent(lesson);
                            dateList.lessons.add(lesson);
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
                    System.out.println("Input for days and time of the lesson is invalid. Please add the class again.");
                    StudyItLog.logger.warning("Invalid timetable command: Invalid date input");
                } catch (InvalidDayOfTheWeekException e) {
                    System.out.println("Day of the week input is invalid. Please add the class again.");
                    StudyItLog.logger.warning("Invalid timetable command: Invalid day of the week input");
                } catch (ClashScheduleException e) {
                    System.out.println("There is a clash in schedule! Please check your schedule and add again.");
                } catch (InvalidTimeException | DateTimeException e) {
                    System.out.println("Input for time of the lesson is invalid! Please add class again.");
                }
            } else if (command.contains("delete activity") && words.length == 3) {
                try {
                    int index = Integer.parseInt(words[2]);
                    System.out.println(dateList.activities.get(index - 1).name + " has been deleted");
                    dateList.deleteActivity(index, storage);
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println(Message.printInvalidEvent);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("The number you have entered is invalid.");
                }
            } else if (command.contains("delete class") && words.length == 3) {
                try {
                    int index = Integer.parseInt(words[2]);
                    System.out.println(dateList.lessons.get(index - 1).name + " has been deleted");
                    dateList.deleteLesson(index, storage);
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println(Message.printInvalidEvent);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("The number you have entered is invalid.");
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
                dateList.lessons.add(lesson);
            }
            break;
            case A: {
                LocalDateTime start = LocalDateTime.parse(words[4]);
                LocalDateTime end = LocalDateTime.parse(words[5]);
                Duration duration = new Duration(start, end);
                Activity activity = new Activity(name, isOnline, linkOrVenue, duration);
                dateList.addEvent(activity);
                dateList.activities.add(activity);
            }
            break;
            default:
            }
        } catch (ClashScheduleException e) {
            System.out.println("There is a clash in schedule!");
        }
    }


}
