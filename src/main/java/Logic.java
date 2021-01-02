//import necessary libraries

import exception.NoCanteenException;
import exception.NoStallException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main logic of the whole program
 */
public class Logic {
    public static String input;
    //input is for each statement input
    public static Scanner in = new Scanner(System.in);
    protected static List<Canteen> canteens = new ArrayList<>();
    protected static Scanner sc = new Scanner(System.in);
    public static FileInputStream inputFile;
    public static ArrayList<Order> Order = new ArrayList<Order>();

    /**
     * main method
     * @param args
     */
    public static void main(String[] args) throws IOException, InvalidFormatException {
        Initializer initializer = new Initializer();
        List<Canteen> canteens = initializer.initialize();
        main(canteens);
    }

    private static List<Order> readOrders(List<Canteen> canteens) throws IOException {
        try{
            File f = new File("Order Record.xls");
            FileInputStream fis=new FileInputStream(f);
            HSSFWorkbook w=new HSSFWorkbook(fis);
            HSSFSheet sheet=w.getSheetAt(0);
            DataFormatter dataFormatter = new DataFormatter();
            ArrayList<Order> orders = new ArrayList<>();
            for (Row row: sheet) {
                ArrayList<String> values = new ArrayList<>();
                for(Cell cell: row) {
                    String cellValue = dataFormatter.formatCellValue(cell);
                    values.add(cellValue);
                }
                if(!values.get(0).equals("UserName")){
                    ArrayList<Dish> orderedDishes;
                    orderedDishes = (ArrayList<Dish>) Parser.parseDish(values.get(5),canteens);
                    Canteen canteenInOrder = null;
                    for(Canteen canteen:canteens){
                        if(canteen.getCanteenName().equals(values.get(3))){
                            canteenInOrder = canteen;
                            break;
                        }
                    }
                    Stall stallInOrder = null;
                    for(Canteen canteen:canteens){
                        for(Stall stall:canteen.getStallList()){
                            if(stall.getStall_name().equals(values.get(4))){
                                stallInOrder = stall;
                                break;
                            }
                        }
                    }
                    switch (values.get(6)){
                        case "Take Away":
                            orders.add(new takeAwayOrder(canteenInOrder,stallInOrder,orderedDishes,new Customer(values.get(0),Integer.parseInt(values.get(7)),Integer.parseInt(values.get(2)),Integer.parseInt(values.get(1))),true));
                            break;
                        case "Dine In":
                            orders.add(new dineInOrder(canteenInOrder,stallInOrder,orderedDishes,new Customer(values.get(0),Integer.parseInt(values.get(7)),Integer.parseInt(values.get(2)),Integer.parseInt(values.get(1))),true));
                            break;
                        case "Delivery":
                            orders.add(new deliveryOrder(canteenInOrder,stallInOrder,orderedDishes,new Customer(values.get(0),Integer.parseInt(values.get(7)),Integer.parseInt(values.get(2)),Integer.parseInt(values.get(1))),true));
                            break;
                    }
                }
            }
            f.delete();
            return orders;
        }catch(Exception e){
            //declare file name to be create
            String filename = "Order Record.xls";
//creating an instance of HSSFWorkbook class
            HSSFWorkbook workbook = new HSSFWorkbook();
//invoking creatSheet() method and passing the name of the sheet to be created
            HSSFSheet sheet = workbook.createSheet("0");
//creating the 0th row using the createRow() method
            HSSFRow rowhead = sheet.createRow((short)0);
//creating cell by using the createCell() method and setting the values to the cell by using the setCellValue() method
            rowhead.createCell(0).setCellValue("UserName");
            rowhead.createCell(1).setCellValue("Day Of Week");
            rowhead.createCell(2).setCellValue("Arrival Time");
            rowhead.createCell(3).setCellValue("Canteen");
            rowhead.createCell(4).setCellValue("Stall");
            rowhead.createCell(5).setCellValue("Dishes");
            rowhead.createCell(6).setCellValue("Order Type");
            rowhead.createCell(7).setCellValue("ID");
            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
//closing the Stream
            fileOut.close();
//closing the workbook
            workbook.close();
//prints the message on the console
            System.out.println("Excel file has been generated successfully.");
            return new ArrayList<Order>();
        }
    }

    private static void main(List<Canteen> canteens) {
        try{
            Order.addAll(readOrders(canteens));
            UI.greet();
            // call greet() method to greet
            Customer customer = UI.getCustomer(sc);
            System.out.println("Please enter your command. (Type help for instruction.)");
            input=sc.nextLine();
            while(!input.equals("bye")) {
                //if input is not "bye"
                /** print the list of tasks*/
                if (input.equals("list")) {
                    UI.printOrder(input, Order);
                }
                else if (input.equals("help")) {
                    UI.help();
                }
                else if (input.equals("checkcanteen")) {
                    UI.checkCanteenOperatingTime(canteens,customer,sc);
                }
                else if (input.equals("checkstall")) {
                    UI.checkStallOperatingTime(canteens,customer,sc);
                }
                /** mark one task as done */
                /** delete one task */
                else if (input.startsWith("delete")) {
                    UI.deleteOrder(input,Order);
                }
                /** to find tasks containing a certain keyword*/
                else if (input.startsWith("find")) {
                    UI.findDishinOrder(input, Order);
                }
                /** user decides to make orders*/
                else if (input.startsWith("order"))
                {
                    UI.order(canteens,customer,sc,Order);
                }
                else if (input.startsWith("change"))
                {
                    UI.changeOrder(customer,input,Order);
                }
                else if (input.startsWith("search"))
                {
                    UI.searchKeyword(input,canteens);
                }

                else{
                    //dealing with undefined type of input
                    System.out.println("____________________________________________________________\n");
                    System.out.println("  OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                    System.out.println("____________________________________________________________\n");
                }
                /*exception handling of wrong input*/
                input=sc.nextLine();
                // get next input statement

            }
            UI.bye();
            print();

        }catch(NoStallException e){
            sc.nextLine();
            System.out.println("____________________________________________________________\n");
            System.out.println("  OOPS!!! No stall opens at that time. Please change your time. :-(\n");
            System.out.println("____________________________________________________________\n");
            main(canteens);
        }catch (NoCanteenException e){
            System.out.println("____________________________________________________________\n");
            System.out.println("  OOPS!!! No canteen opens at that time. Please change your time. :-(\n");
            System.out.println("____________________________________________________________\n");
            main(canteens);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void print(){
        try
        {
//declare file name to be create
            String filename = "Order Record.xls";
//creating an instance of HSSFWorkbook class
            HSSFWorkbook workbook = new HSSFWorkbook();
//invoking creatSheet() method and passing the name of the sheet to be created
            HSSFSheet sheet = workbook.createSheet("0");
//creating the 0th row using the createRow() method
            HSSFRow rowhead = sheet.createRow((short)0);
//creating cell by using the createCell() method and setting the values to the cell by using the setCellValue() method
            rowhead.createCell(0).setCellValue("UserName");
            rowhead.createCell(1).setCellValue("Day Of Week");
            rowhead.createCell(2).setCellValue("Arrival Time");
            rowhead.createCell(3).setCellValue("Canteen");
            rowhead.createCell(4).setCellValue("Stall");
            rowhead.createCell(5).setCellValue("Dishes");
            rowhead.createCell(6).setCellValue("Order Type");
            rowhead.createCell(7).setCellValue("ID");
            int count = 0;
            for(Order order:Order){
                HSSFRow row = sheet.createRow((short)count);
                row.createCell(0).setCellValue(order.getCustomer().getName());
                row.createCell(1).setCellValue(order.getCustomer().dayOfWeek);
                row.createCell(2).setCellValue(order.getCustomer().getArriveTime());
                row.createCell(3).setCellValue(order.getCanteen().getCanteenName());
                row.createCell(4).setCellValue(order.getStall().getStall_name());
                row.createCell(5).setCellValue(order.getDishes());
                if(order instanceof dineInOrder){
                    row.createCell(6).setCellValue("Dine in");
                }else if(order instanceof takeAwayOrder){
                    row.createCell(6).setCellValue("Take Away");
                }else{
                    row.createCell(6).setCellValue("Delivery");
                }
                row.createCell(7).setCellValue(order.getCustomer().getID());
                count++;
            }
            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
//closing the Stream
            fileOut.close();
//closing the workbook
            workbook.close();
//prints the message on the console
            System.out.println("Excel file has been generated successfully.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}