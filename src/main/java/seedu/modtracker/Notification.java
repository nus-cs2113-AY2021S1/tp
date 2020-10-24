package seedu.modtracker;

import java.util.ArrayList;

import static seedu.modtracker.ModuleList.modList;

public class Notification {

    public static int numOfNotification = 0;
    public static int currentWeek;
    public static final String ON_TRACK = "You are on track. Well Done!";
    public static final String TOO_MUCH_TIME = "Beware! Seems like you are spending too much time on %s.";
    public static final String TOO_LITTLE_TIME = "Oh no! It appears you are spending too little time on %s.";
    public static final String OPEN_NOTIFICATION = "Please type `open` to view the notifications.";


    //update week number with user input
    public void updateCurrentWeek() {
        currentWeek = 13;
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
        if (mod.doesExpectedWorkLoadExist() && mod.doesActualTimeExist(currentWeek)) {
            return true;
        }
        return false;
    }

    public void getNumNotification(ModuleList list) {
        ArrayList<Module> modList = list.getData();
        updateCurrentWeek();
        if (currentWeek == 0) {
            return;
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
    }

    public void printNotification(ModuleList list) {
        getNumNotification(list);
        if (numOfNotification == 0 || currentWeek == 0) {
            System.out.println(ON_TRACK + System.lineSeparator());
            return;
        }
        ArrayList<Module> modList = list.getData();
        for (Module mod : modList) {
            ViewTimeBreakdownAnalysis breakDown = new ViewTimeBreakdownAnalysis();
            Analysis analysis;
            if (mod.doesExpectedWorkLoadExist()) {
                analysis = breakDown.computeAnalysisOfTimeSpent(mod, currentWeek);
                switch (analysis) {
                case tooMuchTimeSpent:
                    System.out.println(String.format(TOO_MUCH_TIME, mod.getModuleCode()));
                    System.out.println();
                    break;
                case tooLittleTimeSpent:
                    System.out.println(String.format(TOO_LITTLE_TIME, mod.getModuleCode()));
                    System.out.println();
                    break;
                default:
                    break;
                }
            }
        }
    }

    public void start() {
        ModuleList list = new ModuleList();
        getNumNotification(list);
        if (numOfNotification > 0) {
            System.out.println("You have " + numOfNotification + " notifications.");
            System.out.println(OPEN_NOTIFICATION);
            System.out.println("");
        }
    }


}
