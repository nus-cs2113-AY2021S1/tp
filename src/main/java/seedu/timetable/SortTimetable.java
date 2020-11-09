package seedu.timetable;

import seedu.exception.WhereGotTimeException;
import seedu.task.Event;
import seedu.user.User;
import seedu.user.UserList;

import java.util.ArrayList;

public class SortTimetable {

    public static void sortTimetable(UserList users, User nowUser, String day) throws WhereGotTimeException {
        assert nowUser != null;
        int userIndex = -1;

        for (int i = 0; i < users.getTotalUserCount(); i++) {
            if ((users.getUser(i + 1).getName() == nowUser.getName())) {
                userIndex = i + 1;
            }
        }

        ArrayList<Event> timetable = (users.getUser(userIndex).getTimetable()).getTimetable(day);
        ArrayList<Event> sortedTimetable = new ArrayList<>();
        sortedTimetable.clear();

        assert !timetable.isEmpty();

        while (timetable.size() != 0) {
            int earliestClassTime = 2400;
            int earliestClassIndex = 0;
            int currentClassIndex = 0;
            for (Event event : timetable) {
                int currentClassTime = Integer.parseInt(event.getTimeStart());
                if (currentClassTime < earliestClassTime) {
                    earliestClassIndex = currentClassIndex;
                    earliestClassTime = currentClassTime;
                }
                currentClassIndex++;
            }
            sortedTimetable.add(timetable.get(earliestClassIndex));
            timetable.remove(earliestClassIndex);
        }

        for (Event event : sortedTimetable) {
            timetable.add(event);
        }
    }
}
