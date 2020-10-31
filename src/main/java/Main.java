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
        List<Canteen> canteens = initialize();
        greet(); // call greet() method to greet
        input=sc.nextLine();
        while(!input.equals("bye")) { //if input is not "bye"
                /** print the list of tasks*/
                if (input.equals("list")) {
                    printOrder();
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
                else if (input.startsWith("order")) {
                    order(canteens);}

                else{ //dealing with undefined type of input
                        System.out.println("____________________________________________________________\n");
                        System.out.println("  OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                        System.out.println("____________________________________________________________\n");
                    }
                    /*exception handling of wrong input*/

            input=sc.nextLine();// get next input statement

            }
        }




    public static List<Canteen> initialize() {
        Dish porkChop = new Dish("Pork Chop",4);
        Dish fishNChip = new Dish("Fish & Chip",4);
        Dish chickenCutLet = new Dish("Chicken Cutlet",3.5);
        Stall westernStall = new Stall("Western Stall", 1,"Hall of Residence 1, 21 Nanyang Cir, Singapore 639778",900,1800, Arrays.asList(1,2,3,5,6,7),Arrays.asList(porkChop,fishNChip,chickenCutLet),1);
        Dish espresso = new Dish("Espresso",5);
        Dish caramelFrappuccino = new Dish("Caramel Frappuccino",7.5);
        Dish signatureHotChocolateckenCutLet = new Dish("Signature Hot Chocolate",6);
        Stall starbucks = new Stall("Starbucks", 2,"Hall of Residence 1, 21 Nanyang Cir, Singapore 639778",900,1830, Arrays.asList(2,3,4,5,6,7),Arrays.asList(espresso,caramelFrappuccino,signatureHotChocolateckenCutLet),1);
        Dish sweetNSourPorkRice = new Dish("Sweet & Sour Pork Rice",4.5);
        Dish curryChickenRice = new Dish("Curry Chicken Rice",3.5);
        Dish friedRiceWithBeef = new Dish("Fried Rice with Beef",5);
        Stall claypotRice = new Stall("ClaypotRice", 3,"Hall of Residence 1, 21 Nanyang Cir, Singapore 639778",800,2000, Arrays.asList(3,4,5,6,7),Arrays.asList(sweetNSourPorkRice,curryChickenRice,friedRiceWithBeef),1);
        Canteen canteen1 = new Canteen("Canteen 1", Arrays.asList(westernStall,starbucks,claypotRice),Arrays.asList(900,900,800),Arrays.asList(1800,1830,2000));

        //KoreanStall
        Dish kimchiFriedRice = new Dish("Kimchi Fried Rice", 3.50 );
        Dish spicyChickenSet = new Dish("Spicy Chicken Set", 5.00);
        Dish kimchiRamen = new Dish("Kimchi Ramen", 4.00);
        Stall koreanStall = new Stall("KoreanStall", 4, "35 Students Walk", 830, 1730,
                Arrays.asList(1,2,3,4,7),Arrays.asList(kimchiFriedRice, spicyChickenSet, kimchiRamen), 2);


        //PizzaHut
        Dish pepperoni = new Dish("Pepperoni", 8.50);
        Dish hawaiian = new Dish("Hawaiian", 9.50);
        Dish aglioOlio = new Dish("Aglio Olio",10.50);
        Stall pizzaHut = new Stall("PizzaHut",5, "35 Students Walk", 900, 1800,
                Arrays.asList(1,2,4,5,6), Arrays.asList(pepperoni, hawaiian, aglioOlio),2);

        //MacDonalds
        Dish mcChicken = new Dish("Mc Chicken", 2);
        Dish mcNuggets = new Dish("Mc Nuggets", 4);
        Dish applePie = new Dish("Apple Pie", 2);
        Stall macDonalds = new Stall("MacDonalds",6, "35 Students Walk", 930, 1830,
                Arrays.asList(1,2,3,4,5,7), Arrays.asList(mcChicken, mcNuggets, applePie),2);

        //Canteen2
        Canteen canteen2 =new Canteen("Canteen 2",Arrays.asList(koreanStall,pizzaHut,macDonalds),
                Arrays.asList(830,900,930), Arrays.asList(1730,1800,1830));

        List<Canteen> canteens = new ArrayList<Canteen>();
        canteens.add(canteen1);
        canteens.add(canteen2);
        return canteens;
    }

    public static void order(List<Canteen> canteens){
        System.out.println("How many people do you have?");
        int numOfPeople = sc.nextInt();

        for (int i = 1; i <= numOfPeople; i++) {

            System.out.println("Please enter your name:");
            sc.nextLine();
            String customerName = sc.nextLine();
            System.out.println("Please enter the day of the week:");
            int dayOfWeek = sc.nextInt();
            System.out.println("When will you arrive:");
            int arriveTime = sc.nextInt();
            Customer customer = new Customer(customerName, i,arriveTime,dayOfWeek);



            System.out.println("Dear " + customerName + ",");
            System.out.println("Please choose a canteen from the list:");
            List<Canteen> openCanteens = customer.checkOpenCanteens(canteens);//list of canteens
            int j = 0;
            for(Canteen canteen:openCanteens) {
                j++;
                System.out.println(j +". " + canteen);
            }
            System.out.println("Enter the number in front to choose:");
            int canteenIdChoosed = sc.nextInt();
            Canteen canteenChoosed = openCanteens.get(canteenIdChoosed - 1);

            System.out.println("The avaliable stalls in " + canteenChoosed + " are:");

            List<Stall> openStall = customer.checkOpenStalls(canteenChoosed);
            j = 0;
            for (Stall stall : openStall) {
                j++;
                System.out.println(j +". " + stall);
            }
            System.out.println("Please choose a stall:");
            int stallIdChoosed = sc.nextInt();
            Stall stallChoosed = openStall.get(stallIdChoosed - 1);

            System.out.println(stallChoosed + " provides following dishes:");
            j = 0;
            List<Dish> dishinStall = stallChoosed.getDish();
            for (Dish d : dishinStall) {
                j++;
                System.out.println(j +". " + d);
            }
            System.out.println("How many dishes you want to order please?");
            int numOfDishes = sc.nextInt();
            System.out.println("Please choose what you want:");
            List<Dish> orderedDishes = new ArrayList<>();
            for(int num=0;num<numOfDishes;num++) {
                int dishIdChoosed = sc.nextInt();
                Dish dishChoosed = dishinStall.get(dishIdChoosed - 1);
                orderedDishes.add(dishChoosed);
            }

            System.out.println("Please choose your order type:\n\t1.Dine in.\n\t2.Take away.\n\t3.delevery.");
            int typeChoosed = sc.nextInt();
            String orderType= "Dine in";
            if (typeChoosed == 1) {
                orderType = "Dine in";
            }
            else if (typeChoosed == 2) {
                orderType = "Take away";
            }
            else {
                orderType = "Delevery";
            }


            Order order =customer.order(canteenChoosed,stallChoosed,orderedDishes,orderType);
            Order.add(order);
            System.out.println("Your order created! Thanks.");
            System.out.println(order);
        }
    }

    public static void printOrder() {
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
            if(Order.get(i).getDish().get(j).getDishName().contains(keyword)){
                System.out.println(count +": " + Order.get(i));
                count ++;
            }
        }
        System.out.println("____________________________________________________________\n");
    }
    public static void greet(){
        System.out.println("____________________________________________________________\n");
        System.out.println(" Hello! I'm Duke\n");
        System.out.println(" What can I do for you?\n");
        System.out.println("____________________________________________________________\n");
    }
    /**
     * method to say bye
     * @return void
     */
    public static void bye(){
        System.out.println("____________________________________________________________\n");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________\n");
    }

}
