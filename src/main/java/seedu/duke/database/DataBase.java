package seedu.duke.database;


import seedu.duke.food.Food;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataBase{
    private static final String START_SYMBOL = "&%START";
    private static final String STOP_SYMBOL = "&%STOP";
    private static final String UP_SYMBOL = "&%UP";

    private static final String DATA_FILE_SEPERATOR = "\\|";

    private static final String rootDirectory = System.getProperty("user.dir");
    private static final String dataFileFolder = "src" + File.separator + "main" + File.separator +
            "java" + File.separator + "seedu" + File.separator + "duke" + File.separator + "database";

    private final ArrayList<Canteen> canteenList;

    public DataBase(){
        this.canteenList = new ArrayList<>();
    }

    /***
     * Reads a file from the data base and puts it into the DataBase object
     */
    public void init() throws FileNotFoundException {
        String fileFolder = rootDirectory + File.separator + dataFileFolder;
        File dataFile = new File( fileFolder + File.separator + "data.txt");
        Scanner fileReader = new Scanner(dataFile);
        String fileLine;
        boolean start = false;
        while (fileReader.hasNext()){
            fileLine = fileReader.nextLine();
            // ------ Check if the data base has started scanning ------
            if (fileLine.equals(START_SYMBOL)){
                start = true;
                continue;
            }
            if (!(start)){
                continue;
            }
            if (fileLine.equals(STOP_SYMBOL)){
                break;
            }
            canteenList.add(fillCanteen(fileLine, fileReader));
        }
    }

    /***
     * This function is called right after the canteen name is provided
     * The very next line that the file reads is the store name
     * It will turn call fillStore with that name inserted, when the function fillStore
     * finishes executing, fileRead.nextLine() can either provide a new store name or UP_SYMBOL
     * if the UP_SYMBOL is provided, the function ends and the final Canteen object is returned
     * @param name
     * @param fileSegment
     * @return
     */
    private Canteen fillCanteen(String name, Scanner fileSegment){
        Canteen canteen = new Canteen(name);
        String fileLine = fileSegment.nextLine();
        while (!(fileLine.equals(UP_SYMBOL))){
            canteen.addStore(fillStore(name,fileSegment));
            fileLine = fileSegment.nextLine();
        }
        return canteen;
    }

    /***
     * This function is called right after the store name is provided
     * The very next line in the file should be the first food to be added
     * The function stops when it hits the line of the file that says UP_SYMBOL
     * @param name name of the store
     * @param fileSegment the Scanner object used for the init() function
     * @return the completed store with all the food loaded
     */
    private Store fillStore(String name, Scanner fileSegment){
        Store store = new Store(name);
        Food food;
        String fileLine = fileSegment.nextLine();
        String[] fileData = fileLine.split(DATA_FILE_SEPERATOR);
        while (!(fileLine.equals(UP_SYMBOL))){
            food = new Food(fileData[0], Integer.parseInt(fileData[1]),Integer.parseInt(fileData[2])
            ,Integer.parseInt(fileData[3]),Integer.parseInt(fileData[4]));
            store.addFood(food);
            fileLine = fileSegment.nextLine();
            fileData = fileLine.split(DATA_FILE_SEPERATOR);
        }
        return store;
    }

    /***
     * Debugging function prints out all contents
     */
    public void printAllData(){
        System.out.println("Printing out all data");
        for (Canteen canteen : canteenList){
            System.out.println("Canteeh : " + canteen.getName());
            for (Store store : canteen.getStoreList()){
                System.out.println("Store : " + store.getName());
                for (Food food : store.getFoodList()){
                    System.out.println(food);
                }
            }
        }
        System.out.println("Finished Printing out all data");
    }
}
