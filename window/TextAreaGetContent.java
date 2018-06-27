package window;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import extra.Flag;

public class TextAreaGetContent extends JPanel {
    public TextAreaGetContent() {
        initializeUI();
    }

    private void initializeUI() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(400, 200));

        final JTextArea textArea = new JTextArea();
        
        //
        // Set the contents of the JTextArea.
        //
        String text =EditText.getText();
        		//"The quick brown fox jumps over the lazy dog.";
        textArea.setText(text);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane pane = new JScrollPane(textArea);
        pane.setPreferredSize(new Dimension(400, 200));
        pane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JButton button = new JButton("Get Contents");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //
                // Get the contents of the JTextArea component.
                //
                String contents = textArea.getText();
                EditText.text=contents;
                Flag.setFlag(true);
                System.out.println("contents = " + contents);
                System.out.println(Flag.getFlag());
              
            }
        });

        this.add(pane, BorderLayout.CENTER);
        this.add(button, BorderLayout.SOUTH);
    }

    public static void showFrame() {
    	 JPanel panel = new TextAreaGetContent();
        panel.setOpaque(true);

        JFrame frame = new JFrame("Hi mod! Edit the message and press the button!");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }
}