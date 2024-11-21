import java.awt.*;
import javax.swing.*;

public class AnimationApp extends JFrame {
    private AnimationPanel animationPanel;
    private final JButton moveButton, rotateButton, scaleButton;

    public AnimationApp() {
        setTitle("Template Animation App");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        
        animationPanel = new AnimationPanel();
        add(animationPanel, BorderLayout.CENTER);

        
        JPanel buttonPanel = new JPanel();
        moveButton = new JButton("Move");
        rotateButton = new JButton("Rotate");
        scaleButton = new JButton("Scale");

        buttonPanel.add(moveButton);
        buttonPanel.add(rotateButton);
        buttonPanel.add(scaleButton);
        add(buttonPanel, BorderLayout.SOUTH);

        
        moveButton.addActionListener(e -> animationPanel.setAnimation(new MoveAnimation(animationPanel)));
        rotateButton.addActionListener(e -> animationPanel.setAnimation(new RotateAnimation(animationPanel)));
        scaleButton.addActionListener(e -> animationPanel.setAnimation(new ScaleAnimation(animationPanel)));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AnimationApp app = new AnimationApp();
            app.setVisible(true);
        });
    }
}
