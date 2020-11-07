import exception.WrongNumberException;
import exception.ArriveTimeException;
import exception.DayOfWeekException;
import exception.YNException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * UI class to deal with interactions with user
 */
public class UI {
    /**
     * when user wants to make an order
     * this method is invoked to create Order objects
     * call methods inside to perform steps
     * for knowing which canteen, stall user goes to
     * how many dishes user orders, dishes user orders
     * @param canteens
     * @param customer
     * @param sc
     * @param Order
     */
    public static void order(List<Canteen> canteens, Customer customer,Scanner sc, ArrayList<Order> Order){

        Canteen canteenChoosed = getCanteen(canteens, customer,sc);

        Stall stallChoosed = getStall(customer, canteenChoosed,sc);

        List<Dish> orderedDishes = getDishes(stallChoosed,sc);

        Order order = getOrder(customer, canteenChoosed, stallChoosed, orderedDishes,sc,Order);

        System.out.println("Your order created! Thanks.");
        System.out.println(order);
        sc.nextLine();
        System.out.println("____________________________________________________________\n");

    }

    /**
     * when a user starts our app,
     * collect his basic information
     * including name,day of week and time arrive
     * user needs input following the format name/day of week/time arrive
     * @param sc
     * @return
     */
    public static Customer getCustomer(Scanner sc) {
        try{
            System.out.println("Please enter your name/day of week/time arrive:");
            String inputMessage = sc.nextLine();
            Customer customer = Parser.parseCustomer(inputMessage);
            return customer;
        } catch(DayOfWeekException e){
            System.out.println("____________________________________________________________\n");
            System.out.println("  OOPS!!! Wrong day of week! Please enter again. :-(\n");
            System.out.println("____________________________________________________________\n");
            return getCustomer(sc);
        }catch(ArriveTimeException e){
            System.out.println("____________________________________________________________\n");
            System.out.println("  OOPS!!! Wrong time! Please enter again. :-(\n");
            System.out.println("____________________________________________________________\n");
            return getCustomer(sc);
        } catch(Exception e){
            System.out.println("____________________________________________________________\n");
            System.out.println("  OOPS!!! Wrong format! Please enter again. :-(\n");
            System.out.println("____________________________________________________________\n");
            return getCustomer(sc);
        }
    }

    /**
     * method to get the order type
     * for creating an order
     * @param customer
     * @param canteenChoosed
     * @param stallChoosed
     * @param orderedDishes
     * @param sc
     * @param Order
     * @return
     */
    private static Order getOrder(Customer customer, Canteen canteenChoosed, Stall stallChoosed, List<Dish> orderedDishes,Scanner sc, ArrayList<Order> Order) {
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
            return getOrder(customer,canteenChoosed,stallChoosed,orderedDishes,sc, Order);
        }catch (Exception e){
            sc.nextLine();
            System.out.println("____________________________________________________________\n");
            System.out.println("  OOPS!!! Please enter number. :-(\n");
            System.out.println("____________________________________________________________\n");
            return getOrder(customer,canteenChoosed,stallChoosed,orderedDishes,sc, Order);
        }
    }

    /**
     * a method to get
     * what dishes the user orders
     * @param stallChoosed
     * @param sc
     * @return
     */
    private static List<Dish> getDishes(Stall stallChoosed, Scanner sc) {
        try{
            int dishCount;
            System.out.println(stallChoosed + " provides following dishes:");
            dishCount = 0;
            List<Dish> dishinStall = stallChoosed.getDish();
            for (Dish d : dishinStall) {
                dishCount++;
                System.out.println(dishCount +". " + d);
            }
            int numOfDishes = getNumOfDishes(dishCount,sc);
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
            String isComment = getYN(sc);
            if (isComment.equals("y")) {
                checkComment(orderedDishes);
            }
            return orderedDishes;
        }catch(WrongNumberException e){
            System.out.println("____________________________________________________________\n");
            System.out.println("  OOPS!!! Wrong number of dish! Please enter again. :-(\n");
            System.out.println("____________________________________________________________\n");
            return getDishes(stallChoosed,sc);
        }catch(Exception e){
            sc.nextLine();
            System.out.println("____________________________________________________________\n");
            System.out.println("  OOPS!!! Wrong information! Please enter again. :-(\n");
            System.out.println("____________________________________________________________\n");
            return getDishes(stallChoosed,sc);
        }
    }

    /**
     * a method to ask user whether he/she wants to know the comments
     * of the selected dish
     * @param sc
     * @return
     */
    private static String getYN(Scanner sc) {
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
            return getYN(sc);
        }catch (Exception e){
            System.out.println("____________________________________________________________\n");
            System.out.println("  OOPS!!! Wrong input! Please enter y or n. :-(\n");
            System.out.println("____________________________________________________________\n");
            return getYN(sc);
        }
    }

    /**
     * a method to know how many dishes user wants to order
     * @param count
     * @param sc
     * @return
     */
    private static int getNumOfDishes(int count,Scanner sc) {
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
            return getNumOfDishes(count,sc);
        }catch (Exception e){
            sc.nextLine();
            System.out.println("____________________________________________________________\n");
            System.out.println("  OOPS!!! Wrong format! Please enter again. :-(\n");
            System.out.println("____________________________________________________________\n");
            return getNumOfDishes(count,sc);
        }
    }

    /**
     * a method to get which stall the user selects
     * @param customer
     * @param canteenChoosed
     * @param sc
     * @return
     */
    private static Stall getStall(Customer customer, Canteen canteenChoosed, Scanner sc) {
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
            return getStall(customer,canteenChoosed,sc);
        }catch(Exception e){
            sc.nextLine();
            System.out.println("____________________________________________________________\n");
            System.out.println("  OOPS!!! Wrong stall number! Please enter again. :-(\n");
            System.out.println("____________________________________________________________\n");
            return getStall(customer,canteenChoosed,sc);
        }
    }

    /**
     * a method to know which canteen the user selects
     * @param canteens
     * @param customer
     * @param sc
     * @return
     */
    private static Canteen getCanteen(List<Canteen> canteens, Customer customer, Scanner sc) {
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
            return getCanteen(canteens, customer,sc);
        }catch(Exception e){
            sc.nextLine();
            System.out.println("____________________________________________________________\n");
            System.out.println("  OOPS!!! Wrong canteen number! Please enter again. :-(\n");
            System.out.println("____________________________________________________________\n");
            return getCanteen(canteens, customer,sc);
        }
    }

    /**
     * a method to greet the user at the start of the program
     */
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

    /**
     * a method to display help information for the user
     * if the user does not know what to do next
     */
    public static void help() {
        System.out.println("____________________________________________________________\n");
        System.out.println("Hello! Here is a list of commands you can try:");
        System.out.println("1. Order dish: 'order'");
        System.out.println("2. Delete order: 'delete [order number]'");
        System.out.println("3. Find order: 'find [keyword]'");
        System.out.println("4. List order: 'list'");
        System.out.println("5. Change order: 'change/[number]/[type]'");
        System.out.println("6. Check Canteen Operating Time: 'checkcanteen'");
        System.out.println("7. Check Stall Operating Time: 'checkstall '");
        System.out.println("8. Exit program: 'bye' ");
        System.out.println("____________________________________________________________\n");
    }

    /**
     * method to check comments
     * based on list of dishes
     * user needs to choose y/n
     * @param dCs
     */
    public static void checkComment(List<Dish> dCs) {
        for (int i = 0; i < dCs.size(); i++) {
            System.out.println(dCs.get(i).getComment());
        }
    }

    /**
     * a method to check the operating time of a canteen
     * prints operating time of user selected canteen
     * user needs to choose the number before canteen list
     * @param canteens
     * @param customer
     * @param sc
     */
    public static void checkCanteenOperatingTime(List<Canteen> canteens,Customer customer,Scanner sc){
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

    /**
     * a method to check the operating time of a stall
     * prints operating time of user selected stall
     * user needs to choose the number before the canteen list
     * and stall list
     * to get the corresponding one
     * @param canteens
     * @param customer
     * @param sc
     */
    public static void checkStallOperatingTime(List<Canteen> canteens,Customer customer,Scanner sc){
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

    /**
     * a method to change order
     * if the user wants to change type of order
     * user needs to input index of order and what order type he/she wants to change to
     * @param customer
     * @param input
     * @param Order
     */
    public static void changeOrder(Customer customer,String input,ArrayList<Order> Order)
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

    /**
     * a method to print all the orders made by the user
     * @param input
     * @param Order
     */
    public static void printOrder(String input,ArrayList<Order> Order) {
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

    /**
     * a method to delete one order
     * if user wants to delete one order from what he had ordered
     * user needs to provide order index to delete
     * @param input
     * @param Order
     */
    public static void deleteOrder(String input,ArrayList<Order> Order) {
        System.out.println("____________________________________________________________\n");
        System.out.println("Noted. I've removed this order:  ");
        int orderNumberdeleted = Integer.parseInt(input.replaceAll("\\D+", "")) - 1; //find the corresponding index of task to be deleted
        System.out.println(Order.get(orderNumberdeleted));
        Order.remove(orderNumberdeleted); //remove that task from arrayList
        System.out.println("Now you have " + Order.size() + " orders in the list. ");
        System.out.println("____________________________________________________________\n");
    }

    /**
     * a method for user to find all the orders containing certain dish
     * user needs to input dish name
     * @param input
     * @param Order
     */
    public static void findDishinOrder(String input,ArrayList<Order> Order) {
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
