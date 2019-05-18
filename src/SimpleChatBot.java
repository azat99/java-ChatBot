import javafx.scene.layout.Border;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleChatBot extends JFrame implements ActionListener {

    final String TITLE_OF_PROGRAM = "Chatter: simple chatbot";
    final int START_LOCATION = 200;
    final int WINDOW_WIDTH = 350;
    final int WINDOW_HEIGHT = 450;

    JTextArea dialogue;  // area for dialog
    JCheckBox ai;        // enable/disable AI
    JTextField message;  // field for entering message
    SimpleBot sBot;      // chat-bot class (in bot package)

    public static void main(String[] args){
        new SimpleChatBot();
    }
    SimpleChatBot(){
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(START_LOCATION,START_LOCATION,WINDOW_WIDTH,WINDOW_HEIGHT);

        // area for dialog
        dialogue = new JTextArea();
        dialogue.setLineWrap(true);
        JScrollPane scrollBar = new JScrollPane(dialogue);

        // panel for chekcbox, message field  and button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
        ai = new JCheckBox("AI");
        ai.doClick();
        message = new JTextField();
        message.addActionListener(this);
        JButton enter = new JButton("Enter");
        enter.addActionListener(this);

        // add all elements to the window
        buttonPanel.add(ai);
        buttonPanel.add(message);
        buttonPanel.add(enter);

        add(BorderLayout.SOUTH,buttonPanel);
        add(BorderLayout.CENTER,scrollBar);
        setVisible(true);

        sBot = new SimpleBot();  //creating a bot

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(message.getText().trim().length()>0){
            dialogue.append(message.getText() + "\n");
            dialogue.append(TITLE_OF_PROGRAM.substring(0,9) +
                    sBot.sayInReturn(message.getText(),ai.isSelected()) + "\n");
        }
        message.setText("");
        message.requestFocusInWindow();
    }
}
