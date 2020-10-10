package seedu.financeit.common;

import seedu.financeit.common.exceptions.EmptyParamException;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.InvalidCategoryException;
import seedu.financeit.parser.DateTimeManager;

import java.security.InvalidParameterException;
import java.time.DateTimeException;
import java.time.LocalDateTime;

public abstract class Item {
    protected DateTimeManager dateTimeManager;
    protected LocalDateTime dateTime = null;
    protected String defaultDateTimeFormat = null;
    protected String[] requiredParams;
    protected int index = -1;

    public void setDefaultDateTimeFormat(String format) {
        this.defaultDateTimeFormat = format;
    }

    public Item() {
    }

    public Item(LocalDateTime dateTime) {
        this.setDateTime(dateTime);
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }


    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        this.dateTimeManager = new DateTimeManager(dateTime);
    }

    public String getDateTimeFormatted() {
        return this.dateTimeManager.getDateFormatted(this.defaultDateTimeFormat);
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public void handleParams(CommandPacket packet) throws DateTimeException, InvalidParameterException,
            InsufficientParamsException, EmptyParamException, InvalidCategoryException {
        for (String paramType : packet.getParamTypes()) {
            handleParam(packet, paramType);
        }
        if (!isValidItem()) {
            throw new InsufficientParamsException(this.requiredParams);
        }
    }

    public abstract void handleParam(CommandPacket packet, String paramType) throws DateTimeException,
            InvalidParameterException, InsufficientParamsException, EmptyParamException, InvalidCategoryException;

    public abstract boolean isValidItem();

    public abstract String getName();
}
