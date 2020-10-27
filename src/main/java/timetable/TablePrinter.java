package timetable;

import java.time.LocalDate;
import java.util.List;


public class TablePrinter {
    private static final String space = " ";

    public static void printTable(List<EventList> dateList) {
        String[][] table = new String[8][25];
        table[0][0] = space;
        for (int i = 0; i < 2400; i += 100) {
            table[0][i / 100 + 1] = String.format("%04d", i);
        }

        for (int i = 0; i < 7; i++) {
            table[i + 1][0] = LocalDate.now().plusDays(i).getDayOfWeek().name();
            boolean skip = false;
            for (int dateListIndex = 0; dateListIndex < dateList.size() && !skip; dateListIndex++) {
                if (dateList.get(dateListIndex).dateTag.equals(LocalDate.now().plusDays(i))) {
                    for (int j = 0; j < 24; j++) {
                        boolean isFree = true;
                        for (int eventListIndex = 0; eventListIndex < dateList.get(dateListIndex).events.size();
                             eventListIndex++) {
                            Event current = dateList.get(dateListIndex).events.get(eventListIndex);
                            for (Duration period: current.periods) {
                                if (period.containTimeSlot(j * 100) && period.startDateTime
                                        .toLocalDate().equals(dateList.get(dateListIndex).dateTag)) {
                                    table[i + 1][j + 1] = current.name;
                                    isFree = false;
                                }
                            }
                        }
                        if (isFree) {
                            table[i + 1][j + 1] = space;
                        }
                    }
                    skip = true;
                }
            }
            for (int j = 0; j < 24 && !skip; j++) {
                table[i + 1][j + 1] = space;
            }
        }

        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.printf("%-10s|", table[j][i]);
            }
            System.out.println("");
        }
    }

}
