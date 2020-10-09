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

    private final ArrayList<Canteen> canteenList;

    public DataBase(){
        this.canteenList = new ArrayList<>();
    }

    /***
     * Reads a file from the data base and puts it into the DataBase object
     */
    public void init() throws FileNotFoundException {
        File dataFile = new File(System.getProperty("user.dir") + File.separator + "data.txt");
        Scanner fileReader = new Scanner(dataFile);
        String fileLine;
        String[] fileData;
        boolean start = false;
        boolean hasPreviousCanteen = false;
        boolean hasPreviousStore = false;
        DataBaseReadState readState = DataBaseReadState.CANTEEN;
        Canteen currentCanteen = new Canteen("dummy");
        Store currentStore = new Store("dummy");
        Food currentFood;
        while (fileReader.hasNext()){
            fileLine = fileReader.nextLine();
            fileData = fileLine.split("\\|");
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

            // ------ Adjust the state of the data base and end the reading at the lower level -------
            if (fileLine.equals(UP_SYMBOL)){
                if (readState == DataBaseReadState.FOOD){
                    currentCanteen.addStore(currentStore);
                }
                readState = DataBaseReadState.raiseState(readState);
                continue;
            }
            // ----- Start reading the file here ------
            if (readState == DataBaseReadState.CANTEEN){
                if (hasPreviousCanteen){
                    canteenList.add(currentCanteen);
                }
                currentCanteen = new Canteen(fileData[0]);
                readState = DataBaseReadState.lowerState(readState);
                hasPreviousCanteen = true;
                hasPreviousStore = false;
            }
            else if (readState == DataBaseReadState.STORE){
                if (hasPreviousStore){
                    currentCanteen.addStore(currentStore);
                }
                currentStore = new Store(fileData[0]);
                hasPreviousStore = true;
            }
            else{
                currentFood = new Food(fileData[0], Integer.parseInt(fileData[1]),Integer.parseInt(fileData[2]),
                        Integer.parseInt(fileData[3]),Integer.parseInt(fileData[4]));
                currentStore.addFood(currentFood);
            }
        }
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
