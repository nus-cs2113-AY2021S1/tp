package editor;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Editor extends JFrame implements ActionListener {
    JTextArea textArea;
    JFrame editorFrame;

    static String typedText;
    boolean isEditing;

    public Editor() {
        generateEditorUI();
    }

    public Editor(String content) {
        generateEditorUI();
        textArea.setText(content);
    }

    private void generateEditorUI() {
        isEditing = true;
        editorFrame = new JFrame("editor");
        textArea = new JTextArea();

        final JMenuBar menuBar = new JMenuBar();

        final JMenu menu1 = new JMenu("Actions");

        final JMenuItem menuItem1 = new JMenuItem("Save");
        final JMenuItem menuItem2 = new JMenuItem("Clear All");
        final JMenuItem menuItem3 = new JMenuItem("Cancel");


        menuItem1.addActionListener(this);
        menuItem2.addActionListener(this);
        menuItem3.addActionListener(this);

        menu1.add(menuItem1);
        menu1.add(menuItem2);
        menu1.add(menuItem3);

        menuBar.add(menu1);

        editorFrame.setJMenuBar(menuBar);
        editorFrame.setTitle("CheatLogs Text Editor");
        editorFrame.add(textArea);
        editorFrame.setSize(600,800);
        editorFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent a) {
        String action = a.getActionCommand();
        switch (action) {
        case "Save":
            setEditingFinish();
            typedText = textArea.getText();
            editorFrame.dispose();
            break;
        case "Clear All":
            textArea.setText(" ");
            break;
        case "Cancel":
            setEditingFinish();
            textArea.setText(" ");
            editorFrame.dispose();
            break;
        default:
            break;
        }
    }

    private void setEditingFinish() {
        isEditing = false;
    }

    public String getContent() {
        return typedText;
    }

    public boolean isEditing() {
        return isEditing;
    }

    public void showWindow(){
        editorFrame.setVisible(true);
        editorFrame.setAlwaysOnTop(true);
    }

}
