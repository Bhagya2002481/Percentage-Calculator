package com.calculator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PercentageCalculator extends JFrame {

    // UI Components
    private JTextField inputField1;
    private JTextField inputField2;
    private JLabel resultLabel;
    private JComboBox<String> calcTypeComboBox;
    private JButton calculateButton;

    // Constructor: Setup UI and event handlers
    public PercentageCalculator() {
        setTitle("Percentage Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        
        // Dropdown to choose calculation type
        String[] calcTypes = {"Calculate Percentage", "Percentage Increase/Decrease", "Find Whole from Part and Percentage"};
        calcTypeComboBox = new JComboBox<>(calcTypes);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(calcTypeComboBox, gbc);
        
        // First input field
        inputField1 = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(new JLabel("Input 1:"), gbc);
        gbc.gridx = 1;
        add(inputField1, gbc);
        
        // Second input field
        inputField2 = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Input 2:"), gbc);
        gbc.gridx = 1;
        add(inputField2, gbc);
        
        // Calculation button
        calculateButton = new JButton("Calculate");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(calculateButton, gbc);
        
        // Result label to display output
        resultLabel = new JLabel("Result: ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(resultLabel, gbc);
        
        // Event Listener for button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performCalculation();
            }
        });
        
        pack();
        setLocationRelativeTo(null); // Center window
        setVisible(true);
    }

    // Method to perform calculations based on the selected type
    private void performCalculation() {
        String selectedCalc = (String) calcTypeComboBox.getSelectedItem();
        try {
            // Parse inputs. For some calculations only one input might be needed.
            double num1 = Double.parseDouble(inputField1.getText());
            double num2 = Double.parseDouble(inputField2.getText());
            double result = 0;
            
            switch (selectedCalc) {
                case "Calculate Percentage":
                    result = (num1 / num2) * 100;//num1=part,num2=whole
                    resultLabel.setText(String.format("Result: %.2f%%", result));
                    break;
                    
                case "Percentage Increase/Decrease":
                    if (num1 == 0) {
                        throw new ArithmeticException("Original value cannot be zero for percentage change.");
                    }
                    result = ((num2 - num1) / num1) * 100;//num2=new value,num1=old value
                    resultLabel.setText(String.format("Percentage Change: %.2f%%", result));
                    break;
                    
                case "Find Whole from Part and Percentage":
                    if (num2 == 0) {
                        throw new ArithmeticException("Percentage cannot be zero.");
                    }
                    result = (num1 * 100) / num2;//num1=part,num2=percentage
                    resultLabel.setText(String.format("Whole Value: %.2f", result));
                    break;
                    
                default:
                    resultLabel.setText("Invalid calculation type selected.");
                    break;
            }
        } catch (NumberFormatException nfe) {
            resultLabel.setText("Error: Please enter valid numbers.");
        } catch (ArithmeticException ae) {
            resultLabel.setText("Error: " + ae.getMessage());
        } catch (Exception ex) {
            resultLabel.setText("Error: " + ex.getMessage());
        }
    }

    // Main method to run the calculator
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PercentageCalculator());
    }
}
