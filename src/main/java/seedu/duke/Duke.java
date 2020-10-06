package seedu.duke;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        quickStart();
        addVoice();
        addAnime();
    }

    private static void addVoice() {
        VoiceActor yoshitsuguMatsuoka = new VoiceActor("Yoshitsugu Matsuoka");
        VoiceActor brycePapenbrook = new VoiceActor("Bryce Papenbrook");
        Character kirito = new Character("Kirito");
        Character somaYukihira = new Character("Soma Yukihira");

        // Many to many relationship
        yoshitsuguMatsuoka.addCharacter(kirito); // Japanese va
        yoshitsuguMatsuoka.addCharacter(somaYukihira);
        brycePapenbrook.addCharacter(kirito); // English va

        kirito.addVoiceActor(yoshitsuguMatsuoka);
        kirito.addVoiceActor(brycePapenbrook);
        somaYukihira.addVoiceActor(yoshitsuguMatsuoka);

        kirito.printVoiceActors();
        yoshitsuguMatsuoka.printCharacters();
    }

    private static void quickStart() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello welcome to AniChan\n" + logo);

        Scanner input = new Scanner(System.in);

        System.out.println("What might your name be?");
        String name = input.nextLine();
        System.out.println("What might your dob be?");
        String dob = input.nextLine();
        System.out.println("What might your gender be?");
        String gender = input.nextLine();

        UserProfile newProfile = new UserProfile(name, dob, gender);
        System.out.println(newProfile);
    }

    //Sample Usage of Anime Class [To Be Deleted]
    private static void addAnime() {
        System.out.println("===Running Sample Anime Class===");
        String[] releaseDate = {"2020", "12", "30"};
        String[] genre = {"Science", "Action", "Dance"};
        String animeName = "Adventures of Adventurers";
        int rating = 65;
        int avgEpisodeLength = 30;
        int totalEpisodes = 24;
        Anime testAnime = new Anime(animeName, releaseDate, rating, genre, avgEpisodeLength, totalEpisodes);
        System.out.println("Release Date in String: " + testAnime.getReleaseDateInString());
        System.out.println("===End of Sample Anime Class===");
    }

}

