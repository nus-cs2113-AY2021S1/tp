package seedu.financeit.financetools;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.exceptions.InfoTextIndexOutOfRangeException;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.ui.TablePrinter;
import seedu.financeit.ui.UiManager;

import java.util.ArrayList;

public class Handler {
    public static double handleSimpleInterest(CommandPacket packet) {
        SimpleInterest tool = new SimpleInterest();
        tool.setRequiredParams(
            "/a",
            "/r"
        );
        try {
            tool.handlePacket(packet);
            return (tool.calculateSimpleInterest());
        } catch (InsufficientParamsException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        }  finally {
            if (!tool.getHasParsedAllRequiredParams()) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
            }
        }
        return 0;
    }

    public static double handleCashback(CommandPacket packet) {
        Cashback tool = new Cashback();
        tool.setRequiredParams(
            "/a",
            "/r",
            "/c"
        );
        try {
            tool.handlePacket(packet);
            return (tool.calculateCashback());
        } catch (InsufficientParamsException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        } finally {
            if (!tool.getHasParsedAllRequiredParams()) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
            }
        }
        return 0;
    }

    public static double handleMilesCredit(CommandPacket packet) {
        MilesCredit tool = new MilesCredit();
        tool.setRequiredParams(
            "/a",
            "/r"
        );
        try {
            tool.handlePacket(packet);
            return (tool.calculateMiles());
        } catch (InsufficientParamsException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        } finally {
            if (!tool.getHasParsedAllRequiredParams()) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
            }
        }
        return 0;
    }

    public static void handleAccountStorage(CommandPacket packet, String filePath, ArrayList<String> infoText) {
        AccountStorage tool = new AccountStorage();
        tool.setRequiredParams();

        try {
            tool.handlePacket(packet);
            tool.handleInfoStorage(filePath, infoText);
        } catch (AssertionError error) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
        } catch (InsufficientParamsException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        } catch (InfoTextIndexOutOfRangeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static double handleMonthlyCompoundInterest(CommandPacket packet) {
        MonthlyCompoundInterest tool = new MonthlyCompoundInterest();
        tool.setRequiredParams(
                "/a",
                "/r",
                "/p"
        );
        try {
            tool.handlePacket(packet);
            return (tool.calculateCompoundInterest());
        } catch (AssertionError error) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
        } catch (InsufficientParamsException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        }
        return 0;
    }

    public static double handleYearlyCompoundInterest(CommandPacket packet) {
        YearlyCompoundInterest tool = new YearlyCompoundInterest();
        tool.setRequiredParams(
                "/a",
                "/r",
                "/p"
        );
        try {
            tool.handlePacket(packet);
            return (tool.calculateCompoundInterest());
        } catch (AssertionError error) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
        } catch (InsufficientParamsException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        }
        return 0;
    }

    public static void printCommandList() {
        TablePrinter.setTitle("List of Commands");
        TablePrinter.addRow("No;Finance Tool             ;Input Format                                         ");
        TablePrinter.addRow("1;Simple Interest Calculator;simple /a {AMOUNT} /r {INTEREST_RATE} ");
        TablePrinter.addRow("2;Yearly Compound Interest Calculator;cyearly /a {AMOUNT} /r {INTEREST_RATE}"
                + " /p {YEARS} /d {YEARLY_DEPOSIT} ");
        TablePrinter.addRow("3;Monthly Compound Interest Calculator;cmonthly /a {AMOUNT} /r {INTEREST_RATE}"
                + " /p {MONTHS} /d {MONTHLY_DEPOSIT} ");
        TablePrinter.addRow("4;Cashback Calculator;cashb /a {AMOUNT} /r {CASHBACK_RATE} /c {CASHBACK_CAP}");
        TablePrinter.addRow("5;Miles Credit Calculator;miles /a {AMOUNT} /r {MILES_RATE}");
        TablePrinter.addRow("6;Account Storage;store /n {ACCOUNT_NAME} /ir {INTEREST_RATE} /r {CASHBACK_RATE}"
                + " /c {CASHBACK_CAP} /o {OTHER_NOTES} ");
        TablePrinter.addRow("7;Delete Account;store /rm {ACCOUNT_NO}");
        TablePrinter.addRow("8;Delete All Account Information;clearinfo");
        TablePrinter.addRow("9;Show Account Information;info");
        TablePrinter.addRow("10;Show Command and Calculation History;history");
        TablePrinter.addRow("11;Exit FinanceTools;exit");

        TablePrinter.printList();
    }
}
