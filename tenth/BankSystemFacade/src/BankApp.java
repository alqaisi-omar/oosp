import java.awt.*;
import java.text.DecimalFormat;
import javax.swing.*;

/**
 * Основное приложение с графическим интерфейсом для взаимодействия с банковской системой.
 */
public class BankApp {
    private final BankFacade bankFacade;
    private static final DecimalFormat CURRENCY_FORMAT = new DecimalFormat("#,##0.00 ₽");

    public BankApp() {
        // Создание фасада для управления банковским счетом
        bankFacade = new BankFacade("12345678", 10000);

        // Окно приложения
        JFrame frame = new JFrame("Банковская система");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Верхняя панель с заголовком
        JLabel titleLabel = new JLabel("Добро пожаловать в вашу банковскую систему", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Центральная панель для операций
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 2, 10, 10));
        frame.add(centerPanel, BorderLayout.CENTER);

        // Поле для отображения баланса
        JLabel balanceLabel = new JLabel("Текущий баланс: " + CURRENCY_FORMAT.format(bankFacade.checkBalance()));
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        centerPanel.add(balanceLabel);

        JTextField amountField = new JTextField();
        amountField.setBorder(BorderFactory.createTitledBorder("Введите сумму (₽):"));
        centerPanel.add(amountField);

        JButton depositButton = new JButton("Пополнить");
        JButton withdrawButton = new JButton("Снять");
        JButton checkBalanceButton = new JButton("Проверить баланс");

        // Добавление кнопок на панель
        centerPanel.add(depositButton);
        centerPanel.add(withdrawButton);
        centerPanel.add(checkBalanceButton);

        // Лог операций
        JTextArea logArea = new JTextArea(10, 40);
        logArea.setEditable(false);
        logArea.setBorder(BorderFactory.createTitledBorder("История операций"));
        frame.add(new JScrollPane(logArea), BorderLayout.SOUTH);

        // Логика кнопок
        depositButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (bankFacade.deposit(amount)) {
                    logArea.append("Пополнение: " + CURRENCY_FORMAT.format(amount) + "\n");
                } else {
                    logArea.append("Ошибка: Неверная сумма.\n");
                }
                updateBalance(balanceLabel);
            } catch (NumberFormatException ex) {
                logArea.append("Ошибка: Введите корректную сумму.\n");
            }
        });

        withdrawButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (bankFacade.withdraw(amount)) {
                    logArea.append("Снятие: " + CURRENCY_FORMAT.format(amount) + "\n");
                } else {
                    logArea.append("Ошибка: Недостаточно средств.\n");
                }
                updateBalance(balanceLabel);
            } catch (NumberFormatException ex) {
                logArea.append("Ошибка: Введите корректную сумму.\n");
            }
        });

        checkBalanceButton.addActionListener(e -> updateBalance(balanceLabel));

        // Отображение окна
        frame.setVisible(true);
    }

    private void updateBalance(JLabel balanceLabel) {
        double balance = bankFacade.checkBalance();
        balanceLabel.setText("Текущий баланс: " + CURRENCY_FORMAT.format(balance));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BankApp::new);
    }
}
