import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class StockMarketPanel extends JPanel {
    private final Map<String, Stock> stocks;
    private final JTextArea notificationsArea;
    private final JComboBox<String> stockSelector;
    private final JTextField priceField;
    private final Timer priceUpdateTimer;

    public StockMarketPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.DARK_GRAY);

        
        stocks = new HashMap<>();
        stocks.put("Apple", new Stock("Apple", 150.0));
        stocks.put("Google", new Stock("Google", 2800.0));
        stocks.put("Amazon", new Stock("Amazon", 3300.0));

        
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(3, 2, 5, 5));
        controlPanel.setBackground(Color.DARK_GRAY);

        JLabel selectLabel = new JLabel("Select Stock:");
        selectLabel.setForeground(Color.WHITE);
        controlPanel.add(selectLabel);

        stockSelector = new JComboBox<>(stocks.keySet().toArray(String[]::new));
        stockSelector.setBackground(Color.GRAY);
        stockSelector.setForeground(Color.WHITE);
        controlPanel.add(stockSelector);

        JLabel priceLabel = new JLabel("New Price:");
        priceLabel.setForeground(Color.WHITE);
        controlPanel.add(priceLabel);

        priceField = new JTextField();
        priceField.setBackground(Color.GRAY);
        priceField.setForeground(Color.WHITE);
        controlPanel.add(priceField);

        JButton updatePriceButton = new JButton("Update Price");
        styleButton(updatePriceButton);
        updatePriceButton.addActionListener(new PriceUpdateListener());
        controlPanel.add(updatePriceButton);

        add(controlPanel, BorderLayout.NORTH);

        
        notificationsArea = new JTextArea();
        notificationsArea.setEditable(false);
        notificationsArea.setBackground(Color.BLACK);
        notificationsArea.setForeground(Color.LIGHT_GRAY);
        notificationsArea.setBorder(new EmptyBorder(5, 5, 5, 5));
        JScrollPane scrollPane = new JScrollPane(notificationsArea);
        add(scrollPane, BorderLayout.CENTER);

        
        JPanel investorPanel = new JPanel();
        investorPanel.setLayout(new GridLayout(2, 1, 5, 5));
        investorPanel.setBackground(Color.DARK_GRAY);

        JButton subscribeButton = new JButton("Subscribe to Notifications");
        styleButton(subscribeButton);
        subscribeButton.addActionListener(new SubscribeListener());
        investorPanel.add(subscribeButton);

        JButton unsubscribeButton = new JButton("Unsubscribe from Notifications");
        styleButton(unsubscribeButton);
        unsubscribeButton.addActionListener(new UnsubscribeListener());
        investorPanel.add(unsubscribeButton);

        add(investorPanel, BorderLayout.SOUTH);

        
        priceUpdateTimer = new Timer(1000, new PriceFluctuationListener());
        priceUpdateTimer.start();
    }

    private void styleButton(JButton button) {
        button.setBackground(Color.GRAY);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    }

    private class PriceUpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedStock = (String) stockSelector.getSelectedItem();
            Stock stock = stocks.get(selectedStock);

            try {
                double newPrice = Double.parseDouble(priceField.getText());
                stock.setPrice(newPrice);
                notificationsArea.append("Updated " + stock.getName() + " price to " + newPrice + "\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid price.");
            }
        }
    }

    private class SubscribeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedStock = (String) stockSelector.getSelectedItem();
            Stock stock = stocks.get(selectedStock);

            Investor investor = new Investor("Investor " + stock.getName());
            stock.addInvestor(investor);
            notificationsArea.append("Subscribed to " + stock.getName() + " notifications.\n");
        }
    }

    private class UnsubscribeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedStock = (String) stockSelector.getSelectedItem();
            Stock stock = stocks.get(selectedStock);

            Investor investor = new Investor("Investor " + stock.getName());
            stock.removeInvestor(investor);
            notificationsArea.append("Unsubscribed from " + stock.getName() + " notifications.\n");
        }
    }

    private class PriceFluctuationListener implements ActionListener {
        private final Random random = new Random();

        @Override
        public void actionPerformed(ActionEvent e) {
            for (Stock stock : stocks.values()) {
                double fluctuation = (random.nextDouble() - 0.5) * 2; 
                double newPrice = stock.getPrice() + fluctuation;

                
                stock.setPrice(newPrice);
                String priceChange = String.format("%.2f", newPrice);
                Color priceColor = fluctuation > 0 ? Color.GREEN : Color.RED;
                notificationsArea.append(stock.getName() + " price: " + priceChange + "\n");
                notificationsArea.setForeground(priceColor);
            }
        }
    }
}
