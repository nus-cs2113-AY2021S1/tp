import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
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
