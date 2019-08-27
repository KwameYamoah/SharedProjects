package User_Interface;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;


//code from online -it creates a custom JTextField and alters paint methods to make it circular
public class RoundedJTextField extends JTextField {

    RoundedJTextField(String text, int columns) {
        super(text, columns);
        setOpaque(false);
        setBorder(new RoundBorder());
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
        super.paintComponent(g);
    }

}

class RoundBorder extends AbstractBorder {
    public void paintBorder(Component c, Graphics g,
                            int x, int y,
                            int width, int height) {
        Color oldColor = g.getColor();

        g.setColor(Color.black);
        g.drawRoundRect(x, y, width - 1, height - 1, 10, 10);
        g.setColor(oldColor);
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(4, 4, 4, 4);
    }

    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.top = insets.right = insets.bottom = 4;
        return insets;
    }

}