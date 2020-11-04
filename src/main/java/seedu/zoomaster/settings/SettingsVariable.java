package seedu.zoomaster.settings;

public class SettingsVariable<T> {
    public static final String ON = "on";
    public static final String OFF = "off";

    private String fieldName;
    private String keyword;

    private String description;
    private T[] options;
    private int chosenOptionIndex;

    public SettingsVariable(String fieldName, String keyword, String description, T[] options) {
        this.fieldName = fieldName;
        this.keyword = keyword;
        this.options = options;
        this.description = description;
        chosenOptionIndex = 0;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public T[] getOptions() {
        return options;
    }

    public void setOptions(T[] options) {
        this.options = options;
    }

    public int getChosenOptionIndex() {
        return chosenOptionIndex;
    }

    public void setChosenOptionIndex(int chosenOptionIndex) {
        this.chosenOptionIndex = chosenOptionIndex;
    }

    public String getDescription() {
        return description;
    }

    public String getOptionsList() {
        StringBuilder optionsListString = new StringBuilder();
        for (int i = 0; i < options.length; i++) {
            if (i == chosenOptionIndex) {
                optionsListString.append("<<");
            }

            optionsListString.append(options[i]);

            if (i == chosenOptionIndex) {
                optionsListString.append(">>");
            }

            optionsListString.append(" ");
        }
        return optionsListString.toString();
    }
}
