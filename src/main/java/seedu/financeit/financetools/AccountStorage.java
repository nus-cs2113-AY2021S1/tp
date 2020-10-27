package seedu.financeit.financetools;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
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

public class AccountStorage extends ParamHandler {

    String nameLabel;
    String otherLabel;
    double interestRate;
    double cashbackRate;
    double monthlyCap;
    int deleteIndex;
    boolean activateDelete = false;

    public AccountStorage() {
        super();
    }

    public void handlePacket(CommandPacket packet) throws InsufficientParamsException {
        try {
            this.handleParams(packet);
        } catch (ItemNotFoundException exception) {
            // Fall-through
        }
    }

    public String formatText(String nameLabel, double interestRate, double cashbackRate, double monthlyCap,
                             String otherLabel) {
        String nameInfo = "Name: " + nameLabel + "\n";
        String interestRateInfo = "Interest: " + interestRate + "%\n";
        String cashbackRateInfo = "Cashback: " + cashbackRate + "%\n";
        String monthlyCapInfo = "Cashback Cap: $" + monthlyCap + "\n";
        String otherInfo = "Notes: " + otherLabel + "\n";
        String textToAdd = nameInfo + interestRateInfo + cashbackRateInfo + monthlyCapInfo + otherInfo;

        return textToAdd;
    }

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

    @Override
    public void handleSingleParam(CommandPacket packet, String paramType) throws ParseFailParamException,
            ItemNotFoundException {
        switch (paramType) {
        case "/n":
            this.nameLabel = packet.getParam(paramType);
            break;
        case "/ir":
            this.interestRate = ParamChecker.getInstance().checkAndReturnDouble(paramType);
            break;
        case "/r":
            this.cashbackRate = ParamChecker.getInstance().checkAndReturnDouble(paramType);
            break;
        case "/c":
            this.monthlyCap = ParamChecker.getInstance().checkAndReturnDouble(paramType);
            break;
        case "/rm":
            this.deleteIndex = ParamChecker.getInstance().checkAndReturnInt(paramType);
            this.activateDelete = true;
            break;
        case "/o":
            this.otherLabel = packet.getParam(paramType);
            break;
        default:
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                ParamChecker.getInstance().getUnrecognizedParamMessage(paramType));
            break;
        }
    }
}