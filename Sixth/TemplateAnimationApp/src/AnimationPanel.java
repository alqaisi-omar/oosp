import java.awt.*;
import javax.swing.*;

public class AnimationPanel extends JPanel {
    private Animation currentAnimation;

    public void setAnimation(Animation animation) {
        this.currentAnimation = animation;
        Timer timer = new Timer(30, e -> {
            if (currentAnimation != null) currentAnimation.update();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (currentAnimation != null && currentAnimation instanceof MoveAnimation) {
            ((MoveAnimation) currentAnimation).paint(g);
        } else if (currentAnimation != null && currentAnimation instanceof RotateAnimation) {
            ((RotateAnimation) currentAnimation).paint(g);
        } else if (currentAnimation != null && currentAnimation instanceof ScaleAnimation) {
            ((ScaleAnimation) currentAnimation).paint(g);
        }
    }
}
