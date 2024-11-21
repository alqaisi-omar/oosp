import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class ScaleAnimation extends Animation {
    private double scale = 1.0;
    private boolean growing = true;

    public ScaleAnimation(JPanel panel) {
        super(panel);
    }

    @Override
    protected void performAnimation() {
        if (growing) {
            scale += 0.05;
            if (scale >= 2.0) growing = false;
        } else {
            scale -= 0.05;
            if (scale <= 1.0) growing = true;
        }
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(scale, scale);
        g2d.fillRect((panel.getWidth() - 50) / 2, (panel.getHeight() - 50) / 2, 50, 50);
    }
}
