import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String args[]) throws IOException, InvalidFormatException {
        FileInputStream fis=new FileInputStream(new File("Canteen Dataset.xls"));
        HSSFWorkbook wb=new HSSFWorkbook(fis);
        HSSFSheet sheet=wb.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();
        ArrayList<Dish> dishes = new ArrayList<>();
        for (Row row: sheet) {
            ArrayList<String> values = new ArrayList<>();
            loop(dataFormatter, row, values);
            if(!values.get(0).equals("Dish.No.")){
//                new Dish(values.get(1),Integer.parseInt(values.get(2)),values.get(3),values.get(4),values.get(5),values.get(6),Integer.parseInt(values.get(7)),Integer.parseInt(values.get(8)),Parser.parseList(values.get(9)),Integer.parseInt(values.get(10)),values.get(11),Integer.parseInt(values.get(12)));
                dishes.add(new Dish(values.get(1),Double.parseDouble(values.get(2)),values.get(3),values.get(4),values.get(5),values.get(6),Integer.parseInt(values.get(7)),Integer.parseInt(values.get(8)),Parser.parseList(values.get(9)),Integer.parseInt(values.get(10)),values.get(11),Integer.parseInt(values.get(12))));
            }
            System.out.println();
        }
        ArrayList<Stall> stalls = new ArrayList<>();
        String temp = "";
        while(dishes.size()!=0){
            if(temp.equals("")){
                temp = dishes.get(0).getStallName();
            }
            ArrayList<Dish> dishes1 = new ArrayList<>();
            for(int i = 0; i < dishes.size(); i++){
                if(dishes.get(i).getStallName().equals(temp)){
                    dishes1.add(dishes.get(i));
                }else{
                    break;
                }
            }
            for(Dish dish:dishes1){
                dishes.remove(dish);
            }
            stalls.add(new Stall(dishes1.get(0).getStallName(),dishes1.get(0).getStallID(),dishes1.get(0).getStallLocation(),(int)dishes1.get(0).getOpenTime(),(int)dishes1.get(0).getCloseTime(),dishes1.get(0).getOpenDayOfWeek(),dishes1,dishes1.get(0).getServingTime(),dishes1.get(0).getCanteenName()));
            temp="";
        }
        ArrayList<Canteen> canteens = new ArrayList<>();
        while(stalls.size()!=0){
            if(temp.equals("")){
                temp = stalls.get(0).getCanteenName();
            }
            ArrayList<Stall> stalls1 = new ArrayList<>();
            for(int i = 0; i < stalls.size(); i++){
                if(stalls.get(i).getCanteenName().equals(temp)){
                    stalls1.add(stalls.get(i));
                }else{
                    break;
                }
            }
            for(Stall stall:stalls1){
                stalls.remove(stall);
            }
            canteens.add(new Canteen(stalls1.get(0).getCanteenName(),stalls1, Arrays.asList(730,730,730,730,730,1100,1000),Arrays.asList(2130,2130,2200,2200,2200,2200,2000)));
            temp="";
        }
    }

    private static void loop(DataFormatter dataFormatter, Row row, ArrayList<String> values) {
        for(Cell cell: row) {
            String cellValue = dataFormatter.formatCellValue(cell);
            values.add(cellValue);
        }
    }
}
