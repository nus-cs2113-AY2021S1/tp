package seedu.financeit.manualtracker.subroutine;

import seedu.financeit.utils.Constants;
import seedu.financeit.utils.DateTimeManager;
import seedu.financeit.utils.InputParser;
import seedu.financeit.utils.User;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Entry {
    private String description = " ";
    private String category = " ";
    private Constants.EntryType entryType;
    LocalDateTime dateTime;
    DateTimeManager dateTimeManager;
    private boolean isAuto;
    String defaultDateTimeFormat = "time";

    public Entry() {
    }

    public Entry(String description, String category, Constants.EntryType entryType, LocalDateTime dateTime, boolean isAuto) {
        this.description = description;
        this.category = category;
        this.entryType = entryType;
        this.dateTime = dateTime;
        this.isAuto = isAuto;
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

    public void setCategory(String category, Constants.EntryType type) {
        if (isValidCategory(category, type)) {
            this.category = Constants.categoryMap.get(category);
        }
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
        this.dateTime = LocalDateTime.parse(InputParser.parseRawDateTime(rawTime, "time"));
        this.dateTimeManager = new DateTimeManager(dateTime);
    }

    public void setIsAuto(boolean isAuto) {
        this.isAuto = isAuto;
    }

    @Override
    public String toString(){
        return String.format("%s;%s;%s;%s", this.entryType, this.category, this.getTime(), this.description);
    }
}
