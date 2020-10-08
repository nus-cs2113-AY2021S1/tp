package seedu.financeit.manualtracker.subroutine;

import seedu.financeit.common.Constants;
import seedu.financeit.parser.DateTimeManager;
import seedu.financeit.parser.InputParser;
import seedu.financeit.common.User;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Entry {
    private String description = " ";
    private String category = " ";
    private Constants.EntryType entryType;
    private LocalDateTime dateTime;
    private DateTimeManager dateTimeManager;
    private boolean isAuto = false;
    private String defaultDateTimeFormat = "time";
    private double amount = 0.00;

    public Entry() {
    }

    public Entry(String description, String category, Constants.EntryType entryType, LocalDateTime dateTime, boolean isAuto) {
        this.description = description;
        this.category = category;
        this.entryType = entryType;
        this.dateTime = dateTime;
        this.isAuto = isAuto;
    }

    public LocalDateTime getDateTime(){
        return this.dateTime;
    }

    public String getTime() {
        return this.dateTimeManager.getDateFormatted(this.defaultDateTimeFormat);
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

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setCategory(String category) {
        this.category = Constants.categoryMap.get(category);
    }

    public String getCategory(){
        return this.category;
    }

    public void setEntryType(Constants.EntryType entryType) {
        this.entryType = entryType;
    }

    public Constants.EntryType getEntryType() {
        return this.entryType;
    }

    public void setDateTime(String rawTime){
        this.dateTime = InputParser.parseRawDateTime(rawTime, "time");
        this.dateTimeManager = new DateTimeManager(dateTime);
    }

    public void setIsAuto(boolean isAuto) {
        this.isAuto = isAuto;
    }

    public String isAutoToString() {
        return this.isAuto ? "Auto" : "";
    }

    @Override
    public String toString(){
        return String.format("%s;%s;%s;%s;%s;%s", this.isAutoToString(),this.entryType, this.category, this.amount, this.getTime(), this.description);
    }
}
