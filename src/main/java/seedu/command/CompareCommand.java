package seedu.command;

import seedu.exception.NotLoggedInException;
import seedu.exception.WhereGotTimeException;
import seedu.task.Event;
import seedu.ui.Ui;
import seedu.user.User;
import seedu.user.UserList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CompareCommand extends Command {

    public CompareCommand(String input) {
        super(input);
    }

    @Override
    public void execute(UserList users, Ui ui, User nowUser) throws WhereGotTimeException {
        try {
            ArrayList<Integer> outputArray = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                    10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23));

            //@@author yeapcl
            if (nowUser == null) {
                throw new NotLoggedInException("Sorry! You are not logged in to any account!");
            } else {
                int tempInt = 1;
                int sameUserIndex = 0;
                System.out.println("Hey " + nowUser.getName() + ", please enter the index number of the "
                        + "user that you would like to compare with.");
                System.out.println("____________________________________________________________");
                for (User u : users.getUserList()) {
                    if (u == nowUser) {
                        sameUserIndex = tempInt;
                        continue;
                    }
                    System.out.println("\t" + tempInt + ". " + u.getName());
                    tempInt++;
                    
                }
                System.out.println("____________________________________________________________");
                Scanner scanner = new Scanner(System.in);
                String indexString = scanner.nextLine().trim();
                int indexInt = Integer.parseInt(indexString);
                if (indexInt > (users.getTotalUserCount() - 1)) {
                    throw new WhereGotTimeException("You have entered an invalid index number!");
                } else if (indexInt == sameUserIndex) {
                    indexInt += 1;
                }
                //@@author
                
                ArrayList<Event> nowUserTimetable;
                ArrayList<Event> targetUserTimetable;

                final User targetUser = users.getUser(indexInt);
                System.out.println("____________________________________________________________");
                System.out.println("Please input the day (Mon-Sun) that you would like to compare.");
                System.out.println("____________________________________________________________");
                scanner = new Scanner(System.in);
                String date = scanner.nextLine().trim();

                if (targetUser == null) {
                    throw new WhereGotTimeException("No such user!");
                }

                switch (date.toLowerCase()) {
                case "mon":
                    nowUserTimetable = nowUser.getTimetable().getTimetable("mon");
                    targetUserTimetable = targetUser.getTimetable().getTimetable("mon");
                    break;
                case "tue":
                    nowUserTimetable = nowUser.getTimetable().getTimetable("tue");
                    targetUserTimetable = targetUser.getTimetable().getTimetable("tue");
                    break;
                case "wed":
                    nowUserTimetable = nowUser.getTimetable().getTimetable("wed");
                    targetUserTimetable = targetUser.getTimetable().getTimetable("wed");
                    break;
                case "thu":
                    nowUserTimetable = nowUser.getTimetable().getTimetable("thu");
                    targetUserTimetable = targetUser.getTimetable().getTimetable("thu");
                    break;
                case "fri":
                    nowUserTimetable = nowUser.getTimetable().getTimetable("fri");
                    targetUserTimetable = targetUser.getTimetable().getTimetable("fri");
                    break;
                case "sat":
                    nowUserTimetable = nowUser.getTimetable().getTimetable("sat");
                    targetUserTimetable = targetUser.getTimetable().getTimetable("sat");
                    break;
                case "sun":
                    nowUserTimetable = nowUser.getTimetable().getTimetable("sun");
                    targetUserTimetable = targetUser.getTimetable().getTimetable("sun");
                    break;
                default:
                    throw new WhereGotTimeException("Sorry! I don't know what day you mean!");
                }

                /**
                 * Compare targetUser and nowUser Timetables (ArrayList<Object>)
                 * Assuming for each day of the week, timetable ArrayList<Object> contains only [0:23] timeslots
                 * Loop below returns an outputArray that holds the common available hours in that day
                 */
                for (Event event : nowUserTimetable) {
                    int tempX = (Integer.parseInt(event.getTimeStart().substring(0, 2)));
                    int tempY = (Integer.parseInt(event.getTimeEnd().substring(0, 2)));

                    for (int i = tempX; i < tempY; i++) {
                        outputArray.remove(Integer.valueOf(i));
                    }
                }

                for (Event event : targetUserTimetable) {
                    int tempX = (Integer.parseInt(event.getTimeStart().substring(0, 2)));
                    int tempY = (Integer.parseInt(event.getTimeEnd().substring(0, 2)));

                    for (int i = tempX; i < tempY; i++) {
                        outputArray.remove(Integer.valueOf(i));
                    }
                }

                ui.printCompare(outputArray);

            }
        } catch (NumberFormatException e) {
            throw new WhereGotTimeException("Please provide an integer input!");
        }
    }
}