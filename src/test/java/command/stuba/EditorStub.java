package command.stuba;

import cheatsheet.CheatSheet;
import editor.Editor;
import exception.EditorException;

public class EditorStub extends Editor {
    CheatSheet content = null;

    @Override
    public void open(){
    }

    @Override
    public void setContent(CheatSheet cheatSheet) {
        content = cheatSheet;
    }

    @Override
    public String getContent() throws EditorException {
        return content.getDetails();
    }

    @Override
    public void waitForClose() {
    }
}
