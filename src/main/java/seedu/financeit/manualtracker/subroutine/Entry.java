package seedu.financeit.manualtracker.subroutine;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.Item;
import seedu.financeit.common.exceptions.EmptyParamException;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.InvalidCategoryException;
import seedu.financeit.manualtracker.Ledger;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.UiManager;

import java.security.InvalidParameterException;
import java.time.DateTimeException;
import java.time.LocalDateTime;

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
        InsufficientParamsException, EmptyParamException, InvalidCategoryException {
        this();
        this.handleParams(packet);
    }

    @Override
    public void handleParam(CommandPacket packet, String paramType) throws DateTimeException, InvalidParameterException,
            EmptyParamException, InvalidCategoryException {
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

    public void setCategory(String category) throws InvalidCategoryException {
        if (!Constants.categoryMap.containsKey(category)) {
            throw new InvalidCategoryException(category);
        }
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
    public void handleParams(CommandPacket packet) throws DateTimeException, InvalidParameterException,
        InsufficientParamsException, EmptyParamException, InvalidCategoryException {
        for (String paramType : packet.getParamTypes()) {
            handleParam(packet, paramType);
        }
        if (!isValidItem()) {
            throw new InsufficientParamsException(this.requiredParams);
        }
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
