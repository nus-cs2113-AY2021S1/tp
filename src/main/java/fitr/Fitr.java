package fitr;

import fitr.command.Command;
import fitr.exercise.Recommender;
import fitr.list.ListManager;
import fitr.list.TipList;
import fitr.storage.StorageManager;
import fitr.tip.TipManager;
import fitr.ui.Ui;
import fitr.user.User;
import fitr.parser.Parser;
import org.fusesource.jansi.AnsiConsole;


import java.io.IOException;

import static fitr.common.Commands.COMMAND_BYE;
import static fitr.common.Messages.DASH;

public class Fitr {
    private StorageManager storageManager;
    private ListManager listManager;
    private User user;
    private Recommender recommender;

    public Fitr() {
        try {
            Ui.printGreetingMessage();

            storageManager = new StorageManager();
            user = storageManager.loadUserProfile();
            storageManager.writeUserProfile(user);
            listManager = new ListManager(storageManager, user);

            TipList tipList = new TipList(storageManager.loadTipList());
            TipManager tipOfTheDay = new TipManager(tipList);
            recommender = new Recommender();
            tipOfTheDay.execute();

            Ui.printSuggestQuestion();
        } catch (IOException e) {
            Ui.printCustomError("An error has occurred - the file cannot be opened!");
        }
    }

    public void run() {
        boolean isExit = false;
        while (!isExit) {
            System.out.print("> ");
            String userInput = Ui.read();
            Ui.printCustomMessage(DASH.repeat(136));
            Command c = Parser.parse(userInput);
            c.execute(listManager, storageManager, user, recommender);
            if (!userInput.equals(COMMAND_BYE)) {
                Ui.printCustomMessage(DASH.repeat(136));
            }
            isExit = c.isExit();
        }
        Ui.printExitMessage();
    }

    public static void main(String[] args) {
        AnsiConsole.systemInstall();
        new Fitr().run();
        AnsiConsole.systemUninstall();
    }
}
