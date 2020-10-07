package seedu.duke;

public class Parser implements ParserInterface{

    public void parseCommand(String userInput){
        String[] commandArr = userInput.trim().split(" ", 0);
        UiInterface ui = null;
        switch(commandArr[0]) {
        case "about":
            ui.printAbout();
            break;
        case "help":
            ui.printHelp();
            break;
        case "topics":
            break;
        case "textbook":
            ui.printTextbook();
            break;
        case "quiz":
            QuizInterface quiz = null;
            quiz.startQuiz();
            break;
        case "exit":
            System.exit(0);
            break;
        default:
            break;
        }
    }

    public void parseAnswer(String quizAnswer) {
        switch(quizAnswer.trim()){
        case "1":
        case "2":
        case "3":
        case "4":
        default:
            break;
        }
    }

}
