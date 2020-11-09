package seedu.modtracker;

import java.util.ArrayList;
import java.util.Random;

import static seedu.modtracker.ModuleList.modList;

public class Notification {

    private static int numOfNotification = 0;
    private static final int MIN_WEEK = 1;
    private static final int MAX_WEEK = 13;
    private static int currentWeek;
    public static final String ON_TRACK = "You are on track in week %d. Well Done!";
    public static final String MAINTAIN = "Keep up the good work!";
    public static final String SUCCESS = "Good time management boosts productivity!";
    public static final String TIME_MANAGEMENT = "Good time management is the key to success!";

    public static final String HARD_WORK = "The harder you work, the closer you are to success!";
    public static final String PUSH_ON = "Never give up!";
    public static final String STRONGER = "When we push ourselves, we come back stronger.";
    public static final String CAPABLE = "You are capable of achieving better.";
    public static final String TODAY = "Today is a perfect day to become better.";

    public static final String TOO_MUCH_TIME = "Beware! Seems like you are spending too much time on %s in week %d.";
    public static final String TOO_LITTLE_TIME = "Oh no! It appears you are spending too little time on %s in week %d.";
    public static final String OPEN = "Please type `open` to view the notification and an encouraging message.";

    public static final String[] lines = {MAINTAIN, SUCCESS, TIME_MANAGEMENT};
    public static final String[] pushForward = {HARD_WORK, PUSH_ON, STRONGER, CAPABLE, TODAY};

    //update week number based on user input
    public void updateCurrentWeek() {
        currentWeek = MAX_WEEK;
        while (currentWeek > 0) {
            for (Module mod : modList) {
                if (hasExpAndAct(mod)) {
                    return;
                }
            }
            currentWeek--;
        }
    }

    //check if module has actual and expected time
    private boolean hasExpAndAct(Module mod) {
        return mod.doesExpectedWorkLoadExist() && mod.doesActualTimeExist(currentWeek);
    }

    /**
     * Returns the number of notifications by updating the week number, followed by
     * checking the modules of that week if there is too much or too less time spent on the modules.
     *
     * @param list list of modules.
     * @return the number of notifications.
     */
    public int getNumNotification(ModuleList list) {
        numOfNotification = 0;
        ArrayList<Module> modList = list.getData();
        updateCurrentWeek();
        if (currentWeek == 0) {
            return 0;
        }
        for (Module mod : modList) {
            ViewTimeBreakdownAnalysis breakDown = new ViewTimeBreakdownAnalysis();
            Analysis analysis;
            if (hasExpAndAct(mod)) {
                analysis = breakDown.computeAnalysisOfTimeSpent(mod, currentWeek);
                switch (analysis) {
                case tooMuchTimeSpent:
                case tooLittleTimeSpent:
                    numOfNotification++;
                    break;
                default:
                    break;
                }
            }
        }
        return numOfNotification;
    }

    public void randomise(String[] lines) {
        String currentLine;
        Random rand = new Random();
        currentLine = lines[rand.nextInt(lines.length)];
        System.out.println(currentLine + System.lineSeparator());
    }

    public void printNotification(ModuleList list) {
        numOfNotification = getNumNotification(list);
        if (numOfNotification == 0 || currentWeek == 0) {
            if (currentWeek == 0) {
                currentWeek = MIN_WEEK;
            }
            System.out.printf((ON_TRACK) + "%n", currentWeek);
            randomise(lines);
            return;
        }
        ArrayList<Module> modList = list.getData();
        boolean isBehind = false;
        for (Module mod : modList) {
            ViewTimeBreakdownAnalysis breakDown = new ViewTimeBreakdownAnalysis();
            Analysis analysis;
            if (hasExpAndAct(mod)) {
                analysis = breakDown.computeAnalysisOfTimeSpent(mod, currentWeek);
                switch (analysis) {
                case tooMuchTimeSpent:
                    System.out.printf((TOO_MUCH_TIME) + "%n", mod.getModuleCode(), currentWeek);
                    System.out.println();
                    break;
                case tooLittleTimeSpent:
                    System.out.printf((TOO_LITTLE_TIME) + "%n", mod.getModuleCode(), currentWeek);
                    System.out.println();
                    isBehind = true;
                    break;
                default:
                    break;
                }
            }
        }
        if (isBehind) {
            randomise(pushForward);
        }
    }

    public void start() {
        ModuleList list = new ModuleList();
        numOfNotification = getNumNotification(list);
        if (numOfNotification > 0) {
            if (numOfNotification == 1) {
                System.out.println("You have 1 notification.");
            } else {
                System.out.println("You have " + numOfNotification + " notifications.");
            }
            System.out.println(OPEN + System.lineSeparator());
        }
    }


}
