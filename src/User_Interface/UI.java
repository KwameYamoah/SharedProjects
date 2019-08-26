package User_Interface;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class UI extends JPanel {

    public UI(){
        FlowLayout layout = new FlowLayout();
        layout.setVgap(0);
        setLayout(layout);


        JTextField search_field = new JTextField(20);
        JButton search_button = new JButton("Search");
        JPanel north = new JPanel();
        JPanel south = new JPanel();

        add(north,BorderLayout.NORTH);
        north.add(search_field);
        north.add(search_button);
        north.setPreferredSize(new Dimension(400,50));

        south.setPreferredSize(new Dimension(400,350));
        add(south,BorderLayout.SOUTH);
        south.setBorder(BorderFactory.createLineBorder(Color.black));

    }



    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400,400);
    }
}
