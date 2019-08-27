package Program;

import User_Interface.UI;

import javax.swing.*;
import java.awt.geom.RoundRectangle2D;

public class Main2 {
    public static void main(String[] args) {
        // UI
        JFrame frame = new JFrame();
        frame.setTitle("File Searcher");    //even thought frame title bar is removed, the name of program would still show when you alt-tab

        frame.add(new UI());                //adds panel to frame
        frame.setUndecorated(true);        //removes frame's title bar
        frame.pack();                       //fits size to components
        frame.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 20, 20)); //creates rounded edges for modern feel
        frame.setLocationRelativeTo(null);      //centers frame
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  //still necessary so alt-f4 correctly closes program
    }
}
