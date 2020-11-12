package zeronote;

import org.junit.jupiter.api.Test;
import zeronote.userinterface.AppMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppModeTest {
    @Test
    public void appMode_toString_notebookShelfModeTest() {
        AppMode test = AppMode.NOTEBOOK_SHELF;
        String testToString = test.toString();
        assertEquals("notebook", testToString);
    }

    @Test
    public void appMode_toString_notebookModeTest() {
        AppMode test = AppMode.NOTEBOOK_BOOK;
        String testToString = test.toString();
        assertEquals("notebook book", testToString);
    }

    @Test
    public void appMode_toString_sectionModeTest() {
        AppMode test = AppMode.NOTEBOOK_SECTION;
        String testToString = test.toString();
        assertEquals("notebook section", testToString);
    }

    @Test
    public void appMode_toString_pageModeTest() {
        AppMode test = AppMode.NOTEBOOK_PAGE;
        String testToString = test.toString();
        assertEquals("notebook page", testToString);
    }
}
