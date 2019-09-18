package Program;

import User_Interface.UI;

import javax.swing.*;
import java.awt.geom.RoundRectangle2D;
import java.util.logging.Level;


public class Main2 {
    public static JFrame frame;
    public static void main(String[] args) {
        // UI
        java.util.logging.Logger.getLogger("org.apache.pdfbox")
                .setLevel(Level.OFF);
        frame = new JFrame();
        frame.setUndecorated(true);        //removes frame's title bar
        frame.setTitle("File Searcher");    //even thought frame title bar is removed, the name of program would still show when you alt-tab
        frame.add(new UI(frame));                //adds panel to frame
        frame.pack();                       //fits size to components
        frame.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 20, 20)); //creates rounded edges for modern feel
        frame.setLocationRelativeTo(null);      //centers frame
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  //still necessary so alt-f4 correctly closes program
    }
}
