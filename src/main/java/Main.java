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
        Customer customer = getCustomer();
        System.out.println("Please enter your command. (Type help for instruction.)");
        input=sc.nextLine();
        while(!input.equals("bye")) { //if input is not "bye"
            /** print the list of tasks*/
            if (input.equals("list")) {
                printOrder();
            }
            else if (input.equals("help")) {
                help();
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
                order(canteens,customer);
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
            //sc.nextLine();
            input=sc.nextLine();// get next input statement

        }
        bye();
    }

    private static Customer getCustomer() {
        try{
            System.out.println("Please enter your name/day of week/time arrive:");
            String inputMessage = sc.nextLine();
            Customer customer = Parser.parseCustomer(inputMessage);
            return customer;
        } catch(DayOfWeekException e){
            System.out.println("____________________________________________________________\n");
            System.out.println("  OOPS!!! Wrong day of week! Please enter again. :-(\n");
            System.out.println("____________________________________________________________\n");
            return getCustomer();
        }catch(ArriveTimeException e){
            System.out.println("____________________________________________________________\n");
            System.out.println("  OOPS!!! Wrong time! Please enter again. :-(\n");
            System.out.println("____________________________________________________________\n");
            return getCustomer();
        } catch(Exception e){
            System.out.println("____________________________________________________________\n");
            System.out.println("  OOPS!!! Wrong format! Please enter again. :-(\n");
            System.out.println("____________________________________________________________\n");
            return getCustomer();
        }
    }


    public static List<Canteen> initialize() {

        Dish porkChop = new Dish("Pork Chop",4,
                "Sugar, spice, and everything nice.");
        Dish fishNChip = new Dish("Fish & Chip",4,
                "Very crispy, makes me feel the happiness of eating!");
        Dish chickenCutLet = new Dish("Chicken Cutlet",3.5,
                "Not very good tasting, I think the meat is a bit hard to eat.");
        Stall westernStall = new Stall("Western Stall", 1,
                "Hall of Residence 1, 21 Nanyang Cir, Singapore 639778",
                900,1800, Arrays.asList(1,2,3,5,6,7),
                Arrays.asList(porkChop,fishNChip,chickenCutLet),1);

        Dish espresso = new Dish("Espresso",5,
                "The coffee smells good but it is a bit bitter for me.");
        Dish caramelFrappuccino = new Dish("Caramel Frappuccino",7.5,
                "I don't taste any coffee. Extremely sweet");
        Dish signatureHotChocolateckenCutLet = new Dish("Signature Hot Chocolate",6,
                "The cake is accompanied by a slight bitterness from the dark chocolate " +
                "that lingers at the back of your throat. It is super addictive for a chocolate lover like myself.");
        Stall starbucks = new Stall("Starbucks", 2,
                "Hall of Residence 1, 21 Nanyang Cir, Singapore 639778",
                900,1830, Arrays.asList(2,3,4,5,6,7),
                Arrays.asList(espresso,caramelFrappuccino,signatureHotChocolateckenCutLet),1);

        Dish sweetNSourPorkRice = new Dish("Sweet & Sour Pork Rice",4.5,
                "The meat is good taste, but the rice is not very good");
        Dish curryChickenRice = new Dish("Curry Chicken Rice",3.5,
                "The curry is sooooo delicious!!!!");
        Dish friedRiceWithBeef = new Dish("Fried Rice with Beef",5,
                "I think there is too much oil in this dish");
        Stall claypotRice = new Stall("ClaypotRice", 3,
                "Hall of Residence 1, 21 Nanyang Cir, Singapore 639778",
                800,2000, Arrays.asList(3,4,5,6,7),
                Arrays.asList(sweetNSourPorkRice,curryChickenRice,friedRiceWithBeef),1);

        Canteen canteen1 = new Canteen("Canteen 1", Arrays.asList(westernStall,starbucks,claypotRice),
                Arrays.asList(900,900,800,800,800,800,800),Arrays.asList(1800,1830,2000,2000,2000,2000,2000));

        Dish kimchiFriedRice = new Dish("Kimchi Fried Rice", 3.50,
                "As a korean, I find the taste in my home country.");
        Dish spicyChickenSet = new Dish("Spicy Chicken Set", 5.00,
                "The chicken tastes good but it is too spicy for me.");
        Dish kimchiRamen = new Dish("Kimchi Ramen", 4.00,
                "The kimchi is really delicious as this is my first time to eat this.");
        Stall koreanStall = new Stall("KoreanStall", 4, "35 Students Walk",
                830, 1730, Arrays.asList(1,2,3,4),
                Arrays.asList(kimchiFriedRice, spicyChickenSet, kimchiRamen), 2);

        Dish pepperoni = new Dish("Pepperoni", 8.50,
                "This dish made me feel like diving in the sea.");
        Dish hawaiian = new Dish("Hawaiian", 9.50,
                "I love this hawaii pizza. I want to marry it and eat it at the wedding.");
        Dish aglioOlio = new Dish("Aglio Olio",10.50,
                "The preparation of this dish is easy and quick. Pasta is made in many colors, different shapes" +
                        " and is known by many names in a different country.");
        Stall pizzaHut = new Stall("PizzaHut",5, "35 Students Walk", 900, 1800,
                Arrays.asList(1,2,4,5,6), Arrays.asList(pepperoni, hawaiian, aglioOlio),2);

        Dish mcChicken = new Dish("Mc Chicken", 2,
                "The most classic hamburger in the world!");
        Dish mcNuggets = new Dish("Mc Nuggets", 4,
                "This is really my favorite among all fast food.");
        Dish applePie = new Dish("Apple Pie", 2,
                "this one is good but i prefer the other flavor's pie :)");
        Stall macDonalds = new Stall("MacDonalds",6, "35 Students Walk",
                930, 1830, Arrays.asList(2,3,4,7),
                Arrays.asList(mcChicken, mcNuggets, applePie),2);

        Canteen canteen2 =new Canteen("Canteen 2",Arrays.asList(koreanStall,pizzaHut,macDonalds),
                Arrays.asList(830,830,830,830,900,900,930), Arrays.asList(1730,1830,1830,1830,1800,1800,1830));

        Dish beefNoodle = new Dish("Beef Noodle",3,
                "The beef is really good, but I think this dish is a bit salty :(");
        Dish tomatoEggNoodle = new Dish("Tomato Egg Noodle", 2.5,
                "Really tasteful!!! After this, I know the reason why most Chinese like tomato and egg.");
        Dish noodlesWithSoyBeanPaste = new Dish ("Noodles with Soy Bean Paste",3,
                "Although my Chinese friends like this very much, but as an Indian I do not like this.");
        Stall noodles = new Stall("Noodles",7,"20 Nanyang Cres",
                830,1930,Arrays.asList(1,2,3,6),
                Arrays.asList(beefNoodle,tomatoEggNoodle,noodlesWithSoyBeanPaste),3);

        Dish sweetAndSourMeet = new Dish("Sweet&Sour Meet",5,
                "The taste of the sweetness and sour are just right.");
        Dish kungPaoTofu = new Dish("Kung Pao Tofu", 4.5,
                "Really good, worth try, but not understand the name of this dish.");
        Dish boiledFish = new Dish("Boiled Fish", 5.5,
                "First try! Really spicy!!");
        Stall siChuan = new Stall("Si Chuan",8,"24 Nanyang Ave",
                900,1830,Arrays.asList(1,2,3,4,5),
                Arrays.asList(sweetAndSourMeet,kungPaoTofu,boiledFish),3);

        Dish spicyChicken = new Dish("Spicy Chicken",3.5,
                "Very crispy and spicy!");
        Dish saltedEggChicken = new Dish("Salted Egg Chicken",4,
        "Really like this taste of chicken with salted egg.");
        Dish mapoTofu = new Dish("Mapo Tofu",2.5,
                "Heared of this dish before, really good.");
        Stall mixedRice = new Stall("Mixed Rice",9,"32 Nanyang Cres",
                800,1900,Arrays.asList(1,2,5,7),
                Arrays.asList(spicyChicken,saltedEggChicken,mapoTofu),3);

        Canteen canteen3 = new Canteen("Canteen 3",Arrays.asList(noodles,siChuan,mixedRice),
                Arrays.asList(800,800,830,900,800,830,800), Arrays.asList(1830,1830,1830,1830,1830,1930,1900));

//        Stall malay;
//        Stall waffles;
//        Stall drinks;
//        Canteen canteen4;

        List<Canteen> canteens = new ArrayList<Canteen>();
        canteens.add(canteen1);
        canteens.add(canteen2);
        return canteens;
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
        System.out.println("Operating hours for the stall you choosed is: \n");
        System.out.println("Open Time: " + stallChoosed.getOpenTime()/100 + ":00" );
        System.out.println("Closing Time: " + stallChoosed.getCloseTime()/100 + ":"
            + String.format("%02d",stallChoosed.getCloseTime()%100));
        sc.nextLine();
        System.out.println("____________________________________________________________\n");
    }

    public static void order(List<Canteen> canteens,Customer customer){

        Canteen canteenChoosed = getCanteen(canteens, customer);

        Stall stallChoosed = getStall(customer, canteenChoosed);

        List<Dish> orderedDishes = getDishes(stallChoosed);

        Order order = getOrder(customer, canteenChoosed, stallChoosed, orderedDishes);

        System.out.println("Your order created! Thanks.");
        System.out.println(order);
        sc.nextLine();
        System.out.println("____________________________________________________________\n");

    }

    private static Order getOrder(Customer customer, Canteen canteenChoosed, Stall stallChoosed, List<Dish> orderedDishes) {
        try{
            System.out.println("Please choose your order type:\n\t1.Dine in.\n\t2.Take away.\n\t3.delevery.");
            int typeChoosed = sc.nextInt();
            String orderType= "Dine in";
            if (typeChoosed == 1) {
                orderType = "Dine in";
            }
            else if (typeChoosed == 2) {
                orderType = "Take away";
            }
            else if (typeChoosed == 3){
                orderType = "Delivery";
            }else{
                throw new WrongNumberException();
            }
            Order order = customer.order(canteenChoosed, stallChoosed, orderedDishes,orderType);
            Order.add(order);
            return order;
        }catch (WrongNumberException e){
            System.out.println("____________________________________________________________\n");
            System.out.println("  OOPS!!! Wrong number. Please enter correct number. :-(\n");
            System.out.println("____________________________________________________________\n");
            return getOrder(customer,canteenChoosed,stallChoosed,orderedDishes);
        }catch (Exception e){
            sc.nextLine();
            System.out.println("____________________________________________________________\n");
            System.out.println("  OOPS!!! Please enter number. :-(\n");
            System.out.println("____________________________________________________________\n");
            return getOrder(customer,canteenChoosed,stallChoosed,orderedDishes);
        }
    }

    private static List<Dish> getDishes(Stall stallChoosed) {
        try{
            int dishCount;
            System.out.println(stallChoosed + " provides following dishes:");
            dishCount = 0;
            List<Dish> dishinStall = stallChoosed.getDish();
            for (Dish d : dishinStall) {
                dishCount++;
                System.out.println(dishCount +". " + d);
            }
            int numOfDishes = getNumOfDishes(dishCount);
            System.out.println("Please choose what you want:");
            List<Dish> orderedDishes = new ArrayList<>();
            for(int num=0;num<numOfDishes;num++) {
                System.out.println("Please enter the " + (num + 1) + " number.");
                int dishIdChoosed = sc.nextInt();
                if(dishIdChoosed<=0||dishIdChoosed>dishCount){
                    throw new WrongNumberException();
                }
                Dish dishChoosed = dishinStall.get(dishIdChoosed - 1);
                orderedDishes.add(dishChoosed);
            }
            String dummy = sc.nextLine();
            String isComment = getYN();
            if (isComment.equals("y")) {
                checkComment(orderedDishes);
            }
            return orderedDishes;
        }catch(WrongNumberException e){
            System.out.println("____________________________________________________________\n");
            System.out.println("  OOPS!!! Wrong number of dish! Please enter again. :-(\n");
            System.out.println("____________________________________________________________\n");
            return getDishes(stallChoosed);
        }catch(Exception e){
            sc.nextLine();
            System.out.println("____________________________________________________________\n");
            System.out.println("  OOPS!!! Wrong information! Please enter again. :-(\n");
            System.out.println("____________________________________________________________\n");
            return getDishes(stallChoosed);
        }
    }

    private static String getYN() {
        try{
            System.out.println("Do you want to check the comment for this stall? (y/n)");
            String isComment = sc.nextLine();
            if((!isComment.equals("y"))&&(!isComment.equals("n"))){
                throw new YNException();
            }
            return isComment;
        }catch (YNException e){
            System.out.println("____________________________________________________________\n");
            System.out.println("  OOPS!!! Wrong input! Please enter y or n. :-(\n");
            System.out.println("____________________________________________________________\n");
            return getYN();
        }catch (Exception e){
            System.out.println("____________________________________________________________\n");
            System.out.println("  OOPS!!! Wrong input! Please enter y or n. :-(\n");
            System.out.println("____________________________________________________________\n");
            return getYN();
        }
    }

    private static int getNumOfDishes(int count) {
        try{
            System.out.println("How many dishes you want to order please?");
            int numberOfDishes = sc.nextInt();
            if (numberOfDishes<=0||numberOfDishes>count){
                throw new WrongNumberException();
            }
            return numberOfDishes;
        }catch (WrongNumberException e){
            System.out.println("____________________________________________________________\n");
            System.out.println("  OOPS!!! Wrong dishes number! Please enter again. :-(\n");
            System.out.println("____________________________________________________________\n");
            return getNumOfDishes(count);
        }catch (Exception e){
            sc.nextLine();
            System.out.println("____________________________________________________________\n");
            System.out.println("  OOPS!!! Wrong format! Please enter again. :-(\n");
            System.out.println("____________________________________________________________\n");
            return getNumOfDishes(count);
        }
    }

    private static Stall getStall(Customer customer, Canteen canteenChoosed) {
        try{
            System.out.println("The avaliable stalls in " + canteenChoosed + " are:");
            int stallCount;
            List<Stall> openStall = customer.checkOpenStalls(canteenChoosed);
            stallCount = 0;
            for (Stall stall : openStall) {
                stallCount++;
                System.out.println(stallCount + ". " + stall);
            }
            System.out.println("Please choose a stall:");
            int stallIdChoosed = sc.nextInt();
            if(stallIdChoosed<=0||stallIdChoosed>stallCount){
                throw new WrongNumberException();
            }
            Stall stallChoosed = openStall.get(stallIdChoosed - 1);
            return stallChoosed;
        }catch(WrongNumberException e){
            System.out.println("____________________________________________________________\n");
            System.out.println("  OOPS!!! Wrong stall number! Please enter again. :-(\n");
            System.out.println("____________________________________________________________\n");
            return getStall(customer,canteenChoosed);
        }catch(Exception e){
            sc.nextLine();
            System.out.println("____________________________________________________________\n");
            System.out.println("  OOPS!!! Wrong stall number! Please enter again. :-(\n");
            System.out.println("____________________________________________________________\n");
            return getStall(customer,canteenChoosed);
        }
    }


    private static Canteen getCanteen(List<Canteen> canteens, Customer customer) {
        try{
            System.out.println("Dear " + customer.name + ",");
            System.out.println("Please choose a canteen from the list:");
            List<Canteen> openCanteens = customer.checkOpenCanteens(canteens);//list of canteens
            int canteenCount = 0;
            for(Canteen canteen:openCanteens) {
                canteenCount++;
                System.out.println(canteenCount +". " + canteen);
            }
            System.out.println("Enter the number in front to choose:");
            int canteenIdChoosed = sc.nextInt();
            if(canteenIdChoosed<=0||canteenIdChoosed>canteenCount){
                throw new WrongNumberException();
            }
            Canteen canteenChoosed = openCanteens.get(canteenIdChoosed - 1);
            return canteenChoosed;
        }catch(WrongNumberException e){
            System.out.println("____________________________________________________________\n");
            System.out.println("  OOPS!!! Wrong canteen number! Please enter again. :-(\n");
            System.out.println("____________________________________________________________\n");
            return getCanteen(canteens, customer);
        }catch(Exception e){
            sc.nextLine();
            System.out.println("____________________________________________________________\n");
            System.out.println("  OOPS!!! Wrong canteen number! Please enter again. :-(\n");
            System.out.println("____________________________________________________________\n");
            return getCanteen(canteens, customer);
        }
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
    public static void greet(){
        System.out.println("____________________________________________________________\n");
        System.out.println(" Hello! I'm Canteenhelper\n");
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

    public static void help() {
        System.out.println("____________________________________________________________\n");
        System.out.println("Hello! Here is a list of commands you can try:");
        System.out.println("1. Order dish: 'order'");
        System.out.println("2. Delete order: 'delete [order number]'");
        System.out.println("3. Find order: 'find [keyword]'");
        System.out.println("4. List order: 'list'");
        System.out.println("5. Exit program: 'bye' ");
        System.out.println("____________________________________________________________\n");
    }

    public static void checkComment(List<Dish> dCs) {
        for (int i = 0; i < dCs.size(); i++) {
            System.out.println(dCs.get(i).getComment());
        }
    }


}