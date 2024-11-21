package ui;

import factories.GifEditorFactory;
import factories.ImageEditorFactory;
import factories.JpgEditorFactory;
import factories.PngEditorFactory;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import models.ImageEditor;

public class EditorUI {

    private final JFrame frame;
    private final JPanel panel;
    private final JLabel imageLabel;
    private BufferedImage originalImage;
    private BufferedImage currentImage;
    private ImageEditor editor;
    private ImageEditorFactory editorFactory;

    
    private double scaleFactor = 1.0; 

    public EditorUI() {
        frame = new JFrame("Image Editor");
        panel = new JPanel();
        imageLabel = new JLabel();
        panel.setLayout(new BorderLayout());
        panel.add(imageLabel, BorderLayout.CENTER);
    }

    public void show() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); 
        frame.setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        JButton openButton = new JButton("Open Image");
        JButton zoomInButton = new JButton("Zoom In");
        JButton zoomOutButton = new JButton("Zoom Out");

        controlPanel.add(openButton);
        controlPanel.add(zoomInButton);
        controlPanel.add(zoomOutButton);

        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.add(panel, BorderLayout.CENTER);

        
        openButton.addActionListener(e -> openImage(frame));

      
        zoomInButton.addActionListener(e -> zoomIn());

        
        zoomOutButton.addActionListener(e -> zoomOut());

        frame.setVisible(true);
    }

    private void openImage(JFrame frame) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an image file");
        int result = fileChooser.showOpenDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();

           
            if (filePath.endsWith(".jpg") || filePath.endsWith(".jpeg")) {
                editorFactory = new JpgEditorFactory();
            } else if (filePath.endsWith(".png")) {
                editorFactory = new PngEditorFactory();
            } else if (filePath.endsWith(".gif")) {
                editorFactory = new GifEditorFactory();
            } else {
                JOptionPane.showMessageDialog(frame, "Unsupported file format.");
                return;
            }

            editor = editorFactory.createEditor();
            try {
                editor.open(filePath); 

               
                originalImage = ImageIO.read(selectedFile);
                currentImage = originalImage;
                updateImageDisplay();
            } catch (IOException e) {
                
                JOptionPane.showMessageDialog(frame, "Error loading image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                
            }
        }
    }

    
    private void zoomIn() {
        if (currentImage != null) {
            scaleFactor += 0.1;  
            if (scaleFactor > 2.0) {  
                scaleFactor = 2.0;
            }

            int newWidth = (int) (originalImage.getWidth() * scaleFactor);
            int newHeight = (int) (originalImage.getHeight() * scaleFactor);
            Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

            currentImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = currentImage.createGraphics();
            g2d.drawImage(scaledImage, 0, 0, null);
            g2d.dispose();
            updateImageDisplay();
        } else {
            JOptionPane.showMessageDialog(frame, "No image to zoom.");
        }
    }

    
    private void zoomOut() {
        if (currentImage != null) {
            scaleFactor -= 0.1;  
            if (scaleFactor < 0.5) {  
                scaleFactor = 0.5;
            }

            int newWidth = (int) (originalImage.getWidth() * scaleFactor);
            int newHeight = (int) (originalImage.getHeight() * scaleFactor);
            Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

            currentImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = currentImage.createGraphics();
            g2d.drawImage(scaledImage, 0, 0, null);
            g2d.dispose();
            updateImageDisplay();
        } else {
            JOptionPane.showMessageDialog(frame, "No image to zoom.");
        }
    }

    private void updateImageDisplay() {
        if (currentImage != null) {
            ImageIcon imageIcon = new ImageIcon(currentImage);
            imageLabel.setIcon(imageIcon);
            
            imageLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight() - 100);
            frame.revalidate();
            frame.repaint();
        }
    }
}
