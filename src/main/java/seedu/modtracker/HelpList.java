package seedu.modtracker;

/**
 * Stores a list of all the commands available.
 */
public class HelpList {

    /**
     * Prints the full list of commands available.
     */
    public void listCommands() {
        System.out.println("Available Commands:\n"
                + "1. addmod <module code>\n"
                + "   example: addmod CS2113T\n"
                + "2. addexp <module code> <expected workload>\n"
                + "   example: addexp CS2113T 5\n"
                + "3. addtime <module code> <actual time spent> <week number>\n"
                + "   example: addtime CS2113T 2 1\n"
                + "4. list <week number>\n"
                + "   example: list 2\n"
                + "5. deletemod <module code>\n"
                + "   example: deletemod CS2113T\n"
                + "6. deleteexp <module code>\n"
                + "   example: deleteexp CS2113T\n"
                + "7. minus <module code> <time> <week number>\n"
                + "   example: minus CS2113T 2 1\n"
                + "8. compare <module code>\n"
                + "   example: compare CS2113T\n"
                + "9. exit\n");

    }
}
