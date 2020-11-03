package seedu.financeit.financetools;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Common;
import seedu.financeit.common.exceptions.InfoTextIndexOutOfRangeException;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.ui.TablePrinter;
import seedu.financeit.ui.UiManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents all handlers for FinanceTools features.
 */
public abstract class Handler {
    private static Logger logger = Logger.getLogger("FinanceTools");

    /**
     * Handles Simple Interest Calculator feature.
     *
     * @param packet contains input params by user.
     * @return computation result of Simple Interest Calculator.
     */
    public static double handleSimpleInterest(CommandPacket packet) {
        SimpleInterest tool = new SimpleInterest();
        tool.setRequiredParams(
            "/a",
            "/r"
        );
        try {
            logger.log(Level.INFO, "going to start handling params for Simple Interest Calculator");
            tool.handlePacket(packet);
            logger.log(Level.INFO, "going to start computing results");
            double computeResult = tool.calculateSimpleInterest();
            logger.log(Level.INFO,"end of computation");
            return computeResult;
        } catch (InsufficientParamsException exception) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        }  finally {
            if (!tool.getHasParsedAllRequiredParams()) {
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
            }
        }
        return 0;
    }

    /**
     * Handles Cashback Calculator feature.
     *
     * @param packet contains input params by user.
     * @return computation result of Cashback Calculator.
     */
    public static double handleCashback(CommandPacket packet) {
        Cashback tool = new Cashback();
        tool.setRequiredParams(
            "/a",
            "/r",
            "/c"
        );
        try {
            logger.log(Level.INFO, "going to start handling params for Cashback Calculator");
            tool.handlePacket(packet);
            logger.log(Level.INFO, "going to start computing results");
            double computeResult = tool.calculateCashback();
            logger.log(Level.INFO,"end of computation");
            return computeResult;
        } catch (InsufficientParamsException exception) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        } finally {
            if (!tool.getHasParsedAllRequiredParams()) {
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
            }
        }
        return 0;
    }

    /**
     * Handles Miles Credit Calculator feature.
     *
     * @param packet contains input params by user.
     * @return computation result of Miles Credit Calculator.
     */
    public static double handleMilesCredit(CommandPacket packet) {
        MilesCredit tool = new MilesCredit();
        tool.setRequiredParams(
            "/a",
            "/r"
        );
        try {
            logger.log(Level.INFO, "going to start handling params for Miles Credit Calculator");
            tool.handlePacket(packet);
            logger.log(Level.INFO, "going to start computing results");
            double computeResult = tool.calculateMiles();
            logger.log(Level.INFO,"end of computation");
            return computeResult;
        } catch (InsufficientParamsException exception) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        } finally {
            if (!tool.getHasParsedAllRequiredParams()) {
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
            }
        }
        return 0;
    }

    /**
     * Handles Account Storage feature.
     *
     * @param packet contains input params by user.
     * @param filePath file path that contains all the data in text format.
     * @param infoText arraylist that stores all data in text format.
     */
    public static void handleAccountStorage(CommandPacket packet, String filePath, ArrayList<String> infoText) {
        AccountStorage tool = new AccountStorage();
        tool.setRequiredParams();

        try {
            logger.log(Level.INFO, "going to start handling params for Account Storage");
            tool.handlePacket(packet);
            logger.log(Level.INFO, "going to start saving data into txt file");
            tool.handleInfoStorage(filePath, infoText);
            logger.log(Level.INFO,"end of processing");
        } catch (AssertionError error) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
        } catch (InsufficientParamsException exception) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        } catch (InfoTextIndexOutOfRangeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles Monthly Compound Interest Calculator feature.
     *
     * @param packet contains input params by user.
     * @return computation result of Monthly Compound Interest Calculator.
     */
    public static double handleMonthlyCompoundInterest(CommandPacket packet) {
        MonthlyCompoundInterest tool = new MonthlyCompoundInterest();
        tool.setRequiredParams(
                "/a",
                "/r",
                "/p"
        );
        try {
            logger.log(Level.INFO, "going to start handling params for Monthly Compound Interest Calculator");
            tool.handlePacket(packet);
            logger.log(Level.INFO, "going to start computing results");
            double computeResult = tool.calculateCompoundInterest();
            logger.log(Level.INFO,"end of computation");
            return computeResult;
        } catch (AssertionError error) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
        } catch (InsufficientParamsException exception) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        }
        return 0;
    }

    /**
     * Handles Yearly Compound Interest Calculator feature.
     *
     * @param packet contains input params by user.
     * @return computation result of Yearly Compound Interest Calculator.
     */
    public static double handleYearlyCompoundInterest(CommandPacket packet) {
        YearlyCompoundInterest tool = new YearlyCompoundInterest();
        tool.setRequiredParams(
                "/a",
                "/r",
                "/p"
        );
        try {
            logger.log(Level.INFO, "going to start handling params for Yearly Compound Interest Calculator");
            tool.handlePacket(packet);
            logger.log(Level.INFO, "going to start computing results");
            double computeResult = tool.calculateCompoundInterest();
            logger.log(Level.INFO,"end of computation");
            return computeResult;
        } catch (AssertionError error) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
        } catch (InsufficientParamsException exception) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        }
        return 0;
    }

    /**
     * Prints all commands in FinanceTools.
     */
    public static void printCommandList() {
        TablePrinter.setTitle("List of Commands");
        TablePrinter.addRow("No;Finance Tool             ;Input Format                                         ");
        TablePrinter.addRow("1;Simple Interest Calculator;simple /a {AMOUNT} /r {INTEREST_RATE}");
        TablePrinter.addRow("2;Yearly Compound Interest Calculator;cyearly /a {AMOUNT} /r {INTEREST_RATE}"
                + " /p {YEARS} /d {YEARLY_DEPOSIT}");
        TablePrinter.addRow("3;Monthly Compound Interest Calculator;cmonthly /a {AMOUNT} /r {INTEREST_RATE}"
                + " /p {MONTHS} /d {MONTHLY_DEPOSIT}");
        TablePrinter.addRow("4;Cashback Calculator;cashb /a {AMOUNT} /r {CASHBACK_RATE} /c {CASHBACK_CAP}");
        TablePrinter.addRow("5;Miles Credit Calculator;miles /a {AMOUNT} /r {MILES_RATE}");
        TablePrinter.addRow("6;Account Storage;store /n {ACCOUNT_NAME} /ir {INTEREST_RATE} /r {CASHBACK_RATE}"
                + " /c {CASHBACK_CAP} /o {OTHER_NOTES}");
        TablePrinter.addRow("7;Delete Account;store /rm {ACCOUNT_NO}");
        TablePrinter.addRow("8;Delete All Account Information;clearinfo");
        TablePrinter.addRow("9;Show Account Information;info");
        TablePrinter.addRow("10;Show Command and Calculation History;history");
        TablePrinter.addRow("11;Exit FinanceTools;exit");

        TablePrinter.printList();
    }
}
