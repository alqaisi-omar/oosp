import javax.swing.JPanel;

public abstract class Animation {
    protected JPanel panel;

    public Animation(JPanel panel) {
        this.panel = panel;
    }

    
    public final void update() {
        performAnimation();
        panel.repaint();
    }

   
    protected abstract void performAnimation();
}
