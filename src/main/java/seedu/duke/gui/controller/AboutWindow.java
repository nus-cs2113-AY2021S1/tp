package seedu.duke.gui.controller;

import javafx.fxml.FXML;

import javax.swing.text.html.ImageView;

public class AboutWindow {

    @FXML
    private ImageView imageView;

    private MainController parentController;
    public void setParentController(MainController mainStage) {
        parentController = mainStage;
    }
}