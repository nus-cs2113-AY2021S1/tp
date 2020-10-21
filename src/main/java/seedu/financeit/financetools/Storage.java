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


import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Storage extends ParamHandler {

    String nameLabel;
    String otherLabel;
    double interestRate;
    double cashbackRate;
    double monthlyCap;
    int deleteIndex;
    boolean activateDelete = false;

    public Storage() {
        super();
    }

    public void handlePacket(CommandPacket packet) throws InsufficientParamsException {
        this.paramChecker = new ParamChecker(packet);
        try {
            this.handleParams(packet);
        } catch (ItemNotFoundException exception) {
            // Fall-through
        }
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
                FileStorage.updateFile(infoText, filePath);
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
            return;
        }

        String nameInfo = "Name: " + this.nameLabel + "\n";
        String interestRateInfo = "Interest: " + this.interestRate + "%\n";
        String cashbackRateInfo = "Cashback: " + this.cashbackRate + "%\n";
        String monthlyCapInfo = "Cashback Cap: $" + this.monthlyCap + "\n";
        String otherInfo = "Notes: " + this.otherLabel + "\n";
        String textToAdd = nameInfo + interestRateInfo + cashbackRateInfo + monthlyCapInfo + otherInfo;
        infoText.add(textToAdd);
        try {
            FileStorage.updateFile(infoText, filePath);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void handleSingleParam(CommandPacket packet, String paramType) throws ParseFailParamException,
            ItemNotFoundException {
        switch (paramType) {
        case "/name":
            this.nameLabel = packet.getParam(paramType);
            break;
        case "/ir":
            this.interestRate = paramChecker.checkAndReturnDouble(paramType);
            break;
        case "/cashb":
            this.cashbackRate = paramChecker.checkAndReturnDouble(paramType);
            break;
        case "/cap":
            this.monthlyCap = paramChecker.checkAndReturnDouble(paramType);
            break;
        case "/delete":
            this.deleteIndex = paramChecker.checkAndReturnInt(paramType);
            this.activateDelete = true;
            break;
        case "/note":
            this.otherLabel = packet.getParam(paramType);
            break;
        default:
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    paramChecker.getUnrecognizedParamMessage(paramType));
            break;
        }
    }
}