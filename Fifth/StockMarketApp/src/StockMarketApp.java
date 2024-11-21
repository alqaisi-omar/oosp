import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class StockMarketApp extends JFrame {
    private final JComboBox<String> stockSelector;
    private final JLabel priceLabel;
    private final JTextArea notificationsArea;
    private final JButton updateButton, subscribeButton;
    private final ChartPanel chartPanel;
    private final Map<String, Double> stockPrices;
    private final Random random;
    private double lastPrice;

    public StockMarketApp() {
        setTitle("Stock Market App");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.DARK_GRAY);

        
        stockPrices = new HashMap<>();
        random = new Random();
        for (String company : new String[]{"Apple", "Google", "Amazon", "Microsoft", "Tesla", "Facebook", "IBM", "Oracle", "Intel", "Cisco"}) {
            stockPrices.put(company, 100.0 + random.nextDouble() * 100);
        }

        lastPrice = stockPrices.get("Apple");

        
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(Color.DARK_GRAY);
        stockSelector = new JComboBox<>(stockPrices.keySet().toArray(String[]::new));
 
        stockSelector.setSelectedIndex(0);
        stockSelector.addActionListener(e -> updatePriceDisplay(stockSelector.getSelectedItem().toString()));
        topPanel.add(stockSelector);

        subscribeButton = new JButton("Subscribe for Notifications");
        topPanel.add(subscribeButton);

        updateButton = new JButton("Update Interface");
        topPanel.add(updateButton);

        add(topPanel, BorderLayout.NORTH);

       
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.DARK_GRAY);

       
        priceLabel = new JLabel("$0.00", SwingConstants.CENTER);
        priceLabel.setFont(new Font("Arial", Font.BOLD, 48));
        priceLabel.setForeground(Color.GREEN);
        centerPanel.add(priceLabel, BorderLayout.NORTH);

        
        chartPanel = new ChartPanel();
        chartPanel.setPreferredSize(new Dimension(400, 400));  
        centerPanel.add(chartPanel, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        
        notificationsArea = new JTextArea(5, 20);
        notificationsArea.setEditable(false);
        add(new JScrollPane(notificationsArea), BorderLayout.SOUTH);

        
        updateButton.addActionListener(new UpdateListener());
        subscribeButton.addActionListener(new SubscribeListener());

        
        Timer timer = new Timer(1000, e -> fluctuatePrice());
        timer.start();

        
        updatePriceDisplay(stockSelector.getSelectedItem().toString());

        setVisible(true);
    }

    private void updatePriceDisplay(String stockName) {
        double price = stockPrices.get(stockName);
        priceLabel.setText(String.format("$%.2f", price));

        
        if (price > lastPrice) {
            priceLabel.setForeground(Color.GREEN);
        } else if (price < lastPrice) {
            priceLabel.setForeground(Color.RED);
        }

        lastPrice = price;
        chartPanel.updateChart(price); 
    }

    private void fluctuatePrice() {
        String selectedStock = stockSelector.getSelectedItem().toString();
        double currentPrice = stockPrices.get(selectedStock);
        double change = (random.nextDouble() - 0.5) * 5;
        double newPrice = Math.max(0, currentPrice + change);
        stockPrices.put(selectedStock, newPrice);
        updatePriceDisplay(selectedStock);
        notificationsArea.append("Price updated for " + selectedStock + ": $" + String.format("%.2f", newPrice) + "\n");
    }

    private class UpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            fluctuatePrice();
        }
    }

    private class SubscribeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedStock = stockSelector.getSelectedItem().toString();
            notificationsArea.append("Subscribed to notifications for " + selectedStock + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StockMarketApp::new);
    }

    
    class ChartPanel extends JPanel {
        private final java.util.List<Double> prices;

        public ChartPanel() {
            prices = new ArrayList<>();
            setBackground(Color.DARK_GRAY);
        }

        public void updateChart(double newPrice) {
            if (prices.size() > 20) { 
                prices.remove(0);
            }
            prices.add(newPrice);
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int width = getWidth();
            int height = getHeight();
            int maxPoints = Math.min(prices.size(), 20);
            if (maxPoints < 2) return;

            
            double maxPrice = Collections.max(prices);
            double minPrice = Collections.min(prices);

            
            g.setColor(Color.WHITE);
            g.drawString(String.format("High: $%.2f", maxPrice), 5, 15);
            g.drawString(String.format("Low: $%.2f", minPrice), 5, height - 5);

            
            g.drawString("Time (Last 20 seconds)", width / 2 - 50, height - 10);

            
            g.setColor(Color.GREEN);
            for (int i = 0; i < maxPoints - 1; i++) {
                int x1 = i * width / 20;
                int y1 = height - (int) ((prices.get(i) - minPrice) / (maxPrice - minPrice) * height);
                int x2 = (i + 1) * width / 20;
                int y2 = height - (int) ((prices.get(i + 1) - minPrice) / (maxPrice - minPrice) * height);
                g.drawLine(x1, y1, x2, y2);
            }
        }
    }
}
