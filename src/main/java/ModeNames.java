public class ModeNames {
    public static final String BOOKMARK_NAME = "bookmark";
    public static final String TIMETABLE_NAME = "timetable";
    public static final String ACADEMIC_NAME = "academic";
    public static final String FLASHCARD_NAME = "flashcard";
    public static final String MENU_NAME = "menu";

    public static String getCurrentModeName() {
        Mode currentMode = StudyIt.getCurrentMode();

        switch (currentMode) {
        case BOOKMARK:
            return BOOKMARK_NAME;
        case TIMETABLE:
            return TIMETABLE_NAME;
        case ACADEMIC:
            return ACADEMIC_NAME;
        case FLASHCARD:
            return FLASHCARD_NAME;
        default:
            return MENU_NAME;
        }
    }
}