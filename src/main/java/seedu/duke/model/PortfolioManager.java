package seedu.duke.model;


import seedu.duke.exception.DoNotOwnStockException;
import seedu.duke.exception.InsufficientFundException;
import seedu.duke.exception.InsufficientQtyException;
import seedu.duke.exception.NegativeQtyException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PortfolioManager {
    private Portfolio portfolio;
    private static Logger logger = Logger.getLogger("tp");

    public PortfolioManager() {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        load();
    }

    public void buyStock(String symbol, int quantity, double buyPrice)
            throws InsufficientFundException, NegativeQtyException {
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "buying stock ...");
        portfolio.buyStock(symbol, quantity, buyPrice);
        save();
    }

    public void sellStock(String symbol, int quantity, double sellPrice)
            throws InsufficientQtyException, DoNotOwnStockException, NegativeQtyException {
        logger.setLevel(Level.WARNING);
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
        } catch (FileNotFoundException e) {
            portfolio = new Portfolio();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidClassException e) {
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
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
