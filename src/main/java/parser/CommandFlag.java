package parser;

public enum CommandFlag {
    NAME("/n"),
    DELETE("/d"),
    INDEX("/i"),
    SUBJECT("/s"),
    SECTIONKEYWORD("/k"),
    COLORSCHEME("/c"),
    HELPMESSAGE("/m");
    private final String flag;

    CommandFlag(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return this.flag;
    }
}
