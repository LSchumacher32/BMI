import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

    private JTextField weightField;
    private JTextField heightField;
    private JLabel resultLabel;
    private JLabel categoryLabel;

    public Main() {
        setTitle("BMI Rechner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(380, 260);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);

        // Dark Theme Colors
        Color bg = new Color(18, 18, 18);
        Color card = new Color(33, 33, 33);
        Color accent = new Color(0, 150, 136);
        Color text = new Color(236, 239, 241);

        getContentPane().setBackground(bg);

        // Title
        JLabel title = new JLabel("BMI Rechner", SwingConstants.CENTER);
        title.setForeground(text);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        // Center card panel
        JPanel cardPanel = new JPanel(new GridBagLayout());
        cardPanel.setBackground(card);
        cardPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        add(cardPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel weightLabel = new JLabel("Gewicht (kg):");
        weightLabel.setForeground(text);
        cardPanel.add(weightLabel, gbc);

        gbc.gridx = 1;
        weightField = createTextField(card, text);
        cardPanel.add(weightField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel heightLabel = new JLabel("Größe (m):");
        heightLabel.setForeground(text);
        cardPanel.add(heightLabel, gbc);

        gbc.gridx = 1;
        heightField = createTextField(card, text);
        cardPanel.add(heightField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JButton calcButton = new JButton("Berechnen");
        calcButton.setBackground(accent);
        calcButton.setForeground(Color.WHITE);
        calcButton.setFocusPainted(false);
        calcButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        calcButton.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        calcButton.addActionListener(this::calculateBmi);
        cardPanel.add(calcButton, gbc);

        gbc.gridy = 3;
        resultLabel = new JLabel("BMI: –", SwingConstants.CENTER);
        resultLabel.setForeground(text);
        resultLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        cardPanel.add(resultLabel, gbc);

        gbc.gridy = 4;
        categoryLabel = new JLabel("Kategorie: –", SwingConstants.CENTER);
        categoryLabel.setForeground(accent);
        categoryLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        cardPanel.add(categoryLabel, gbc);
    }

    private JTextField createTextField(Color bg, Color fg) {
        JTextField tf = new JTextField(10);
        tf.setBackground(bg.darker());
        tf.setForeground(fg);
        tf.setCaretColor(fg);
        tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(55, 71, 79)),
                BorderFactory.createEmptyBorder(4, 6, 4, 6)
        ));
        return tf;
    }

    private void calculateBmi(ActionEvent e) {
        try {
            double weight = Double.parseDouble(weightField.getText().replace(',', '.'));
            double height = Double.parseDouble(heightField.getText().replace(',', '.'));

            if (weight <= 0 || height <= 0) {
                throw new NumberFormatException();
            }

            double bmi = weight / (height * height);
            String bmiText = String.format("BMI: %.1f", bmi);
            resultLabel.setText(bmiText);

            String category;
            if (bmi < 18.5) {
                category = "Untergewicht";
            } else if (bmi < 25) {
                category = "Normalgewicht";
            } else if (bmi < 30) {
                category = "Übergewicht";
            } else {
                category = "Adipositas";
            }
            categoryLabel.setText("Kategorie: " + category);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Bitte gültige Zahlen für Gewicht und Größe eingeben.",
                    "Eingabefehler",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
