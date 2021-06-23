package logic.parser;

import exceptions.InvalidDateFormatException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.List;

//@@author wgzesg
/**
 * Represents the class containing all the date formats.
 */
public class DateParser {
    private static final List<DateTimeFormatter> dtFormatters = Arrays.asList(
            DateTimeFormatter.ofPattern("uuuuMMdd HH:mm").withResolverStyle(ResolverStyle.STRICT),
            DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm").withResolverStyle(ResolverStyle.STRICT),
            DateTimeFormatter.ofPattern("uuuu MM dd HH:mm").withResolverStyle(ResolverStyle.STRICT),
            DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm").withResolverStyle(ResolverStyle.STRICT),
            DateTimeFormatter.ofPattern("uuuuMMdd HHmm").withResolverStyle(ResolverStyle.STRICT),
            DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm").withResolverStyle(ResolverStyle.STRICT),
            DateTimeFormatter.ofPattern("uuuu MM dd HHmm").withResolverStyle(ResolverStyle.STRICT),
            DateTimeFormatter.ofPattern("uuuu/MM/dd HHmm").withResolverStyle(ResolverStyle.STRICT)
    );

    private static final List<DateTimeFormatter> dFormatters = Arrays.asList(
            DateTimeFormatter.ofPattern("uuuuMMdd").withResolverStyle(ResolverStyle.STRICT),
            DateTimeFormatter.ofPattern("uuuu/MM/dd").withResolverStyle(ResolverStyle.STRICT),
            DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT),
            DateTimeFormatter.ofPattern("uuuu MM dd").withResolverStyle(ResolverStyle.STRICT),
            DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT),
            DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT),
            DateTimeFormatter.ofPattern("dd MM uuuu").withResolverStyle(ResolverStyle.STRICT),
            DateTimeFormatter.ofPattern("ddMMuuuu").withResolverStyle(ResolverStyle.STRICT)
    );

    /**
     * Parses a given string following one of the accepted format into date-time format.
     *
     * @param targetString String input to be parsed.
     * @return Parsed result in the form of LocalDateTime or null if no value parsing is found.
     * @throws InvalidDateFormatException If date format is invalid.
     */
    public static LocalDateTime parseDate(String targetString) throws InvalidDateFormatException {
        for (DateTimeFormatter dtf : dtFormatters) {
            try {
                return LocalDateTime.parse(targetString, dtf);
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        for (DateTimeFormatter dtf : dFormatters) {
            try {
                return LocalDate.parse(targetString, dtf).atStartOfDay();
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        throw new InvalidDateFormatException();
    }
}
