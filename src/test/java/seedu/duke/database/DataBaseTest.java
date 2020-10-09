package seedu.duke.database;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class DataBaseTest {
    public static void main(String[] args){
        DataBase database = new DataBase();
        try {
            database.init();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        database.printAllData();
    }
}