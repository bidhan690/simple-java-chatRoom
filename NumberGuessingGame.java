import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NumberGuessingGame extends JFrame implements ActionListener {
    private JLabel instructionLabel;
    private JTextField guessField;
    private JButton guessButton;
    private int secretNumber;

    public NumberGuessingGame() {
        // Initialize the GUI components
        instructionLabel = new JLabel("Guess a number between 1 and 100:");
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        guessButton.addActionListener(this);

        // Layout the GUI components
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(instructionLabel);
        panel.add(guessField);
        panel.add(guessButton);
        getContentPane().add(panel, BorderLayout.CENTER);

        // Set up the game
        secretNumber = (int) (Math.random() * 100) + 1;
        setTitle("Number Guessing Game");
        setSize(300, 100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guessButton) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                if (guess == secretNumber) {
                    JOptionPane.showMessageDialog(this, "You guessed it!");
                    secretNumber = (int) (Math.random() * 100) + 1;
                } else if (guess < secretNumber) {
                    JOptionPane.showMessageDialog(this, "Too low! Try again.");
                } else {
                    JOptionPane.showMessageDialog(this, "Too high! Try again.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number.");
            }
            guessField.setText("");
        }
    }

    public static void main(String[] args) {
        new NumberGuessingGame();
    }
}
