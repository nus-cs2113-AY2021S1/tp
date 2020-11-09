package editor;

import cheatsheet.CheatSheet;
import exception.EditorException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Component;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Editor extends JFrame implements ActionListener {
    private JTextArea textArea;
    private JLabel footerLabel;
    private String cheatSheetName;
    private String cheatSheetSubject;
    private String originalContent;

    public Editor() {
    }

    public void init() {
        generateEditorUI();
    }

    /**
     * Initializes the editor with the necessary elements.
     */
    private void generateEditorUI() {
        setEditorLayout();
        generateTextArea();
        generateRightPane();
        generateEditorFooter();
        generateEditorHeader();
    }

    /**
     * this method generates the right panel that is used for actions and editing.
     */
    private void generateRightPane() {
        JPanel rightPanel = new JPanel();

        // customizes the buttons panel
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBounds(700,50,100,800);
        rightPanel.setBackground(Color.LIGHT_GRAY);
        addBlackBorder(rightPanel);

        // generates the buttons for the right panel
        generateEditingButtons(rightPanel);
        generateActionsButtons(rightPanel);

        add(rightPanel,BorderLayout.EAST);
    }

    private void generateActionsButtons(JPanel rightPanel) {
        JLabel actionsLabel = new JLabel("  Actions \n",JLabel.CENTER);
        actionsLabel.setBounds(700,800,100,50);
        actionsLabel.setHorizontalAlignment(JLabel.CENTER);

        JButton clearButton = new JButton("Clear All");
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        clearButton.setForeground(Color.blue);
        saveButton.setForeground(Color.green);
        cancelButton.setForeground(Color.red);

        clearButton.addActionListener(this);
        saveButton.addActionListener(this);
        cancelButton.addActionListener(this);

        rightPanel.add(actionsLabel);
        rightPanel.add(clearButton);
        rightPanel.add(saveButton);
        rightPanel.add(cancelButton);
    }

    /**
     * generates the editing buttons for the editor.
     * @param rightPanel creates the button into the right panel.
     */
    private void generateEditingButtons(JPanel rightPanel) {
        JLabel editingLabel = new JLabel("  Edit \n",JLabel.CENTER);
        editingLabel.setHorizontalAlignment(JLabel.CENTER);

        JButton copyButton = new JButton("Copy");
        JButton cutButton = new JButton("Cut");
        JButton pasteButton = new JButton("Paste");

        copyButton.addActionListener(this);
        cutButton.addActionListener(this);
        pasteButton.addActionListener(this);

        rightPanel.add(editingLabel);
        rightPanel.add(copyButton);
        rightPanel.add(cutButton);
        rightPanel.add(pasteButton);
    }

    /**
     * adds black line border to the selected panel.
     * @param panel any panel to be added with a black border.
     */
    private void addBlackBorder(JPanel panel) {
        final Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
        panel.setBorder(blackBorder);
    }

    /**
     * generates the icon on top of the editor.
     */
    private void generateEditorHeader() {
        JPanel topPanel = new JPanel();

        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.Y_AXIS));
        topPanel.setBounds(0,0,800,0);
        topPanel.setSize(800,200);
        topPanel.setBackground(Color.white);

        BufferedImage logoPicture = null;
        try {
            logoPicture = ImageIO.read(new URL("https://i.ibb.co/QCjG7v7/cheatlogs-copy.png"));
            JLabel pictureIcon = new JLabel(new ImageIcon(logoPicture));
            pictureIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
            pictureIcon.setBounds(0,0,800,0);
            topPanel.add(pictureIcon);
        } catch (IOException e) {
            System.out.println("The image cannot be retrieved");
        }

        addBlackBorder(topPanel);
        add(topPanel,BorderLayout.PAGE_START);
    }

    /**
     * method to change the settings of the editor window.
     */
    private void setEditorLayout() {
        setSize(800,600); // sets the editor window size
        setTitle("CheatLogs Editor"); // sets the editor name
        setLayout(new BorderLayout()); // determines the layout of the editor
    }

    /**
     * Generates the editor's text are where the user can input the details of the cheatsheet.
     */
    private void generateTextArea() {
        JPanel textAreaPanel = new JPanel();
        textAreaPanel.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setSelectionColor(Color.GRAY); // sets the color of the text Area
        textArea.setSize(800,600); // sets the size of the text Area

        JScrollPane textAreaScroll = new JScrollPane(textArea);
        textAreaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        textAreaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        textAreaScroll.setViewportView(textArea);

        textAreaPanel.add(textAreaScroll, BorderLayout.CENTER);

        add(textAreaPanel, BorderLayout.CENTER); // adds the text area into the center of the frame
    }

    /**
     * Generates the editor footer to tell the cheatsheet name and subject that is being edited.
     */
    private void generateEditorFooter() {
        JPanel editorFooter = new JPanel();
        editorFooter.setBounds(0,500,800,50);
        footerLabel = new JLabel();
        footerLabel.setText(" ");
        footerLabel.setForeground(Color.white);
        editorFooter.add(footerLabel);
        editorFooter.setBackground(Color.darkGray);
        add(editorFooter,BorderLayout.PAGE_END);
    }

    private String generateEditingInformation() {
        return "You are editing the " + cheatSheetName + " cheatsheet [Subject: " + cheatSheetSubject + "]";
    }

    private void showSaveWarning() {
        footerLabel.setText("You are trying to save a blank file");
        footerLabel.setForeground(Color.red);
    }

    private void showCheatsheetInfo() {
        footerLabel.setText(generateEditingInformation());
        footerLabel.setForeground(Color.white);
    }

    /**
     * generates the editing bar for editing commands of the UI.
     * @param menuBar takes in the menuBar and appends an editingMenu object into the menuBar.
     */
    private void generateEditingBar(JMenuBar menuBar) {
        final JMenu editingMenu = new JMenu("Edit");
        final JMenuItem editItem1 = new JMenuItem("Copy");
        final JMenuItem editItem2 = new JMenuItem("Cut");
        final JMenuItem editItem3 = new JMenuItem("Paste");

        editItem1.addActionListener(this);
        editItem2.addActionListener(this);
        editItem3.addActionListener(this);

        editingMenu.add(editItem1);
        editingMenu.add(editItem2);
        editingMenu.add(editItem3);

        menuBar.add(editingMenu);
    }

    /**
     * generates the actions command options for the UI.
     * @param menuBar takes in the menuBar and appends it with the actionsMenu object.
     */
    private void generateActionsBar(JMenuBar menuBar) {
        final JMenu actionsMenu = new JMenu("Actions");
        final JMenuItem menuItem1 = new JMenuItem("Save");
        final JMenuItem menuItem2 = new JMenuItem("Clear All");
        final JMenuItem menuItem3 = new JMenuItem("Cancel");

        menuItem1.addActionListener(this);
        menuItem2.addActionListener(this);
        menuItem3.addActionListener(this);

        actionsMenu.add(menuItem1);
        actionsMenu.add(menuItem2);
        actionsMenu.add(menuItem3);

        menuBar.add(actionsMenu);
    }

    /**
     * this method checks if the text area of the editor is empty.
     * @return returns true if it is empty.
     */
    private boolean checkIsEditorEmpty() {
        return textArea.getText().isBlank();
    }

    /**
     * this method determines the action when a button is pushed.
     * @param a an action event - invoked by pressing the button.
     */
    public void actionPerformed(ActionEvent a) {
        String action = a.getActionCommand();
        switch (action) {
        case "Save":
            if (checkIsEditorEmpty()) {
                open();
                showSaveWarning();
            } else {
                close();
            }
            break;
        case "Clear All":
            textArea.setText("");
            break;
        case "Cancel":
            revertToOriginal();
            close();
            break;
        case "Copy":
            textArea.copy();
            break;
        case "Cut":
            textArea.cut();
            break;
        case "Paste":
            textArea.paste();
            break;
        default:
            break;
        }
    }

    private void revertToOriginal() {
        textArea.setText(originalContent);
        originalContent = "";
    }

    /**
     * The method sets the content of the textArea and the footer.
     * @param cheatSheet this method extracts the content of the cheatsheet into the editor.
     */
    public void setContent(CheatSheet cheatSheet) {
        cheatSheetName = cheatSheet.getName();
        cheatSheetSubject = cheatSheet.getSubject();
        textArea.setText(cheatSheet.getDetails());
        originalContent = cheatSheet.getDetails();
    }

    /**
     * this method extracts the content of the textArea.
     * @return returns a string that is the content of the text area.
     * @throws EditorException throws an exception if the text editor is empty.
     */
    public String getContent() throws EditorException {
        if (textArea.getText().equals("")) {
            throw new EditorException();
        } else {
            return textArea.getText();
        }
    }

    /**
     * Sets the cheatsheet attributes for the editor footer.
     * @param name takes in the name to be placed in the footer
     * @param subject takes in the cheatsheet subject to be placed in the footer.
     */
    public void setEditingContentAttributes(String name, String subject) {
        cheatSheetName = name;
        cheatSheetSubject = subject;
    }

    /**
     * Opens the text editor, and initializes the textArea.
     */
    public void open() {
        textArea.setText("");
        showCheatsheetInfo();
        setVisible(true);
        setAlwaysOnTop(true);
    }

    /**
     * Waits for the editor to close to terminate the program and return the controls to the editor.
     */
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
