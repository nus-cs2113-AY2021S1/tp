package seedu.duke.model;


import seedu.duke.exception.DoNotOwnStockException;
import seedu.duke.exception.InsufficientFundException;
import seedu.duke.exception.InsufficientQtyException;

import javax.sound.sampled.Port;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PortfolioManager {
    private Portfolio portfolio;
    private static Logger logger = Logger.getLogger("tp");

    public PortfolioManager() {
        load();
    }

    public void buyStock(String symbol, int quantity, double buyPrice) throws InsufficientFundException {
        logger.log(Level.INFO, "buying stock ...");
        portfolio.buyStock(symbol, quantity, buyPrice);
        save();
    }

    public void sellStock(String symbol, int quantity, double sellPrice)
            throws InsufficientQtyException, DoNotOwnStockException {
        logger.log(Level.INFO, "selling stock ...");
        portfolio.sellStock(symbol, quantity, sellPrice);
        save();
    }

    public double getWalletCurrentAmount() {
        return portfolio.getWalletCurrentAmount();
    }

    public double getWalletInitialAmount() {
        return portfolio.getWalletInitialAmount();
    }

    public ArrayList<Stock> getAllStocks() {
        return portfolio.getAllStocks();
    }

    private void load() {
        try {
            FileInputStream fis = new FileInputStream("data/portfolio.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            portfolio = (Portfolio) ois.readObject();
            ois.close();
            fis.close();
        } catch(FileNotFoundException e) {
            portfolio = new Portfolio();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch(InvalidClassException e) {
            portfolio = new Portfolio();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void save() {
        try {
            FileOutputStream fos = new FileOutputStream("data/portfolio.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(portfolio);
            oos.close();
            fos.close();
            System.out.println("Serialization Done!!");
        } catch(IOException e) {
            System.out.println(e);
        }
    }

}
