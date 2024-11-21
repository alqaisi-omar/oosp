import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class RotateAnimation extends Animation {
    private double angle = 0;

    public RotateAnimation(JPanel panel) {
        super(panel);
    }

    @Override
    protected void performAnimation() {
        angle += 0.1; 
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(angle, panel.getWidth() / 2, panel.getHeight() / 2);
        g2d.fillRect(panel.getWidth() / 2 - 25, panel.getHeight() / 2 - 25, 50, 50);
    }
}
