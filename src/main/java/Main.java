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
    public static String input; //input is for each statement input
    public static Scanner in = new Scanner(System.in);
    protected static List<Canteen> canteens = new ArrayList<>();
    protected static Scanner sc = new Scanner(System.in);
    public static FileInputStream inputFile;
    public static ArrayList<Order> Order = new ArrayList<Order>();

    public static void main(String[] args) {
        Initializer initializer = new Initializer();
        List<Canteen> canteens = initializer.initialize();
        UI.greet(); // call greet() method to greet
        Customer customer = UI.getCustomer(sc);
        System.out.println("Please enter your command. (Type help for instruction.)");
        input=sc.nextLine();
        while(!input.equals("bye")) { //if input is not "bye"
            /** print the list of tasks*/
            if (input.equals("list")) {
                printOrder();
            }
            else if (input.equals("help")) {
                UI.help();
            }
            else if (input.equals("checkcanteen")) {
                checkCanteenOperatingTime(canteens,customer);
            }
            else if (input.equals("checkstall")) {
                checkStallOperatingTime(canteens,customer);
            }
            /** mark one task as done */
            /** delete one task */
            else if (input.startsWith("delete")) {
                deleteOrder();
            }
            /** to find tasks containing a certain keyword*/
            else if (input.startsWith("find")) {
                findDishinOrder();
            }
            /** user decides to make orders*/
            else if (input.startsWith("order"))
            {
                UI.order(canteens,customer,sc,Order);
            }
            else if (input.startsWith("change"))
            {
                changeOrder(customer);
            }

            else{ //dealing with undefined type of input
                System.out.println("____________________________________________________________\n");
                System.out.println("  OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                System.out.println("____________________________________________________________\n");
            }
            /*exception handling of wrong input*/
            input=sc.nextLine();// get next input statement

        }
        UI.bye();
    }






    public static void checkCanteenOperatingTime(List<Canteen> canteens,Customer customer){
        System.out.println("Choose the canteen you want to check:");
        List<Canteen> openCanteens = customer.checkOpenCanteens(canteens);//list of canteens
        int j = 0;
        for(Canteen canteen:openCanteens) {
            j++;
            System.out.println(j +". " + canteen);
        }
        System.out.println("Enter the number in front to choose:");
        int canteenIdChoosed = sc.nextInt();
        Canteen canteenChoosed = openCanteens.get(canteenIdChoosed - 1);
        System.out.println("Operating hours for the canteen you choosed is: \n");
        System.out.println("Open Time: " + canteenChoosed.getOpenTime(customer.getDayOfWeek())/100 + ":00");
        System.out.println("Closing Time: " + canteenChoosed.getCloseTime(customer.getDayOfWeek())/100 +":" +
                canteenChoosed.getCloseTime(customer.getDayOfWeek())%100 );
        System.out.println("____________________________________________________________\n");
        sc.nextLine();
    }


    public static void checkStallOperatingTime(List<Canteen> canteens,Customer customer){
        System.out.println("Input the canteen which your desired stall belongs to: \n");
        List<Canteen> openCanteens = customer.checkOpenCanteens(canteens);//list of canteens
        int j = 0;
        for(Canteen canteen:openCanteens) {
            j++;
            System.out.println(j +". " + canteen);
        }
        System.out.println("Enter the number in front to choose:");
        int canteenIdChoosed = sc.nextInt();
        Canteen canteenChoosed = openCanteens.get(canteenIdChoosed - 1);
        List<Stall> openStall = customer.checkOpenStalls(canteenChoosed);
        j = 0;
        for (Stall stall : openStall) {
            j++;
            System.out.println(j +". " + stall);
        }
        System.out.println("Please choose a stall:");
        int stallIdChoosed = sc.nextInt();
        Stall stallChoosed = openStall.get(stallIdChoosed - 1);
        System.out.println("Operating hours for the stall you chose is: \n");
        System.out.println("Open Time: " + stallChoosed.getOpenTime()/100 + ":00" );
        System.out.println("Closing Time: " + stallChoosed.getCloseTime()/100 + ":"
            + String.format("%02d",stallChoosed.getCloseTime()%100));
        sc.nextLine();
        System.out.println("____________________________________________________________\n");
    }


    public static void changeOrder(Customer customer)
    {
        System.out.println("____________________________________________________________\n");
        System.out.println("Noted. I've changed this order:  \n");
        String[] inputWords = input.split("/"); //split the input message
        String orderNumberchenged = inputWords[1];
        int orderNumberchanged = Integer.parseInt(orderNumberchenged) - 1;
        String changedtype = inputWords[2];
        System.out.println(Order.get(orderNumberchanged) + "\n");
        Canteen can = Order.get(orderNumberchanged).getCanteen();
        Stall stall = Order.get(orderNumberchanged).getStall();
        List<Dish> dishes = Order.get(orderNumberchanged).getDish();
        Order changedOrder = customer.order(can,stall,dishes,changedtype);
        Order.set(orderNumberchanged,changedOrder);

        System.out.println("to \n");
        System.out.println(Order.get(orderNumberchanged) + "\n");
        System.out.println("____________________________________________________________\n");

    }

    public static void printOrder() {
        System.out.println("____________________________________________________________\n");
        for (int i = 0; i < Order.size(); i++) {
            System.out.println("____________________________________________________________\n");
            if (Order.get(i) instanceof dineInOrder) {
                System.out.println((i + 1) + ":" + (dineInOrder) Order.get(i));
            }
            if (Order.get(i) instanceof deliveryOrder) {
                System.out.println((i + 1) + ":" + (deliveryOrder) Order.get(i));
            }
            if (Order.get(i) instanceof takeAwayOrder) {
                System.out.println((i + 1) + ":" + (takeAwayOrder) Order.get(i));
            }
            System.out.println("____________________________________________________________\n");

        }
        System.out.println("____________________________________________________________\n");



    }
    public static void deleteOrder() {
        System.out.println("____________________________________________________________\n");
        System.out.println("Noted. I've removed this order:  ");
        int orderNumberdeleted = Integer.parseInt(input.replaceAll("\\D+", "")) - 1; //find the corresponding index of task to be deleted
        System.out.println(Order.get(orderNumberdeleted));
        Order.remove(orderNumberdeleted); //remove that task from arrayList
        System.out.println("Now you have " + Order.size() + " orders in the list. ");
        System.out.println("____________________________________________________________\n");
    }
    public static void findDishinOrder() {
        System.out.println("____________________________________________________________\n");
        System.out.println("Here are the matching orders in your list:\n");
        String keyword = input.substring(5); // to get the keyword string
        int count = 1;
        /*iterate the task arrayList to find corresponding items*/
        for(int i=0;i< Order.size();i++){
            for(int j=0;j<Order.get(i).getDish().size();j++)
                if(Order.get(i).getDish().get(j).getDishName().compareTo(keyword) == 0){
                    System.out.println(count +": " + Order.get(i));
                    count ++;
                }
        }
        System.out.println("____________________________________________________________\n");
    }

}