package seedu.duke.command;

//@author k-walter
public abstract class DateCommand extends Command {
    protected String period = null;

    /**
     * Checks required year and optional month parameters.
     *
     * @param year  NOT NULL, eg "2020"
     * @param month NULL or "01".."12"
     */
    public DateCommand(String year, String month) {
        assert year != null;
        period = year;
        if (month != null) {
            boolean mthWithinRange = (1 <= Integer.parseInt(month) && Integer.parseInt(month) <= 12);
            boolean mthLength2 = month.length() == 2;
            assert mthWithinRange && mthLength2;
            period = String.format("%s-%s", period, month);
        }
    }

    public DateCommand() {

    }
}
