package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.ImageEditor;

public class ImageEditorApp extends JFrame {
    private final ImageEditor imageEditor;
    private final JLabel imageLabel;

    public ImageEditorApp() {
        setTitle("Image Editor with Undo/Redo and Brightness Adjustment");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

       
        BufferedImage initialImage = new BufferedImage(800, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = initialImage.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, initialImage.getWidth(), initialImage.getHeight());
        g.dispose();

        imageEditor = new ImageEditor(initialImage);
        imageLabel = new JLabel(new ImageIcon(imageEditor.getCurrentImage()));

        
        add(new JScrollPane(imageLabel), BorderLayout.CENTER);

        
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JButton(new AbstractAction("Open Image") {
            @Override
            public void actionPerformed(ActionEvent e) {
                openImage();
            }
        }));
        controlPanel.add(new JButton(new AbstractAction("Increase Brightness") {
            @Override
            public void actionPerformed(ActionEvent e) {
                adjustBrightness(20);
            }
        }));
        controlPanel.add(new JButton(new AbstractAction("Decrease Brightness") {
            @Override
            public void actionPerformed(ActionEvent e) {
                adjustBrightness(-20);
            }
        }));
        controlPanel.add(new JButton(new AbstractAction("Undo") {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageEditor.undo();
                updateUI();
            }
        }));
        controlPanel.add(new JButton(new AbstractAction("Redo") {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageEditor.redo();
                updateUI();
            }
        }));
        controlPanel.add(new JButton(new AbstractAction("Show History") {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHistory();
            }
        }));

        add(controlPanel, BorderLayout.SOUTH);
    }

    private void openImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg", "bmp", "gif"));
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedImage loadedImage = ImageIO.read(selectedFile);
                imageEditor.applyChange(loadedImage);
                updateUI();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Failed to open image: " + ex.getMessage());
            }
        }
    }

    private void adjustBrightness(int adjustment) {
        BufferedImage currentImage = imageEditor.getCurrentImage();
        BufferedImage newImage = new BufferedImage(
                currentImage.getWidth(),
                currentImage.getHeight(),
                currentImage.getType()
        );

        for (int y = 0; y < currentImage.getHeight(); y++) {
            for (int x = 0; x < currentImage.getWidth(); x++) {
                int rgb = currentImage.getRGB(x, y);
                Color color = new Color(rgb, true);
                int r = Math.min(255, Math.max(0, color.getRed() + adjustment));
                int g = Math.min(255, Math.max(0, color.getGreen() + adjustment));
                int b = Math.min(255, Math.max(0, color.getBlue() + adjustment));
                newImage.setRGB(x, y, new Color(r, g, b, color.getAlpha()).getRGB());
            }
        }

        imageEditor.applyChange(newImage);
        updateUI();
    }

    private void showHistory() {
        StringBuilder historyDetails = new StringBuilder("Edit History:\n");

        for (int i = 0; i < imageEditor.getHistory().size(); i++) {
            historyDetails.append("Change ").append(i + 1).append(": Brightness adjustment\n");
        }

        JOptionPane.showMessageDialog(this, historyDetails.toString(), "History", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateUI() {
        imageLabel.setIcon(new ImageIcon(imageEditor.getCurrentImage()));
    }
}
