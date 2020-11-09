package command.stuba;

import ui.Ui;

import java.util.LinkedList;

public class UiStub extends Ui {
    private LinkedList<String> userInput;

    public UiStub() {
        userInput = new LinkedList<>();
    }

    public void pushUserInput(String userInput) {
        this.userInput.push(userInput);
    }

    public String getUserInput() {
        return userInput.removeLast();
    }

    public void clearUserInput() {
        userInput.clear();
    }
}
