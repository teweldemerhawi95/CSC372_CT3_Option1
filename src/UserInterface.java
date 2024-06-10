
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class UserInterface extends JFrame {
    private JTextArea textArea;
    private JPanel panel;

    public UserInterface() {
        // Create main application window
        setTitle("User Interface Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a text area
        textArea = new JTextArea(10, 50);

        // Create a panel and add the text area to it
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(textArea, BorderLayout.CENTER);

        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create a menu
        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);

        // Add menu items
        JMenuItem printDateTime = new JMenuItem("Print Date & Time");
        JMenuItem writeToFile = new JMenuItem("Write text to file");
        JMenuItem changeColor = new JMenuItem("Change Background Color");
        JMenuItem exit = new JMenuItem("Exit");

        menu.add(printDateTime);
        menu.add(writeToFile);
        menu.add(changeColor);
        menu.add(exit);

        // Adding action listeners to each item in the menu
        printDateTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printDateTime();
            }
        });

        writeToFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writeToFile();
            }
        });

        changeColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeBackgroundColor(changeColor);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Set the menu bar for the frame
        setJMenuBar(menuBar);

        // Add the panel to the frame
        add(panel);
    }

    // Print the date and time to textArea
    private void printDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        textArea.setText(dtf.format(now));
    }

    // write textArea contents to a file
    private void writeToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
            writer.write("\n" + textArea.getText());
            JOptionPane.showMessageDialog(this, "text successfully written to log.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "failed to write to file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Changing background color
    private void changeBackgroundColor(JMenuItem menuItem) {
        Random rand = new Random();
        int green = rand.nextInt(156) + 100;
        Color randomGreen = new Color(0, green, 0);
        textArea.setBackground(randomGreen);
        menuItem.setText("Current green: " + randomGreen);
    }
}
