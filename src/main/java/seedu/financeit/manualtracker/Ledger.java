package seedu.financeit.manualtracker;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.Item;
import seedu.financeit.common.exceptions.EmptyParamException;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.manualtracker.subroutine.EntryList;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.UiManager;

import java.security.InvalidParameterException;
import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Ledger extends Item {
    public EntryList entryList = new EntryList();

    public Ledger() {
        super();
        super.requiredParams = new String[] {
            "/date"
        };
        super.setDefaultDateTimeFormat("date");
    }

    public Ledger(CommandPacket packet) throws DateTimeException, InvalidParameterException,
        InsufficientParamsException, EmptyParamException {
        this();
        this.handleParams(packet);
    }

    @Override
    public String getName() {
        return String.format("Ledger %d : [ %s ]", this.index + 1, this.dateTimeManager.getDateFormatted("date"));
    }

    @Override
    public boolean equals(Object object) {
        Ledger entry = (Ledger) object;
        return (super.dateTime.equals(entry.dateTime));
    }

    @Override
    public String toString() {
        return super.getDateTimeFormatted();
    }

    @Override
    public boolean isValidItem() {
        return (this.dateTime != null);
    }

    @Override
    public void handleParam(CommandPacket packet, String paramType) throws DateTimeException, InvalidParameterException,
            EmptyParamException {
        switch (paramType) {
        case "/date":
            String rawDate = packet.getParam(paramType);
            if (rawDate.trim().length() == 0) {
                throw new EmptyParamException(paramType);
            }
            LocalDateTime dateTime = InputParser.parseRawDateTime(rawDate, this.defaultDateTimeFormat);
            this.setDateTime(dateTime);
            break;

        default:
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE, paramType + " is not recognised.");
            break;
        }
    }
    @Override
    public void handleParams(CommandPacket packet) throws DateTimeException, InvalidParameterException,
        InsufficientParamsException, EmptyParamException {
        for (String paramType : packet.getParamTypes()) {
            handleParam(packet, paramType);
        }
        if (!isValidItem()) {
            throw new InsufficientParamsException(this.requiredParams);
        }
    }
}
