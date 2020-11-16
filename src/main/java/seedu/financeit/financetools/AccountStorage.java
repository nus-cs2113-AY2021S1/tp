//@@author bqxy

package seedu.financeit.financetools;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Common;
import seedu.financeit.common.ParamHandler;
import seedu.financeit.common.exceptions.InfoTextIndexOutOfRangeException;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;
import seedu.financeit.utils.storage.AccountSaver;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.text.DecimalFormat;

/**
 * Represents all operations for Account Storage feature.
 */
public class AccountStorage extends ParamHandler {

    String nameLabel;
    String otherLabel;
    double interestRate;
    double cashbackRate;
    double monthlyCap;
    int deleteIndex;
    boolean activateDelete = false;

    /**
     * Constructor for AccountStorage object.
     */
    public AccountStorage() {
        super();
    }

    /**
     * Handles parameters inputted by user.
     *
     * @param packet each packet contains different inputs from user.
     * @throws InsufficientParamsException if there are missing params.
     */
    public void handlePacket(CommandPacket packet) throws InsufficientParamsException {
        try {
            this.handleParams(packet);
        } catch (ItemNotFoundException exception) {
            // Fall-through
        }
    }

    /**
     * Format text from data inputted by user to be stored into txt file.
     *
     * @param nameLabel description of account name.
     * @param interestRate value of interest rate.
     * @param cashbackRate value of cashback rate.
     * @param monthlyCap value of monthly cap for cashback.
     * @param otherLabel any other notes related to account.
     * @return formatted text to be stored.
     */
    public String formatText(String nameLabel, double interestRate, double cashbackRate, double monthlyCap,
                             String otherLabel) {

        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(8);

        if (nameLabel == null) {
            nameLabel = "BLANK_NAME";
        }

        if (otherLabel == null) {
            otherLabel = "BLANK_NOTES";
        }

        String nameInfo = "Name: " + nameLabel + "\n";
        String interestRateInfo = "Interest: " + df.format(interestRate) + "%\n";
        String cashbackRateInfo = "Cashback: " + df.format(cashbackRate) + "%\n";
        String monthlyCapInfo = "Cashback Cap: $" + df.format(monthlyCap) + "\n";
        String otherInfo = "Notes: " + otherLabel + "\n";
        String textToAdd = nameInfo + interestRateInfo + cashbackRateInfo + monthlyCapInfo + otherInfo;

        return textToAdd;
    }

    /**
     * Handles information updates for account and deletion of account.
     *
     * @param filePath file path of txt file.
     * @param infoText arraylist of account data.
     * @throws InfoTextIndexOutOfRangeException if user input account number is not available.
     */
    public void handleInfoStorage(String filePath, ArrayList<String> infoText) throws InfoTextIndexOutOfRangeException {
        assert this.interestRate >= 0 : "Interest rate should not be a negative number";
        assert this.cashbackRate >= 0 : "Cashback rate should not be a negative number";
        assert this.monthlyCap >= 0 : "Monthly cap for cashback should not be a negative number";

        if (activateDelete == true) {
            if (this.deleteIndex > 0 && this.deleteIndex <= infoText.size()) {
                infoText.remove(this.deleteIndex - 1);
            } else {
                throw new InfoTextIndexOutOfRangeException();
            }
            try {
                AccountSaver.updateFile(infoText, filePath);
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
            return;
        }

        String textToAdd = formatText(this.nameLabel, this.interestRate, this.cashbackRate, this.monthlyCap,
                this.otherLabel);
        infoText.add(textToAdd);
        try {
            AccountSaver.updateFile(infoText, filePath);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles user inputted param.
     *
     * @param packet    input CommandPacket obtained from parsing user input.
     * @param paramType paramType of param that is currently being validated and processed.
     * @throws ParseFailParamException if param does not fulfil conditions for a proper input param.
     */
    @Override
    public void handleSingleParam(CommandPacket packet, String paramType) throws ParseFailParamException {
        switch (paramType) {
        case "/n":
            this.nameLabel = packet.getParam(paramType);
            break;
        case "/ir":
            this.interestRate = ParamChecker.getInstance().checkAndReturnDoubleSigned(paramType);
            break;
        case "/r":
            this.cashbackRate = ParamChecker.getInstance().checkAndReturnDoubleSigned(paramType);
            break;
        case "/c":
            this.monthlyCap = ParamChecker.getInstance().checkAndReturnDoubleSigned(paramType);
            break;
        case "/rm":
            this.deleteIndex = ParamChecker.getInstance().checkAndReturnIntSigned(paramType);
            this.activateDelete = true;
            break;
        case "/o":
            this.otherLabel = packet.getParam(paramType);
            break;
        default:
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                ParamChecker.getInstance().getUnrecognizedParamMessage(paramType));
            break;
        }
    }
}