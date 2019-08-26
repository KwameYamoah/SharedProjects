package User_Interface;

import HelperClasses.FileSearcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JPanel {
    private JTextField search_field;
    public UI(){
        FlowLayout layout = new FlowLayout();
        layout.setVgap(0);
        setLayout(layout);


        search_field = new JTextField(20);
        JButton search_button = new JButton("Search");

        search_button.addActionListener(new ButtonHandler(this,1));

        JPanel north = new JPanel();
        JPanel south = new JPanel();

        add(north,BorderLayout.NORTH);
        north.add(search_field);
        north.add(search_button);
        north.setPreferredSize(new Dimension(400,40));

        south.setPreferredSize(new Dimension(400,360));
        add(south,BorderLayout.SOUTH);
        south.setBorder(BorderFactory.createLineBorder(Color.black));
    }



    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400,400);
    }


    class ButtonHandler implements ActionListener {
        UI panel;
        int action;
        ButtonHandler(UI panel, int action) {
            this.panel = panel;
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (action == 1) {
                System.out.println(panel.search_field.getText());

            }
        }
    }
}
