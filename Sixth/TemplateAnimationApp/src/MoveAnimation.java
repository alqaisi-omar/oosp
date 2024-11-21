import java.awt.Graphics;
import javax.swing.JPanel;

public class MoveAnimation extends Animation {
    private int x = 0;

    public MoveAnimation(JPanel panel) {
        super(panel);
    }

    @Override
    protected void performAnimation() {
        x = (x + 5) % panel.getWidth(); 
    }

    public void paint(Graphics g) {
        g.fillOval(x, panel.getHeight() / 2, 50, 50);
    }
}
