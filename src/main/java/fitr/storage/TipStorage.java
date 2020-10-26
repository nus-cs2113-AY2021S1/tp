package fitr.storage;

import fitr.common.ResourceManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class TipStorage {
    private static final Logger LOGGER = Logger.getLogger(TipStorage.class.getName());
    private static final String TIP_LIST_PATH = "tips.txt";

    /**
     * Loads the tips from a file and returns an ArrayList of String tips.
     *
     * @return an ArrayList of String tips
     * @throws IOException if an I/O error has occurred
     */
    public ArrayList<String> loadTipList() throws IOException {
        LOGGER.fine("Attempting to read file: " + TIP_LIST_PATH);
        ArrayList<String> tipList = new ArrayList<>();

        BufferedReader br = new BufferedReader(ResourceManager.loadResource(TIP_LIST_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            tipList.add(line);
        }

        LOGGER.fine("Tip list file written successfully.");
        return tipList;
    }
}
