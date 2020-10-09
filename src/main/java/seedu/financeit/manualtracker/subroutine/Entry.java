package seedu.financeit.manualtracker.subroutine;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.Item;
import seedu.financeit.common.User;
import seedu.financeit.common.exceptions.EmptyParamException;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.manualtracker.Ledger;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.UiManager;

import java.security.InvalidParameterException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Entry extends Item {
    private String description = " ";
    private String category = null;
    private Constants.EntryType entryType = null;
    private double amount = -1;
    private Ledger ledger = null;

    public Entry() {
        super();
        super.requiredParams = new String[]{
            "/time",
            "/cat",
            "/amt",
            "[ -i / -e ]"
        };
        super.setDefaultDateTimeFormat("time");
    }

    public Entry(CommandPacket packet) throws DateTimeException, InvalidParameterException,
            InsufficientParamsException, EmptyParamException {
        this();
        this.handleParams(packet);
    }

    @Override
    public void handleParam(CommandPacket packet, String paramType) throws DateTimeException, InvalidParameterException,
            InsufficientParamsException, EmptyParamException {
        if (paramType.charAt(0) == '/' && packet.getParam(paramType).trim().length() == 0) {
            throw new EmptyParamException(paramType);
        }
        switch (paramType) {
        case "/time":
            String rawTime = packet.getParam(paramType);
            LocalDateTime dateTime = InputParser.parseRawDateTime(rawTime, defaultDateTimeFormat);
            this.setDateTime(dateTime);
            break;
        case "/amt":
            Double amount = Double.parseDouble(packet.getParam(paramType));
            this.setAmount(amount);
            break;
        case "-i":
            this.setEntryType(Constants.EntryType.INC);
            break;
        case "-e":
            this.setEntryType(Constants.EntryType.EXP);
            break;
        case "/desc":
            this.setDescription(packet.getParam(paramType));
            break;
        case "/cat":
            this.setCategory(packet.getParam(paramType));
            break;
        default:
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE, paramType + " is not recognised.");
            break;
        }
    }

    public boolean isValidCategory(String category, Constants.EntryType type) {
        return type == Constants.EntryType.INC
                ? isCategoryInStringArray(Constants.DEFAULT_INC_CAT, category) || User.customCat.contains(category) :
                isCategoryInStringArray(Constants.DEFAULT_EXP_CAT, category) || User.customCat.contains(category);
    }

    public boolean isCategoryInStringArray(String[] arr, String category) {
        return Arrays.stream(arr).anyMatch(category::equals);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setLedger(Ledger ledger) {
        this.ledger = ledger;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setCategory(String category) {
        this.category = Constants.categoryMap.get(category);
    }

    public String getCategory() {
        return this.category;
    }

    public void setEntryType(Constants.EntryType entryType) {
        this.entryType = entryType;
    }

    public Constants.EntryType getEntryType() {
        return this.entryType;
    }

    @Override
    public String getName() {
        return String.format("Entry %d : [ %s ] [ %s ]", this.getIndex(),
            this.dateTimeManager.getDateFormatted("time"), this.description);
    }

    @Override
    public String toString() {
        return String.format("%s;%s;%s;%s;%s", this.entryType, this.category, this.amount,
            this.getDateTimeFormatted(), this.description);
    }

    @Override
    public boolean equals(Object object) {
        Entry entry = (Entry) object;
        return (this.description.equals(entry.description))
            && (this.category.equals(entry.category))
            && (this.entryType.equals(entry.entryType))
            && (this.dateTime.equals(entry.dateTime))
            && (this.amount == entry.amount);
    }

    @Override
    public boolean isValidItem() {
        return (this.category != null)
            && (this.entryType != null)
            && (super.dateTime != null)
            && (this.amount != -1);
    }
}
