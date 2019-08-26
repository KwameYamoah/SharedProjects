package Program;

import User_Interface.UI;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
//TODO   1. Read pdf files
//       2. Convert pdf to text
//       3. Split text content into individual words
//       4. Filter out ascii symbols or punctuation
//         4.a. Memory-management memory management (Punctuation management)
//       5. Get word term frequency
//       6. Get TF IDF (formula)
//       7. UI to apply search and print out results

        JFrame frame = new JFrame();
        frame.add(new UI());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }
}
