package model;

import java.awt.image.BufferedImage;

public class ImageSnapshot {
    private final BufferedImage imageState;

    public ImageSnapshot(BufferedImage image) {
        this.imageState = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                image.getType()
        );
        this.imageState.getGraphics().drawImage(image, 0, 0, null);
    }

    public BufferedImage getImageState() {
        return imageState;
    }
}
