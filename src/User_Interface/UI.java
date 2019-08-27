package User_Interface;

import HelperClasses.Command;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashMap;

import static HelperClasses.Constants.*;

public class UI extends JPanel {
    private RoundedJTextField add_file; //rounded text-field to give it a modern feel
    private JComboBox<String> file_list; //rounded text-field to give it a modern feel
    private RoundedJTextField search_keyword; //rounded text-field to give it a modern feel
    private JLabel status_label;
    private JFileChooser fileChooser = null;
    //TODO second UI - tomorrow
    public JFrame frame;
    private HashMap<String, File> files_to_search = new HashMap<>();

    public UI(JFrame frame) {
        this.frame = frame;
        addMouseMotionListener(new MouseHandler());

        LookAndFeel previousLF = UIManager.getLookAndFeel();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            UIManager.setLookAndFeel(previousLF);
        } catch (IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException | ClassNotFoundException ignored) {
        }


        final Color background_color = Color.BLACK;

        //removes padding around components
        FlowLayout no_padding_layout = new FlowLayout();
        no_padding_layout.setVgap(0);
        no_padding_layout.setHgap(0);
        setLayout(no_padding_layout);

        setBackground(background_color);

        //title in program window and other labels
        JLabel title = new JLabel("File Searcher");
        title.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        title.setForeground(Color.WHITE);

        JLabel file_path = new JLabel("Enter file path  ");
        JLabel files_added = new JLabel("Files added ");
        JLabel search_label = new JLabel("Search keyword ");

        status_label = new JLabel("Input details");
        status_label.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        status_label.setForeground(Color.GREEN);

        file_path.setForeground(Color.WHITE);
        files_added.setForeground(Color.WHITE);
        search_label.setForeground(Color.WHITE);


        // text fields
        add_file = new RoundedJTextField("",20);
        file_list = new JComboBox<>();
        file_list.setBackground(Color.WHITE);
        file_list.setPreferredSize(new Dimension(add_file.getPreferredSize().width,add_file.getPreferredSize().height));
        search_keyword = new RoundedJTextField("",20);

        //button and corresponding listeners
        JButton add_button = new JButton("Add");
        add_button.addActionListener(new ButtonHandler(this,Command.ADD));

        JButton open_button = new JButton("Open");
        open_button.addActionListener(new ButtonHandler(this,Command.OPEN));

        JButton remove_button = new JButton("Remove");
        remove_button.addActionListener(new ButtonHandler(this,Command.REMOVE));

        JButton search_button = new JButton("Search");
        search_button.addActionListener(new ButtonHandler(this,Command.SEARCH));

        JButton reset_button = new JButton("Reset");
        reset_button.addActionListener(new ButtonHandler(this,Command.RESET));

        JButton exit_button = new JButton("Exit");
        exit_button.addActionListener(new ButtonHandler(this,Command.EXIT));

        //north panel(container/section) - contains title
        JPanel north = new JPanel();
        north.add(title);
        north.setBackground(background_color);
        north.setPreferredSize(new Dimension(UI_WIDTH,NORTH_PANEL_HEIGHT));


        //adding north section to frame/panel
        add(north,BorderLayout.NORTH);

        //center panel(container/section)- contains text-fields and corresponding button
        JPanel center = new JPanel();
        JPanel center_left = new JPanel();
        JPanel center_right = new JPanel();


        //setting different padding for center, center_left, and center_right
        FlowLayout center_right_layout = new FlowLayout(FlowLayout.LEFT);
        center_right_layout.setVgap(10);
        center_right_layout.setHgap(15);

        FlowLayout center_left_layout = new FlowLayout(FlowLayout.LEFT);
        center_left_layout.setVgap(17);
        center_left_layout.setHgap(15);


        center.setLayout(no_padding_layout);
        center_left.setLayout(center_left_layout);
        center_right.setLayout(center_right_layout);

        //setting sizes of center, center_left, and center_right
        center.setPreferredSize(new Dimension(UI_WIDTH,CENTER_PANEL_HEIGHT));
        center_left.setPreferredSize(new Dimension(110,CENTER_PANEL_HEIGHT));
        center_right.setPreferredSize(new Dimension(400,CENTER_PANEL_HEIGHT));

        //adding first row of components
        center_left.add(file_path);
        center_right.add(add_file);
        center_right.add(add_button);
        center_right.add(open_button);

        //adding second row of components
        center_left.add(files_added);
        center_right.add(file_list);
        center_right.add(remove_button);

        //adding third row of components
        center_left.add(search_label);
        center_right.add(search_keyword);
        center_right.add(search_button);

        //setting background for center section
        center_left.setBackground(background_color);
        center_right.setBackground(background_color);
        center.setBackground(background_color);

        //adding center_left and center_right to center  and center section to UI Panel
        center.add(center_left,BorderLayout.WEST);
        center.add(center_right,BorderLayout.EAST);
        add(center,BorderLayout.CENTER);

        //south panel(container/section)- will contain footer elements (i.e exit and clear button)
        JPanel south = new JPanel();
        south.setBackground(background_color);
        south.setPreferredSize(new Dimension(UI_WIDTH,SOUTH_PANEL_HEIGHT));

        //west of south section - setting padding and adding component(reset button)
        JPanel south_left = new JPanel();
        south_left.add(reset_button);

        south_left.setPreferredSize(new Dimension(UI_WIDTH / 3, SOUTH_PANEL_HEIGHT));
        south_left.setBackground(background_color);
        south_left.setLayout(new FlowLayout(FlowLayout.LEFT));
        south_left.setBorder(new EmptyBorder(0,30,0,0));

        //center of south section - setting padding and adding components(status label)
        JPanel south_center = new JPanel();
        south_center.add(status_label);
        south_center.setPreferredSize(new Dimension(UI_WIDTH / 3, SOUTH_PANEL_HEIGHT));
        south_center.setBackground(background_color);
        south_center.setLayout(new FlowLayout(FlowLayout.CENTER));

        //east of south section - setting padding and adding components(exit and search button)
        JPanel south_right = new JPanel();
        south_right.setPreferredSize(new Dimension(UI_WIDTH / 3, SOUTH_PANEL_HEIGHT));
        south_right.add(exit_button);
        south_right.add(search_button);
        south_right.setBackground(background_color);
        south_right.setLayout(new FlowLayout(FlowLayout.RIGHT));
        south_right.setBorder(new EmptyBorder(0,0,0,23));


        //adding south_left and south_right to south  and south section to UI Panel
        south.add(south_left,BorderLayout.WEST);
        south.add(south_center, BorderLayout.CENTER);
        south.add(south_right,BorderLayout.EAST);
        south.setLayout(no_padding_layout);
        add(south,BorderLayout.SOUTH);
        //south.setBorder(BorderFactory.createLineBorder(Color.black));
    }



    @Override
    public Dimension getPreferredSize() {
        return new Dimension(UI_WIDTH,UI_HEIGHT);
    }


    class ButtonHandler implements ActionListener {     //handles what buttons do based on corresponding action
        UI panel;
        Command command;
        ButtonHandler(UI panel, Command command) {
            this.panel = panel;
            this.command = command;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (command){
                case EXIT:
                    System.exit(WindowConstants.DO_NOTHING_ON_CLOSE);
                    break;
                case ADD:
                    if (!(add_file.getText().trim()).equals("")) {
                        File file = new File(add_file.getText());
                        files_to_search.put(file.getName(), file);
                        file_list.addItem(file.getName());  //must also check if file path is legit
                        System.out.println(files_to_search);
                        // Pathways needs to be passed to file searcher
                    }
                    break;
                case OPEN:
                    int action = fileChooser.showOpenDialog(panel);
                    if (action == JFileChooser.APPROVE_OPTION) {
                        add_file.setText(fileChooser.getSelectedFile().toString());
                    }
                    break;
                case RESET:
                    add_file.setText("");
                    file_list.removeAllItems();
                    search_keyword.setText("");
                    break;
                case REMOVE:
                    String file = (String) file_list.getSelectedItem();
                    file_list.removeItem(file);
                    files_to_search.remove(file);
                    break;
                case SEARCH:
                    //TODO Calls static search method
                    break;

            }
        }
    }

    class MouseHandler extends MouseAdapter {

        private int posX, posY;

        @Override
        public void mouseMoved(MouseEvent e) {
            posX = e.getX();
            posY = e.getY();

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Rectangle rectangle = frame.getBounds();
            frame.setBounds(e.getXOnScreen() - posX, e.getYOnScreen() - posY, rectangle.width, rectangle.height);

        }


    }


}


