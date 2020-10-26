package editor;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class Editor extends JFrame implements ActionListener {
    private JTextArea textArea;

    public Editor() {
        generateEditorUI();
    }

    private void generateEditorUI() {
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

        setJMenuBar(menuBar);
        add(textArea);
        setSize(600, 800);
        setVisible(false);
    }

    public void actionPerformed(ActionEvent a) {
        String action = a.getActionCommand();
        switch (action) {
        case "Save":
            close();
            break;
        case "Clear All":
            textArea.setText("");
            break;
        case "Cancel":
            textArea.setText("");
            close();
            break;
        default:
            break;
        }
    }

    public void setContent(String content) {
        textArea.setText(content);
    }

    public String getContent() {
        return textArea.getText();
    }

    public void open() {
        textArea.setText("");
        setVisible(true);
    }

    public void waitForClose() {
        while (isVisible()) {
            try {
                TimeUnit.MICROSECONDS.sleep(1000);
            } catch (Exception e) {
                assert false;
            }
        }
    }
    
    public void close() {
        setVisible(false);
    }
}
