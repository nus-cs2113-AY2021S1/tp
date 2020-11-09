//@@author TomLBZ

package command;

import constants.Constants;
import lexical.Token;
import lexical.Types;

import java.util.ArrayList;

/**
 * The type Param node.
 */
public class ParamNode {

    /**
     * The Name.
     */
    public String name;
    /**
     * The This data.
     */
    public ParamNode thisData;
    /**
     * The Next data.
     */
    public ParamNode nextData;

    /**
     * Instantiates a new Param node.
     *
     * @param tokens the tokens
     */
    public ParamNode(ArrayList<Token> tokens) {
        ArrayList<Token> tokensClone = new ArrayList<>();
        for (Token token: tokens) {
            tokensClone.add(new Token(token.token, token.string));
        }
        Token head = tokensClone.get(0);
        tokensClone.remove(head);
        int splitIndex = splitIndex(tokensClone, head.token);
        ArrayList<Token> thisTokens = new ArrayList<>();
        ArrayList<Token> nextTokens = new ArrayList<>();
        name = head.string;
        if (splitIndex < 0) {
            nextTokens.addAll(tokensClone);
        } else {
            for (int i = 0; i < tokensClone.size(); i++) {
                if (i <= splitIndex) {
                    thisTokens.add(tokensClone.get(i));
                } else {
                    nextTokens.add(tokensClone.get(i));
                }
            }
        }
        if (thisTokens.size() > 0) {
            thisData = new ParamNode(thisTokens);
        } else {
            thisData = null;
        }
        if (nextTokens.size() > 0) {
            Token endToken = nextTokens.get(0);
            while (endToken.token.equals(Types.END)) {
                nextTokens.remove(endToken);
                if (nextTokens.size() > 0) {
                    endToken = nextTokens.get(0);
                } else {
                    break;
                }
            }
        }
        if (nextTokens.size() > 0) {
            nextData = new ParamNode(nextTokens);
        } else {
            nextData = null;
        }
    }

    /**
     * Instantiates a new Param node.
     *
     * @param name     the name
     * @param thisData the this data
     * @param nextData the next data
     */
    public ParamNode(String name, ParamNode thisData, ParamNode nextData) {
        this.name = name;
        this.thisData = thisData;
        this.nextData = nextData;
    }

    private int splitIndex(ArrayList<Token> tokens, Types type) {
        int i = 0;
        for ( ; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            if (type.equals(Types.STR)) {
                if (!token.token.equals(Types.STR)) {
                    return i - 1;
                }
            } else {
                if (token.token.equals(type) || token.token.equals(Types.END)) {
                    return i - 1;
                }
            }
        }
        return i - 1;
    }

    private String shiftTab(String input) {
        String[] strings = input.split(Constants.WIN_NEWLINE);
        StringBuilder stringBuilder = new StringBuilder();
        for (String string: strings) {
            stringBuilder.append(Constants.TAB).append(string).append(Constants.WIN_NEWLINE);
        }
        return stringBuilder.toString();
    }

    /**
     * To flat string string.
     *
     * @return the string
     */
    public String toFlatString() {
        String myName = name == null ? Constants.ZERO_LENGTH_STRING : name + Constants.SPACE;
        String nextName = thisData == null ? Constants.ZERO_LENGTH_STRING : thisData.toFlatString();
        return (myName + nextName).trim();
    }

    @Override
    public String toString() {
        String data = Constants.ZERO_LENGTH_STRING;
        if (thisData != null) {
            data = " {" + Constants.WIN_NEWLINE + shiftTab(thisData.toString()) + "}";
        }
        String next = nextData == null ? "" : Constants.WIN_NEWLINE + nextData.toString();
        return name + data + next;
    }

    /**
     * Flatten array list.
     *
     * @return the array list
     */
    public ArrayList<ParamNode> flatten() {
        ArrayList<ParamNode> output = new ArrayList<>();
        output.add(new ParamNode(name, thisData, nextData));
        int index = 0;
        ParamNode nextNode = output.get(index).nextData;
        while (nextNode != null) {
            output.get(index).nextData = null;
            output.add(new ParamNode(nextNode.name, nextNode.thisData, nextNode.nextData));
            nextNode = nextNode.nextData;
            index++;
        }
        return output;
    }

    /**
     * Extend array list.
     *
     * @return the array list
     */
    public ArrayList<ParamNode> extend() {
        ArrayList<ParamNode> output = new ArrayList<>();
        output.add(new ParamNode(name, thisData, nextData));
        int index = 0;
        ParamNode nextNode = output.get(index).thisData;
        while (nextNode != null) {
            output.get(index).thisData = null;
            output.add(new ParamNode(nextNode.name, nextNode.thisData, nextNode.nextData));
            nextNode = nextNode.thisData;
            index++;
        }
        return output;
    }

}
