package ui;

import editor.Editor;

public class EditorStub extends Editor {
    boolean isExisting = true;

    public EditorStub() {
        return;
    }

    public void reset() {
        isExisting = true;
    }

    @Override
    public void dispose() {
        isExisting = false;
    }
}
