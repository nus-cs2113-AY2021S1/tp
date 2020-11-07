//import necessary libraries
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;

public class Main {
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
    public static void main(String[] args) {
        Initializer initializer = new Initializer();
        List<Canteen> canteens = initializer.initialize();
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
    }






}