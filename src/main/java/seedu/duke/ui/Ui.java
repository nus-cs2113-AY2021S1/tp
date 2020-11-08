package seedu.duke.ui;

import seedu.duke.model.Stock;
import seedu.duke.model.Transaction;
import seedu.duke.model.TransactionType;
import seedu.duke.parser.Parser;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final String dividerLine = "____________________________________________________________";
    private final String logo = "__________                              ___________                  .___\n"
            + "\\______   \\_____  ______   ___________  \\__    ___/___________     __| _/____\n"
            + " |     ___/\\__  \\ \\____ \\_/ __ \\_  __ \\   |    |  \\_  __ \\__  \\   / __ |/ __ \\\n"
            + " |    |     / __ \\|  |_> >  ___/|  | \\/   |    |   |  | \\// __ \\_/ /_/ \\  ___/\n"
            + " |____|    (____  /   __/ \\___  >__|      |____|   |__|  (____  /\\____ |\\___  >\n"
            + "                \\/|__|        \\/                              \\/      \\/    \\/";

    public void greetUser() {
        print(logo);
        printWithDivider("Welcome to the Command Line Paper Trading App!");
    }

    public void print(String message) {
        System.out.println(message);
    }

    public void printWithDivider(String... messages) {
        print(dividerLine);
        for (String message : messages) {
            print(message);
        }
        print(dividerLine);
    }

    public String getUserInput() {
        print("What would you like to do today?");
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        return userInput;
    }

    public void view(ArrayList<Stock> stocks) {
        print(dividerLine);
        if (stocks.size() == 0) {
            print("You currently have an empty portfolio. Try buying a stock!");
        }
        for (int i = 0; i < stocks.size(); i++) {
            print((i + 1) + ". " + stocks.get(i).toString());
            int totalStocksBought = 0;
            double totalCost = 0;
            for (Transaction t : stocks.get(i).getTransactions()) {
                print("\t" + t.toString());
                if (t.getTransactionType() == TransactionType.BUY) {
                    totalCost += t.getUnitPrice() * t.getQuantity();
                    totalStocksBought += t.getQuantity();
                }
            }

            double totalAsset = stocks.get(i).getLatestPrice() * stocks.get(i).getTotalQuantity();
            double weightedAverageCost = (totalCost / totalStocksBought) * stocks.get(i).getTotalQuantity();
            print("Total holding asset = $" + Parser.parsePrice(totalAsset));
            print("Total cost = $" + Parser.parsePrice(weightedAverageCost));

            if (totalAsset != 0) {
                double earnings = Parser.parsePrice(totalAsset) - Parser.parsePrice(weightedAverageCost);
                print("Total Profit/Loss = $" + Parser.parsePrice(earnings));
            } else {
                print("Total Profit/Loss = error!");
            }
        }
        print(dividerLine);
    }

    public void viewWallet(double currentAmount, double initialAmount, double allStocksPrice) {
        print(dividerLine);
        System.out.println("You currently have $" + String.format("%.02f", currentAmount) + " in your wallet.");
        System.out.println("Current market value of all your equities owned: $"
                + String.format("%.02f", allStocksPrice));
        double difference = (currentAmount + allStocksPrice) - initialAmount;
        if (difference >= 0) {
            System.out.println("Profit of: +$" + String.format("%.02f", difference));
        } else {
            System.out.println("Loss of : -$" + String.format("%.02f", Math.abs(difference)));
        }
        print(dividerLine);
    }

    public void printStocks(ArrayList<Stock> stocks) {
        String[] stockNames = new String[stocks.size()];
        for (int i = 0; i < stocks.size(); i++) {
            stockNames[i] = (i + 1) + ". " + stocks.get(i).toString();
        }
        printWithDivider(stockNames);
    }

}
