//@@author TomLBZ

package lexical;

/**
 * The type Token.
 */
public class Token {

    /**
     * The Token.
     */
    public Types token;
    /**
     * The String.
     */
    public final String string;

    /**
     * Instantiates a new Token.
     *
     * @param t the t
     * @param c the c
     */
    public Token(Types t, char c) {
        this.token = t;
        this.string = Character.toString(c);
    }

    /**
     * Instantiates a new Token.
     *
     * @param t the t
     * @param s the s
     */
    public Token(Types t, String s) {
        this.token = t;
        this.string = s;
    }

    /**
     * Change type.
     *
     * @param newType the new type
     */
    public void changeType(Types newType) {
        this.token = newType;
    }

    public String toString() {
        if (token == Types.STR) {
            return "STR\t<" + string + ">";
        } else if (token == Types.PAR) {
            return "PAR\t<" + string + ">";
        } else if (token == Types.COM) {
            return "COM\t<" + string + ">";
        }
        return token.toString();
    }
}