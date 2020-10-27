package seedu.zoomaster;

import org.junit.jupiter.api.Test;
import seedu.zoomaster.bookmark.Bookmark;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;
import seedu.zoomaster.slot.Timetable;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StorageTest {

    @Test
    void loadTimetableWithInvalidFilePath() throws ZoomasterException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        Storage test = new Storage("iNvAlIdPaThNaMe/data/timetable/", Timetable.class);
        assertTrue(test.load() instanceof Timetable);
    }

    @Test
    void loadStorageWithInvalidFilePath() throws ZoomasterException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        Storage test = new Storage("iNvAlIdPaThNaMe/data/bookmark/", BookmarkList.class);
        assertTrue(test.load() instanceof BookmarkList);
    }

    @Test
    void loadWithBaseClassThrowsErrorLoadingFileException() throws ZoomasterException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Storage test = new Storage("iNvAlIdPaThNaMe/data/timetable/", Bookmark.class);
        ZoomasterException e = assertThrows(ZoomasterException.class, () -> test.load());
        assertEquals(ZoomasterExceptionType.ERROR_LOADING_FILE, e.getError());
    }

    @Test
    void getFiles() {
    }

    @Test
    void loadPlanner() {
    }

    @Test
    void saveNullObjectThrowsWriteFileErrorException() {
        Storage test = new Storage("/data/timetable/", Timetable.class);
        ZoomasterException e = assertThrows(ZoomasterException.class, () -> test.save(null));
        assertEquals(ZoomasterExceptionType.WRITE_FILE_ERROR, e.getError());
    }

    @Test
    void loadModuleListWithInvalidPathThrowsFileNotFoundException() {
        Storage test = new Storage("iNvAlIdPaThNaMe/data/timetable/", Timetable.class);
        assertThrows(FileNotFoundException.class, () -> test.loadModuleList());
    }
}