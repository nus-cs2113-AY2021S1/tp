//@@author TomLBZ

package lexical;

import constants.Constants;

import java.util.ArrayList;

/**
 * The type Lexer.
 */
public class Lexer {

    private String getCommand(String s, int i) {
        int j = i;
        while (j < s.length()) {
            if (Character.isLetter(s.charAt(j))) {
                j++;
            } else {
                break;
            }
        }
        return s.substring(i, j);
    }

    private String getAtom(String s, int i) {
        int j = i;
        while (j < s.length()) {
            char c = s.charAt(j);
            if (c != Constants.CHAR_SPACE && c != Constants.CMD_END) {
                j++;
            } else {
                break;
            }
        }
        return s.substring(i, j);
    }

    /**
     * Analyze array list.
     *
     * @param input the input
     * @return the array list
     */
    public ArrayList<Token> analyze(String input) {
        ArrayList<Token> result = new ArrayList<>();
        String inString = input.trim();
        boolean isCommand = true;
        for (int i = 0; i < inString.length(); ) {
            char c = inString.charAt(i);
            switch (c) {
            case Constants.LPAREN:
                result.add(new Token(Types.LPA, c));
                i++;
                break;
            case Constants.RPAREN:
                result.add(new Token(Types.RPN, c));
                i++;
                break;
            case Constants.CHAR_SPACE:
                i++;
                break;
            case Constants.PARAM: //this case is the same as the next case, thus no break
            case Constants.PARAM_ALIAS:
                i++;
                String command = getCommand(inString, i);
                i += command.length(); //skip away the rest of the commands
                result.add(new Token(Types.PAR, command));
                break;
            case Constants.CMD_END:
                result.add(new Token(Types.END, c));
                i++;
                isCommand = true;
                break;
            default:    //atom or cmd
                String atom = getAtom(inString, i);
                i += atom.length();
                if (isCommand) {
                    result.add(new Token(Types.COM, atom));
                    isCommand = false;
                } else {
                    result.add(new Token(Types.STR, atom));
                }
                break;
            }
        }
        return result;
    }

}