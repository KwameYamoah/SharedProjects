package Program;

import User_Interface.UI;

import javax.swing.*;

public class Main2 {
    public static void main(String[] args) {
        // UI
        JFrame frame = new JFrame();
        frame.add(new UI());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
