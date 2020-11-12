package zeronote;

import zeronote.userinterface.CliUserInterface;

// @@author neilbaner

public class ZeroNote {

    /**
     * Main entry-point for the teetwelvedashthree.zeronote.ZeroNote application.
     *
     * @param args the user's input.
     */
    public static void main(String[] args) {
        CliUserInterface ui = new CliUserInterface();
        ui.run();
    }
}
