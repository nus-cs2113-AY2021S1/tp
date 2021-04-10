package seedu.hdbuy.storage;

import seedu.hdbuy.common.HdBuyLogger;
import seedu.hdbuy.common.Unit;
import seedu.hdbuy.common.exception.DuplicateUnitException;
import seedu.hdbuy.common.exception.MissingFileException;
import seedu.hdbuy.data.ShortList;
import seedu.hdbuy.ui.TextUi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StorageManager {

    private static final String filePath = "./shortlist.txt";

    /**
     * This method will read the saved shortlist from a .TXT file and input it into the app.
     */
    public static void read() {
        try {
            File f = new File(filePath);
            if (f.createNewFile()) {
                HdBuyLogger.info("Shortlist can be found at: \" + f.getPath()");
            }
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String input = s.nextLine();
                if (!input.isEmpty()) {
                    Unit unit = UnitDecoder.textToUnit(input);
                    if (unit != null) {
                        ShortList.addToShortList(unit);
                    }
                }
            }
        } catch (IOException e) {
            TextUi.showExceptionMessage(new MissingFileException(filePath));
        } catch (DuplicateUnitException e) {
            TextUi.showExceptionMessage(e);
        }
    }

    /**
     * Before the app shut down, the app will save the non-empty shortlist into a .TXT file.
     * The user will then be able to access their shortlist next time they start up the app.
     */
    public static void write() {
        ArrayList<Unit> units = ShortList.getShortListedUnits();
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Unit unit : units) {
                String unitText = unit.encodeToText();
                if (unitText != null && !unitText.isEmpty()) {
                    fw.write(unitText + "\n");
                }
            }
            fw.close();
        } catch (IOException e) {
            TextUi.showExceptionMessage(new MissingFileException(filePath));
        }
    }
}
