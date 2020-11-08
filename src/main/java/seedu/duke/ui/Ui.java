package seedu.duke.ui;

import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.event.Goal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;

public class Ui {
    Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public void printDividerLine() {
        System.out.println("_________________________________");
    }

    public void printWelcomeMessage() {
        printDividerLine();
        System.out.println("Welcome to scheduler--;!");
        System.out.println("What can I do for you?");
        printDividerLine();
    }

    public void printByeMessage() {
        System.out.println("Thank you for using scheduler--;!");
        System.out.println("We hope to see you soon!");
        printDividerLine();
    }

    public String receiveCommand() {
        return in.nextLine().trim();
    }

    public void printEventAddedMessage(Event event) {
        System.out.println("You have successfully added this event to your list!");
        System.out.println(event);
    }

    public void printRepeatAdd(Event event) {
        System.out.println(event);
        System.out.println("is now repeating " + event.getRepeatType() + " for " + event.getRepeatCount() + " times.");
    }

    public void printRepeatList(Event event) {
        System.out.println(event + " is also on:");
        ArrayList<Event> repeatEventList = event.getRepeatEventList();
        int index = 1;
        for (Event e : repeatEventList) {
            System.out.print("    " + index + ". ");
            System.out.printf("%s ", e.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            if (e.getTime() != null) {
                System.out.printf("%s ", e.getTime().format(DateTimeFormatter.ofPattern("HH:mm")));
            }
            System.out.printf("[%s]", e.getStatus());
            System.out.println();
            index++;
        }
    }

    public void printGoalMessage(Goal goal) {
        if (goal != null) {
            System.out.println("Goal: " + goal);
        } else {
            System.out.println("You have no goal! Why not set one now?");
        }
    }

    public void printChangeGoalMessage(Goal goal) {
        if (goal != null) {
            System.out.println("Goal changed to: " + goal);
        } else {
            System.out.println("No more goal!");
        }
    }

    private void printCalendarDivider() {
        System.out.println("---------------------------------------------------------------------------------------");
    }

    public void printCalendar(Map.Entry<LocalDate, ArrayList<Event>> entry) {
        printCalendarDivider();
        System.out.println(entry.getKey().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
        printCalendarDivider();
        ArrayList<Event> eventsOnDate;
        eventsOnDate = entry.getValue();
        eventsOnDate.sort(Comparator.comparing(Event::getTime));
        for (Event e : eventsOnDate) {
            System.out.println(e.toCalendarString());
        }
    }

    /**
     * Prints the number dates to list in the calendar and the number of events not in the calendar.
     *
     * @param size  number of unique dates in the calendar.
     * @param count number of events not in the calendar.
     */
    public void printCalendarStart(int size, int count) {
        System.out.println("Calendar has " + size + " dates to display");
        if (count > 1) {
            System.out.println(count + " events not on the calendar because they have no date and time");
        } else if (count > 0) {
            System.out.println(count + " event not on the calendar because it has no date and time");
        }
    }

    /**
     * Prints the end of the calendar.
     */
    public void printCalendarEnd() {
        printCalendarDivider();
        System.out.println("End of calendar");
    }

    /**
     * Prints a message to tell the user instructions to continue or to exit the calendar.
     */
    public void printContinueQuery() {
        printCalendarDivider();
        System.out.println("Enter 'q' to exit or enter to continue...");
    }

    public void printCheckMessage() {

    }

    /**
     * Lists the name of all EventList in the given EventList ArrayList.
     *
     * @param eventLists EventList ArrayList to list.
     */
    public void printAvailableList(ArrayList<EventList> eventLists) {
        System.out.println("Following are all existing lists:");
        for (EventList list : eventLists) {
            System.out.println(list.getName());
        }
        System.out.println("'list All' will list all existing lists.");
    }

    /**
     * Lists all the events in the given EventList.
     *
     * @param eventList EventList to list.
     */
    public void printList(EventList eventList) {
        String eventListName = eventList.getName();
        ArrayList<Event> events = eventList.getEvents();
        if (events.size() == 0) {
            System.out.println("You have no " + eventListName + " events!");
        } else {
            System.out.println("Here is a list of your " + eventListName + " events:");
            int index = 1;
            for (Event e : events) {
                System.out.print(index + ". ");
                if (e.getRepeatEventList() != null) {
                    printRepeatList(e);
                } else {
                    System.out.println(e);
                }
                if (e.getNotes().size() > 0) {
                    String indexWord = Integer.toString(index);
                    System.out.println("   Type \"view " + eventListName + " " + indexWord + "\" to see notes");
                }
                index++;
            }
        }
    }

    public void printDeadlineChangedMessage(Event eventUpdated) {
        System.out.println("You have successfully updated the deadline for this event!");
        System.out.println(eventUpdated);
    }

    /**
     * Printing reminder.
     *
     * @param eventsWithTime    events that have time.
     * @param eventsWithoutTime events without time.
     */
    public void printReminder(ArrayList<Event> eventsWithTime, ArrayList<Event> eventsWithoutTime) {
        if (eventsWithoutTime.size() == 0 && eventsWithTime.size() == 0) {
            System.out.println("You have no events today!");
        } else {
            printReminderEvents(eventsWithTime, eventsWithoutTime);
        }
    }

    /**
     * Printing events according to events that have time and without time.
     *
     * @param eventsWithTime    events that have time.
     * @param eventsWithoutTime events without time.
     */
    private void printReminderEvents(ArrayList<Event> eventsWithTime, ArrayList<Event> eventsWithoutTime) {
        System.out.println("You have the following events today: ");
        if (eventsWithTime.size() > 0) {
            System.out.println("_________Events With Time________");
            Collections.sort(eventsWithTime, new Comparator<Event>() {
                @Override
                public int compare(Event o1, Event o2) {
                    return o1.getTime().compareTo(o2.getTime());
                }
            });
            for (int i = 0; i < eventsWithTime.size(); i++) {
                System.out.println(eventsWithTime.get(i).toString());
            }

        }

        if (eventsWithoutTime.size() > 0) {
            System.out.println();
            System.out.println("_________Events Without Time_____");
            for (int i = 0; i < eventsWithoutTime.size(); i++) {
                System.out.println(eventsWithoutTime.get(i).toString());
            }
        }

    }

    public void printEventMarkedDoneMessage(Event doneEvent) {
        System.out.println("You have successfully marked this event as done!");
        System.out.println(doneEvent);
    }

    public void printEventMarkedUndoneMessage(Event undoneEvent) {
        System.out.println("You have successfully marked this event as undone!");
        System.out.println(undoneEvent);
    }

    public void printEventDeletedMessage(Event deleteEvent) {
        System.out.println("You have successfully deleted this event!");
        System.out.println(deleteEvent);
    }

    /**
     * Print note created along with existing notes.
     *
     * @param eventUpdated event attached to notes.
     * @param notes        list of notes.
     */
    public void printNoteMessage(Event eventUpdated, ArrayList<String> notes) {
        System.out.println("You have successfully written the note for this event!");
        System.out.println(eventUpdated);
        for (int i = 0; i < notes.size(); i++) {
            System.out.println(notes.get(i));
        }

    }

    public void printViewNote(ArrayList<String> notes) {

        if (notes.size() == 0) {
            System.out.println("You have not written any notes for this event!");
        } else {
            System.out.println("These are the notes that you have taken: ");
            for (int i = 0; i < notes.size(); i++) {
                System.out.println(notes.get(i));
            }
        }


    }

    public void printStorageSavedMessage() {
        System.out.println("The file has successfully been saved!");
    }

    public void printStorageLoadMessage() {
        System.out.println("The file has successfully been loaded!");
    }

    public void printStorageLoadingErrorMessage() {
        System.out.println("The file does not exist or has been corrupted!");
    }

    /**
     * Prints the message when no date was detected for a personal event.
     */
    public void printExtractNoDatePersonalEventMessage() {
        System.out.println("Since no date was detected in the text body, "
                + "the personal event will only contain the description.");
    }

    /**
     * Prints the message when no date was detected for a zoom event.
     */
    public void printExtractNoDateZoomEventMessage() {
        System.out.println("Since no date was detected in the text body, "
                + "the zoom event will only contain the description and zoom link.");
    }

    /**
     * Prints the message when no time was detected in the zoom event,
     * resulting in creation of zoom event with only link.
     */
    public void printExtractNoTimeZoomEventMessage() {
        System.out.println("Even though date was detected, time was not, in Scheduler--; "
                + "a zoom event cannot have the date without the time. So "
                + "a zoom event with only the date and zoom link will be created.");
    }

    /**
     * Prints the message when no time was detected for a personal event.
     */
    public void printExtractNoTimePersonalEventMessage() {
        System.out.println("Since no time was detected in the text body, "
                + "the personal event will only contain the description and the date.");
    }

    /**
     * Prints the message to choose from timings detected.
     *
     * @param timeCount The number of timings detected.
     * @param timeList An ArrayList of LocalTime containing all timings detected.
     */
    public void printExtractChooseTimeMessage(int timeCount, ArrayList<LocalTime> timeList) {
        System.out.println("We have detected " + timeCount + " timings in this text body!");
        System.out.println("Please select the time you want for this event from the list below!");
        int timeNumber = 0;
        printDividerLine();
        for (LocalTime time : timeList) {
            System.out.println(timeNumber + 1 + ". " + time);
            timeNumber++;
        }
        printDividerLine();
    }

    /**
     * Prints message when only 1 time was detected.
     *
     * @param finalTime The only LocalTime object detected.
     */
    public void printExtractSingleTimeDetectedMessage(LocalTime finalTime) {
        System.out.println("One timing detected and chosen: " + finalTime);
    }

    /**
     * Prints the message to choose from date detected.
     *
     * @param dateCount The number of dates detected.
     * @param dateList An ArrayList of LocalDate containing all dates detected.
     */
    public void printExtractChooseDateMessage(int dateCount, ArrayList<LocalDate> dateList) {
        System.out.println("We have detected " + dateCount + " dates in this text body!");
        System.out.println("Please select the date you want for this event from the list below!");
        int dateNumber = 0;
        printDividerLine();
        for (LocalDate date : dateList) {
            System.out.println(dateNumber + 1 + ". " + date);
            dateNumber++;
        }
        printDividerLine();
    }

    /**
     * Prints the message when only 1 date was detected.
     *
     * @param finalDate The only LocalDate object detected.
     */
    public void printExtractSingleDateDetectedMessage(LocalDate finalDate) {
        System.out.println("One date detected and chosen: " + finalDate);
    }

    /**
     * Prints the instructions to tell users what to do for the extract feature.
     */
    public void printExtractTextBodyRequestMessage() {
        System.out.println("Copy and paste or enter the body of the text you want to extract from!");
        System.out.println("At the end of your text, press enter to go to the next line, enter 'extractend' "
                + "with no quotation marks and press enter once more.");
    }

    /**
     * Prints the message when an invalid number was chosen from the list.
     *
     * @param field A string that could contain the any field that needs this method.
     */
    public void printExtractInvalidFieldChosenMessage(String field) {
        System.out.println("Invalid " + field + " number to choose! Please choose again!");
        printDividerLine();
    }

    /**
     * Prints the message when 0 of a field was detected from the text body.
     *
     * @param field A string that could contain the any field that needs this method.
     */
    public void printExtractNoFieldMessage(String field) {
        System.out.println("No " + field + " detected for this text body!");
    }

    /**
     * Prints a message to choose from all zoom links detected.
     *
     * @param zoomLinkCount The number of zoom links detected.
     * @param zoomLinkList An ArrayList of String containing all the zoom links detected.
     */
    public void printExtractChooseZoomLinkMessage(int zoomLinkCount, ArrayList<String> zoomLinkList) {
        System.out.println("We have detected " + zoomLinkCount + " zoom links in this text body!");
        System.out.println("Please select the zoom link you want for this event from the list below!");
        int zoomLinkNumber = 0;
        printDividerLine();
        for (String zoomLink : zoomLinkList) {
            System.out.println(zoomLinkNumber + 1 + ". " + zoomLink);
            zoomLinkNumber++;
        }
        printDividerLine();
    }

    /**
     * Prints a message when only 1 zoom link was detected.
     *
     * @param zoomLink The only String of zoom link detected.
     */
    public void printExtractSingleZoomLinkDetectedMessage(String zoomLink) {
        System.out.println("One zoom link detected and chosen: " + zoomLink);
    }

    /**
     * Prints exception message.
     *
     * @param exceptionMessage String of warning message from various exceptions.
     */
    public void printErrorMessage(String exceptionMessage) {
        System.out.println(exceptionMessage);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}