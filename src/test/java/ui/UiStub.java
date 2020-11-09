package ui;

import java.util.LinkedList;

class UiStub extends Ui {
    private final LinkedList<String> userInput;
    boolean isExisting = true;

    public void reset() {
        isExisting = true;
    }

    protected UiStub() {
        userInput = new LinkedList<>();
    }

    protected void pushUserInput(String userInput) {
        this.userInput.push(userInput);
    }

    public String getUserInput() {
        return userInput.removeLast();
    }

    protected void clearUserInput() {
        userInput.clear();
    }

    @Override
    public void closeScanner() {
        isExisting = false;
    }
}
