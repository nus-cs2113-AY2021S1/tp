package cheatlogs;

import ui.UserSession;

// Starting class of CheatLogs
public class CheatLogs {

    private static final String FIRST = "FIRST";
    private static final String EMPTY = "";

    /**
     * Starting method of the program. Uses terminal inputs to know if it is
     * the first time running a session of CheatLogs.
     * @param args Arguments provided by the user when first starting the program
     */
    public static void main(String[] args) {
        assert true;

        String argsCommand = args.length > 0
                ? args[0].toUpperCase()
                : EMPTY;

        UserSession session = new UserSession();
        if (argsCommand.equals(FIRST)) {
            session.isFirstRun = true;
        }

        session.start();
        session.runProgramSequence();
        session.exit();
    }
}
