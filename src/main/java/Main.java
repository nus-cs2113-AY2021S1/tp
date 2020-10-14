import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;

public class Main {
    protected static List<Dish> dishes = new ArrayList<>();
    protected static List<Stall> stalls = new ArrayList<>();
    protected static List<Canteen> canteens = new ArrayList<>();
    protected static Scanner sc = new Scanner(System.in);
    public static FileInputStream inputFile;

    public static void main(String[] args) {
        
        System.out.println("How many people do you have?");
        int numOfPeople = sc.nextInt();

        for (int i = 1; i <= numOfPeople; i++) {

            System.out.println("Please enter your name:");
            String customerName = sc.nextLine();
            Customer customer = new Customer(customerName);

            System.out.println("When will you arrive:");
            String arriveTime = sc.nextInt();

            Syetem.out.println("Dear " + customerName + ",");
            System.out.println("Please choose a canteen from the list:");
            int j = 0;
            for (Canteen c : canteeens) {
                j++;
                System.out.println(j +". " + c);
            }
            System.out.println("Enter the number in front to choose:");
            int canteenIdChoosed = sc.nextInt();
            Canteen canteenChoosed = canteens.get(canteenIdChoosed - 1);

            System.out.println("The avaliable stalls in " + canteenChoosed + " are:");
            j = 0;
            for (stall s : stalls) {
                j++;
                System.out.println(j +". " + s);
            }
            System.out.println("Please choose a stall:");
            int stallIdChoosed = sc.nextInt();
            Canteen stallChoosed = stalls.get(stallIdChoosed - 1);

            System.out.println(stallChoosed + " provides following dishes:");
            j = 0;
            for (stall d : dishes) {
                j++;
                System.out.println(j +". " + d);
            }
            System.out.println("Please choose what you want, enter 'done' to finish:");
            List<Dish> orderedDishes = new ArrayList<>();
            while (true) {
                String dishIdChoosed = sc.nextLine();
                if (dishIdChoosed.equals("done")) {
                    break;
                }
                Dish dishChoosed = dishes.get(valueOf(dishIdChoosed) - 1);
                orderedDishes.add(dishChoosed);
            }

            System.out.println("Please choose your order type:\n\t1.Dine in.\n\t2.Take away.\n\t3.delevery.");
            int typeChoosed = sc.nextInt();
            if (typeChoosed == 1) {
                String orderType = "Dine in";
            }
            else if (typeChoosed == 2) {
                String orderType = "Take away";
            }
            else {
                String orderType = "Delevery";
            }


            //Order order = new Order(customer, arriveTime, canteenChoosed, stallChoosed, orderedDishes);
            customer.order()

            System.out.println("Your order created! Thanks."); 
        }
    }
}