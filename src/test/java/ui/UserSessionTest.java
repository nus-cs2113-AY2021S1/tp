//package ui;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;
//
//class UserSessionTest {
//    UserSession userSession;
//    Printer printer;
//
//    UiStub ui;
//    SettingsStub settings;
//    EditorStub editor;
//    DataFileReaderStub fileReader;
//    DataFileWriterStub fileWriter;
//    ParserStub userCommandParser;
//
//    public UserSessionTest() {
//        printer = new Printer();
//        ui = new UiStub();
//        settings = new SettingsStub();
//        editor = new EditorStub();
//        fileReader = new DataFileReaderStub();
//        fileWriter = new DataFileWriterStub();
//        userCommandParser = new ParserStub();
//
//        userSession = new UserSession();
//        userSession.cheatSheetList = null;
//        userSession.fileDestroyer = null;
//        userSession.editor = editor;
//        userSession.ui = ui;
//        userSession.userCommandParser = userCommandParser;
//        userSession.printer = printer;
//        userSession.settings =  settings;
//        userSession.fileReader = fileReader;
//        userSession.fileWriter = fileWriter;
//
//        userSession.isEditorEnabled = false;
//    }
//
//    @Test
//    void start_firstRunDisplayHelp_pass() {
//        fileReader.reset();
//        userSession.isFirstRun = true;
//        settings.setDisplayingHelpMessages(true, true);
//        userSession.start();
//
//        assertEquals(true, fileReader.isDataRead);
//        assertEquals(true, fileReader.isPreloadedExtracted);
//    }
//
//    @Test
//    void start_notFirstRunNoDisplayHelp_pass() {
//        fileReader.reset();
//        userSession.isFirstRun = false;
//        userSession.settings.setDisplayingHelpMessages(false, false);
//        userSession.start();
//
//        assertEquals(false, fileReader.isPreloadedExtracted);
//        assertEquals(true, fileReader.isDataRead);
//    }
//
//    @Test
//    void run_exitCommand_pass() {
//        ui.clearUserInput();
//        ui.pushUserInput("exit");
//        ui.reset();
//        editor.reset();
//        fileReader.reset();
//        userSession.runProgramSequence();
//        assertEquals(false, fileWriter.isDataWritten);
//
//        userSession.exit();
//        assertEquals(false, ui.isExisting);
//        assertEquals(false, editor.isExisting);
//    }
//
//    @Test
//    void run_badCommand_exceptionHandled() {
//        try {
//            ui.clearUserInput();
//            ui.pushUserInput("throw");
//            ui.pushUserInput("exit");
//            fileReader.reset();
//            userSession.runProgramSequence();
//        } catch (Exception e) {
//            fail(e.getMessage());
//        }
//    }
//
//    @Test
//    void run_goodCommand_pass() {
//        try {
//            ui.clearUserInput();
//            ui.pushUserInput("/add");
//            ui.pushUserInput("exit");
//            fileWriter.reset();
//            userSession.runProgramSequence();
//
//            assertEquals(true, fileWriter.isDataWritten);
//        } catch (Exception e) {
//            fail(e.getMessage());
//        }
//    }
//}
