package seedu.financeit.parser;

import seedu.financeit.common.Constants;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that stores and manages the output of Datetime classes.
 */
public class DateTimeManager {
    private LocalDate date;
    private LocalTime time;

    public DateTimeManager(){

    }

    public DateTimeManager(LocalDateTime dateTime) {
        this.time = dateTime.toLocalTime();
        this.date = dateTime.toLocalDate();
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Takes in one format specifier string and outputs datetime string with the corresponding format.
     * @param whichFormat Format to determine output of dateTime.
     * @return Formatted dateTime string
     */

    public String getSingleDateFormatted(String whichFormat) {
        String output = "";

        switch (whichFormat) {
        case "date":
            // May 27 2020
            output = this.date.format(DateTimeFormatter.ofPattern("MMM d YYYY"));
            break;
        case "day":
            // Tuesday
            output = this.date.getDayOfWeek().toString();
            break;
        case "month":
            // May
            output = this.date.getMonth().toString();
            break;
        case "year":
            // 2020
            output = Integer.toString(date.getYear());
            break;
        default:
            // Show all information
            output = this.date.format(DateTimeFormatter.ofPattern("YYYY/MM/dd"));
            break;
        }
        return output;
    }

    public String getSingleTimeFormatted(String whichFormat) {
        String output = "";

        switch (whichFormat) {
        case "time":
            // 2020
            output = time.toString();
            break;
        default:
            // Show all information
            output = this.time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            break;
        }
        return output;
    }

    /**
     * Handles a String array of format specifiers
     * and arranges the tokens of formatted datetime substrings in a proper order.
     * @param whichFormat Format to determine output of dateTime
     * @return Formatted dateTime string
     */
    public String getDateTimeFormatted(String[] whichFormat) {
        String[] token = new String[Constants.MAX_ARRAY_LEN];
        String outputDate;
        String outputTime;
        String output = "";

        if (whichFormat == null) {
            return getSingleDateFormatted(" ")
                + getSingleTimeFormatted(" ");
        }

        for (String format : whichFormat) {
            outputDate = getSingleDateFormatted(format);
            outputTime = getSingleTimeFormatted(format);
            switch (format) {
            case "day":
                token[0] = outputDate;
                break;
            case "month":
                token[2] = outputDate;
                break;
            case "year":
                token[3] = outputDate;
                break;
            case "time":
                token[4] = outputTime;
                break;
            default:
                // Fall-through
                break;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (token[i] != null) {
                output += token[i] + " ";
            }
        }
        return output.trim();
    }
}

