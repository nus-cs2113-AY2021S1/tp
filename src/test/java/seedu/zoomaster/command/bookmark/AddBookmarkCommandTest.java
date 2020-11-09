//package seedu.zoomaster.command.bookmark;
//
//import org.junit.jupiter.api.Test;
//import seedu.zoomaster.Parser;
//import seedu.zoomaster.Ui;
//import seedu.zoomaster.bookmark.Bookmark;
//import seedu.zoomaster.bookmark.BookmarkList;
//import seedu.zoomaster.command.HelpCommand;
//import seedu.zoomaster.exception.ZoomasterException;
//import seedu.zoomaster.exception.ZoomasterExceptionType;
//import seedu.zoomaster.slot.Timetable;
//
//import static org.junit.jupiter.api.Assertions.*;
//
////@@author
//class AddBookmarkCommandTest {
//
//    Timetable timetable = new Timetable();
//    BookmarkList bookmarks;
//    Ui ui = new Ui();
//
//    @Test
//    void execute() {
//    }
//
//    @Test
//    void invalidAddCommand() throws ZoomasterException {
//        Parser.programMode = 0;
//
//        ZoomasterException e = assertThrows(ZoomasterException.class, () -> new HelpCommand("help iNvalidCommand"));
//        assertEquals(ZoomasterExceptionType.UNKNOWN_HELP_COMMAND, e.getError());
//
//        for (String command : modeOneCommands) {
//            e = assertThrows(ZoomasterException.class, () -> new HelpCommand("help" + command));
//            assertEquals(ZoomasterExceptionType.UNKNOWN_HELP_COMMAND, e.getError());
//        }
//
//        for (String command : modeTwoCommands) {
//            e = assertThrows(ZoomasterException.class, () -> new HelpCommand("help" + command));
//            assertEquals(ZoomasterExceptionType.UNKNOWN_HELP_COMMAND, e.getError());
//        }
//
//        for (String command : modeThreeCommands) {
//            e = assertThrows(ZoomasterException.class, () -> new HelpCommand("help" + command));
//            assertEquals(ZoomasterExceptionType.UNKNOWN_HELP_COMMAND, e.getError());
//        }
//
//    }
//}