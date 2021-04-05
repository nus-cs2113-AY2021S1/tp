package seedu.hdbuy.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import seedu.hdbuy.common.HdBuyLogger;
import seedu.hdbuy.common.Unit;
import seedu.hdbuy.common.exception.DuplicateUnitException;
import seedu.hdbuy.common.exception.MissingFileException;
import seedu.hdbuy.data.ShortList;
import seedu.hdbuy.ui.TextUi;

public class StorageManager {

    private static final String filePath = "./shortlist.txt";

    public static void read() throws MissingFileException {
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
            HdBuyLogger.error("Unable to locate text file at: \" + f.getPath()");
            throw new MissingFileException();
        } catch (DuplicateUnitException e) {
            TextUi.showDuplicateUnit(e);
        }
    }

    public static void write() throws MissingFileException {
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
            HdBuyLogger.error("Unable to locate text file at: \" + f.getPath()");
            throw new MissingFileException();
        }
    }
}
