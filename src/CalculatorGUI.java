import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame {
    private JTextField displayField;
    private double num1, num2, result;
    private String operator;

    public CalculatorGUI() {
        super("Калькулятор");

        // Создаем текстовое поле для отображения ввода и результата
        displayField = new JTextField(15);
        displayField.setEditable(false);
        displayField.setFont(new Font("Arial", Font.PLAIN, 20));

        // Создаем кнопки для цифр и операций
        JButton[] digitButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            digitButtons[i] = new JButton(String.valueOf(i));
            digitButtons[i].setFont(new Font("Arial", Font.PLAIN, 20));
        }

        JButton addButton = new JButton("+");
        JButton subtractButton = new JButton("-");
        JButton multiplyButton = new JButton("*");
        JButton divideButton = new JButton("/");
        JButton equalsButton = new JButton("=");
        JButton clearButton = new JButton("C");

        // Устанавливаем слушатели для кнопок цифр и операций
        ActionListener digitListener = e -> {
            String currentText = displayField.getText();
            displayField.setText(currentText + e.getActionCommand());
        };

        for (int i = 0; i < 10; i++) {
            digitButtons[i].addActionListener(digitListener);
        }

        addButton.addActionListener(e -> handleOperatorClick("+"));
        subtractButton.addActionListener(e -> handleOperatorClick("-"));
        multiplyButton.addActionListener(e -> handleOperatorClick("*"));
        divideButton.addActionListener(e -> handleOperatorClick("/"));

        equalsButton.addActionListener(e -> calculateResult());
        clearButton.addActionListener(e -> clearDisplay());

        // Создаем панель для кнопок
        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        buttonPanel.add(digitButtons[7]);
        buttonPanel.add(digitButtons[8]);
        buttonPanel.add(digitButtons[9]);
        buttonPanel.add(addButton);
        buttonPanel.add(digitButtons[4]);
        buttonPanel.add(digitButtons[5]);
        buttonPanel.add(digitButtons[6]);
        buttonPanel.add(subtractButton);
        buttonPanel.add(digitButtons[1]);
        buttonPanel.add(digitButtons[2]);
        buttonPanel.add(digitButtons[3]);
        buttonPanel.add(multiplyButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(digitButtons[0]);
        buttonPanel.add(equalsButton);
        buttonPanel.add(divideButton);

        // Создаем основную панель и добавляем компоненты
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(displayField, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Настройки окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(300, 400);
        setLocationRelativeTo(null);

        // Добавляем основную панель в окно
        add(mainPanel);

        setVisible(true);
    }

    private void handleOperatorClick(String operator) {
        this.operator = operator;
        String currentText = displayField.getText();
        num1 = Double.parseDouble(currentText);
        displayField.setText("");
    }

    private void calculateResult() {
        String currentText = displayField.getText();
        num2 = Double.parseDouble(currentText);

        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    displayField.setText("Ошибка: на ноль делить нельзя!");
                    return;
                }
                break;
            default:
                displayField.setText("Ошибка: некорректная операция!");
                return;
        }

        displayField.setText(String.valueOf(result));
    }

    private void clearDisplay() {
        displayField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculatorGUI::new);
    }
}