package seedu.duke.category;

public class Category {
    String category;

    public Category(String category) {
        this.category = category;
    }

    public String getCategoryName() {
        return category;
    }

    public void setCategoryName(String category) {
        this.category = category;
    }
}
