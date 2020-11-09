package com.scrumptious;

import com.github.cliftonlabs.json_simple.JsonException;
import com.scrumptious.command.Command;
import com.scrumptious.logger.ScrumLogger;
import com.scrumptious.model.project.ProjectManager;
import com.scrumptious.parser.ParserManager;
import com.scrumptious.storage.StorageManager;
import com.scrumptious.ui.Ui;

import java.io.IOException;
import java.time.Clock;

public class Scrumptious {
    /**
     * Main entry-point for the com.scrumptious.Scrumptious application.
     */

    private static Clock clock = null;
    
    private static final String DATA_FILENAME = "data.json";

    private static ParserManager parser = new ParserManager();
    private static StorageManager sm;
    private static ProjectManager projectManager = new ProjectManager();

    public static void main(String[] args) {
        new Scrumptious().run();
    }

    /**
     * Runs the program.
     */
    private void run() {
        init();
        runInstructions();
        destroy();
    }

    /**
     * Welcome the user and initialise the local storage.
     */
    private void init() {
        try {
            ScrumLogger.setup();
        } catch (IOException e) {
            e.printStackTrace();
            Ui.showError("Unable to setup logger!");
        }
        try {
            sm = new StorageManager(DATA_FILENAME, projectManager);
        } catch (IOException e) {
            Ui.showError("Unable to create data/ directory, please "
                    + "ensure that we are allowed to create the data/ directory!");
        }
        try {
            sm.load();
        } catch (IOException e) {
            Ui.showError("Unable to load the data file properly, "
                    + "proceeding in empty state.");
        } catch (ClassCastException | NullPointerException | JsonException e) {
            Ui.showError("Data file is corrupted, "
                    + "proceeding in empty state. Old data.json will be cleared when the next save happens.");
        }
        Ui.showWelcomeScreen();
    }

    private void destroy() {
        try {
            sm.save();
        } catch (IOException e) {
            Ui.showError("Unable to save data successfully, your data might be lost.");
        }
        ScrumLogger.destroy();
    }

    /**
     * Get the user input and runs the instructions.
     * Exits when ExitCommand returns "true"
     */
    private static void runInstructions() {
        String input;
        while (!parser.isExit()) {
            input = Ui.getUserCommand();
            Command command = parser.parser(input, projectManager);
            if (command != null) {
                command.execute();
                if (command.shouldSave) {
                    try {
                        sm.save();
                    } catch (IOException e) {
                        Ui.showError("Unable to save data successfully, "
                                + "please check your data/ directory.");
                    }
                }
            }
        }
    }
    
    public static void setClock(Clock clock) {
        Scrumptious.clock = clock;
    }
    
    public static Clock getClock() {
        if (clock == null) {
            return Clock.systemDefaultZone();
        }
        return clock;
    }
}