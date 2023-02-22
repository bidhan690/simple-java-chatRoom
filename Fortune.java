import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Fortune extends JFrame implements ActionListener {
    // Components
    private JLabel titleLabel;
    private JLabel questionLabel;
    private JTextField questionField;
    private JButton predictButton;
    private JLabel resultLabel;

    // Constructor
    public Fortune() {
        // Set up the window
        setTitle("Fortune ");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create components
        titleLabel = new JLabel("Welcome to the Fortune Teller !");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        questionLabel = new JLabel("Ask your question:");
        questionField = new JTextField(20);
        predictButton = new JButton("Predict");
        predictButton.addActionListener(this);
        resultLabel = new JLabel();

        // Set up layout
        setLayout(new GridLayout(4, 1));

        // Add components to the window
        add(titleLabel);
        add(questionLabel);
        add(questionField);
        add(predictButton);
        add(resultLabel);

        // Make the window visible
        setVisible(true);
    }

    // Action listener for predict button
    public void actionPerformed(ActionEvent e) {
        // Generate a random number between 1 and 5
        int randomNumber = (int) (Math.random() * 5) + 1;

        // Get the user's question
        String question = questionField.getText();

        // Determine the fortune based on the random number
        String fortune;
        switch (randomNumber) {
            case 1:
                fortune = "Your Future is Bright";
                break;
            case 2:
                fortune = "Beware of false friends";
                break;
            case 3:
                fortune = "Good things come to those who wait.";
                break;
            case 4:
                fortune = "A new opportunity will present itself to you soon.";
                break;
            default:
                fortune = "Travel will bring you great joy and adventure.";
                break;
        }

        // Display the fortune
        resultLabel.setText("The fortune teller says: " + fortune);
    }

    // Main method
    public static void main(String[] args) {
        Fortune fortune = new Fortune();
    }
    
}
