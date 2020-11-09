package editor;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import command.AddCommand;
import command.Command;
import exception.CommandException;
import exception.EditorException;
import org.junit.jupiter.api.Test;
import parser.CommandFlag;
import parser.Parser;
import settings.Settings;
import storage.DataFileDestroyer;
import ui.Printer;
import ui.Ui;

import java.awt.event.ActionEvent;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EditorTest {

    @Test
    void setting_normal_content_test() throws EditorException {
        /* Editor testEditor = new Editor();
        CheatSheet testCheatSheet = new CheatSheet("Test1", "Testing", "Testing the editor");
        testEditor.setContent(testCheatSheet);
        assertEquals(testEditor.getContent(),"Testing the editor");*/
    }

    @Test
    void getting_normal_content_test() throws EditorException {
        /* Editor testEditor = new Editor();
        CheatSheet testCheatSheet = new CheatSheet("Test1", "Testing", "Testing the editor");
        testEditor.setContent(testCheatSheet);
        String editorContent = testEditor.getContent();
        assertEquals(editorContent,"Testing the editor"); */
    }
}